package com.Blog.service.impl;

import com.Blog.Utils.BeanCopyUtils;
import com.Blog.domain.entity.Comment;
import com.Blog.domain.entity.ResponseResult;
import com.Blog.domain.vo.CommentVo;
import com.Blog.domain.vo.PageVo;
import com.Blog.enums.AppHttpCodeEnum;
import com.Blog.handler.exception.SystemException;
import com.Blog.mapper.CommentMapper;
import com.Blog.service.CommentService;
import com.Blog.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-10-09 22:32:06
 */
@Service("CommentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper=new LambdaQueryWrapper<Comment>();
        //根据articleid来查找
        queryWrapper.eq(Comment::getArticleId,articleId);
        //根评论值为-1
        queryWrapper.eq(Comment::getRootId,-1);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        //分页查询
        Page<Comment> commentPage=new Page<Comment>(pageNum,pageSize);
        page(commentPage,queryWrapper);

        List<CommentVo> commentVoList= toCommentVoList(commentPage.getRecords());
        //查询所有根评论对应的子评论集合，并赋予属性
        for (CommentVo vo : commentVoList) {
          //查询对应子评论
            List<CommentVo> children=getChildren(vo.getId());
        }

        return  ResponseResult.okResult(new PageVo(commentVoList,commentPage.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) throws SystemException {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    private  List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVoList= BeanCopyUtils.CopyBeanList(list,CommentVo.class);
        //遍历vo集合
        for (CommentVo vo : commentVoList) {
            //通过createBy查询用户昵称并赋值
            String nickName = userService.getById(vo.getCreateBy()).getNickName();
            vo.setUsername(nickName);
            //通过toCommonUserId查询用户的昵称并赋值
            //如果toCommonUserId不为-1才查询
            if(vo.getToCommentUserId()!=-1){
                String ToCommentUserName = userService.getById(vo.getToCommentUserId()).getNickName();
                vo.setToCommentUserName(ToCommentUserName);
            }
        }
        return  commentVoList;
    }

    /**
     * 根据根评论的id查找对应的子评论集合
     * @param id
     * @return
     */
    private   List<CommentVo> getChildren(Long id){
        LambdaQueryWrapper<Comment> queryWrapper=new LambdaQueryWrapper<Comment>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments=list(queryWrapper);
        List<CommentVo> commentVoList=toCommentVoList(comments);
        return  commentVoList;
    }
}

