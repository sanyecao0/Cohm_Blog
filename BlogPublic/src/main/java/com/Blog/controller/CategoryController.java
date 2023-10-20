package com.Blog.controller;

import com.Blog.domain.entity.ResponseResult;
import com.Blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Api(tags = "文章类别",description = "文章类别相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @ApiOperation(value = "文章分类列表",notes = "获取文章分类")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}
