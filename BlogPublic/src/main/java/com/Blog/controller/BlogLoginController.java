package com.Blog.controller;

import com.Blog.domain.entity.ResponseResult;
import com.Blog.domain.entity.User;
import com.Blog.service.BlogLoginCService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "博客登录",description = "博客登录相关接口")
public class BlogLoginController {
    @Autowired
    private BlogLoginCService blogLoginCService;

    @PostMapping("/login")
    @ApiOperation(value = "操作结果",notes = "博客用户登录")
    public ResponseResult login(@RequestBody User user){
       return blogLoginCService.login(user);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "操作结果",notes = "博客用户登出")
    public ResponseResult logout(){return  blogLoginCService.logout();}
}
