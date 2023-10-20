package com.Blog.service;

import com.Blog.domain.entity.Article;
import com.Blog.domain.entity.ResponseResult;
import com.Blog.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BlogLoginCService  {
    ResponseResult login(User user);

    ResponseResult logout();
}
