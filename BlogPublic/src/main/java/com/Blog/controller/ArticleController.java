package com.Blog.controller;

import com.Blog.domain.entity.Article;
import com.Blog.domain.entity.ResponseResult;
import com.Blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "文章",description = "文章相关接口")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @GetMapping("/hotArticleList")
    @ApiOperation(value = "热门文章列表",notes = "获取热门文章")
    public ResponseResult hotArticleList(){
        ResponseResult result =  articleService.hotArticleList();
        return result;
    }
    @GetMapping("/articleList")
    @ApiOperation(value = "文章列表",notes = "分页获取文章")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "文章分类",notes = "获取文章分类")
    public  ResponseResult getArticleDetail(@PathVariable("id") Long id){
            return  articleService.getArticleDetail(id);
    }
    @PutMapping("/updateViewCount/{id}")
    @ApiOperation(value = "浏览量更新",notes = "更新浏览量")
    public  ResponseResult updateViewCount(@PathVariable("id") Long id){
     return articleService.updateViewCount(id);
    }

}
