package com.Blog.service;


import com.Blog.domain.entity.Comment;
import com.Blog.domain.entity.ResponseResult;
import com.Blog.handler.exception.SystemException;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-10-09 22:32:06
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment) throws SystemException;
}

