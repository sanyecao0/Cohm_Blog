package com.Blog.controller;

import com.Blog.domain.entity.Comment;
import com.Blog.domain.entity.ResponseResult;
import com.Blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentlist")
    @ApiOperation(value = "评论列表",notes = "获取一页评论")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
           return  commentService.commentList(articleId,pageNum,pageSize);
    }
    @PostMapping("/commenList")
    @ApiOperation(value = "成功结果",notes = "发送评论")
    public  ResponseResult addComment(@RequestBody Comment comment){
          return commentService.addComment(comment);
    }
}
