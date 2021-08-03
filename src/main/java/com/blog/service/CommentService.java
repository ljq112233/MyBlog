package com.blog.service;

import com.blog.pojo.Comment;

import java.util.List;

/**
 * @author Lu Jianqiang
 * @date 2021/7/28 19 03
 * discription
 */
public interface CommentService {

    List<Comment> getCommentByBlogId(Long blogId);

    int saveComment(Comment comment);
}
