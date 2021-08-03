package com.blog.dao;

import com.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lu Jianqiang
 * @date 2021/7/28 18 47
 * discription
 */

@Repository
@Mapper
public interface CommentDao {

    //根据创建时间倒序来排
    List<Comment> findByBlogIdAndParentCommentNull(@Param("blogId")Long blogId,@Param("blogParentId") Long blogParentId);

    //查询父级对象
    Comment findByParentCommentId(@Param("parentCommentId")Long parentCommentId);

    //添加一个评论
    int saveComment(Comment comment);
}
