package com.Blog.service.impl;

import com.Blog.Utils.BeanCopyUtils;
import com.Blog.Utils.JwtUtil;
import com.Blog.Utils.RedisCache;
import com.Blog.domain.entity.LoginUser;
import com.Blog.domain.entity.ResponseResult;
import com.Blog.domain.entity.User;
import com.Blog.domain.vo.BlogUserLoginVo;
import com.Blog.domain.vo.UserInfoVo;
import com.Blog.service.BlogLoginCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("BlogLoginCService")
public class BlogLoginCServiceImpl implements BlogLoginCService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw  new RuntimeException("用户名或密码错误");
        }
        //获取userID。生成token
        LoginUser loginUser=(LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        //把用户id存入redis
        redisCache.setCacheObject("bloglogin:"+id,loginUser);
        //把token和userinfo封装返回
        UserInfoVo userInfoVo = BeanCopyUtils.CopyBean(loginUser, UserInfoVo.class);
        BlogUserLoginVo vo=new BlogUserLoginVo(jwt,userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        //获取token，解析userid
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser  = (LoginUser) authentication.getPrincipal();
        Long userId=loginUser.getUser().getId();
        //删除 redis中的用户信息
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }
}
