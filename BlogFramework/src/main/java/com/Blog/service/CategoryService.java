package com.Blog.service;

import com.Blog.domain.entity.Category;
import com.Blog.domain.entity.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-10-05 17:38:03
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

