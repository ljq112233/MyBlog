package com.blog.service.impl;/**
 * @author Lu Jianqiang
 * @date 2021/7/28 19 03
 * discription
 */

import com.blog.dao.BlogDao;
import com.blog.dao.CommentDao;
import com.blog.pojo.Blog;
import com.blog.pojo.Comment;
import com.blog.service.CommentService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/28 19:03
 * @Version 1.0
 */

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        //没有父节点的默认为-1
        List<Comment> comments = commentDao.findByBlogIdAndParentCommentNull(blogId, Long.parseLong("-1"));
        return comments;
    }

    @Override
    //接收回复的表单
    public int saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();//父id
        if (parentCommentId != -1){
//            has父comment
            comment.setParentComment(commentDao.findByParentCommentId(comment.getParentCommentId()));
        }else{
//            no父comment
            comment.setParentCommentId((long) -1);
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());

        return commentDao.saveComment(comment);

    }
}
