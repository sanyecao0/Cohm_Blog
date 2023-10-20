package com.Blog.Runner;

import com.Blog.Utils.RedisCache;
import com.Blog.domain.entity.Article;
import com.Blog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper atricleMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        //查询博客信息
        List<Article> articleList=atricleMapper.selectList(null);
        //提取对应博客的阅览数量
        Map<String, Integer> viewCountMap = articleList.stream().collect(
                Collectors.toMap(
                        article -> article.getId().toString(), article -> article.getViewCount().intValue()
                )
        );
        //存入redis
        redisCache.setCacheMap("Article:viewCount",viewCountMap);
    }
}
