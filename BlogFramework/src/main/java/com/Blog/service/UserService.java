package com.Blog.service;

import com.Blog.domain.entity.ResponseResult;
import com.Blog.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-10-11 22:16:08
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

