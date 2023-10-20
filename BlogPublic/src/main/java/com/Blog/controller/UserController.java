package com.Blog.controller;

import com.Blog.annotation.SystemLog;
import com.Blog.domain.entity.ResponseResult;
import com.Blog.domain.entity.User;
import com.Blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户",description = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @ApiOperation(value = "用户信息",notes = "获取在spring sectury的用户信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @ApiOperation(value = "操作结果",notes = "更新用户信息")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody  User user){return  userService.updateUserInfo(user);}

    @PostMapping("/register")
    @ApiOperation(value = "操作结果",notes = "注册用户")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

}
