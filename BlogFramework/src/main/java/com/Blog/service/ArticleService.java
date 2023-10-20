package com.Blog.service;

import com.Blog.domain.entity.Article;
import com.Blog.domain.entity.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);
}
