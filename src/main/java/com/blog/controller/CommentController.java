package com.blog.controller;/**
 * @author Lu Jianqiang
 * @date 2021/7/29 20 04
 * discription
 */

import com.blog.pojo.Comment;
import com.blog.pojo.User;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.prefs.BackingStoreException;

/**
 * @ClassName CommentController
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/29 20:04
 * @Version 1.0
 */

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;


    /**
     * @Description: 展示留言
     * @Param: [blogId, model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.getCommentByBlogId(blogId));
        model.addAttribute("blog",blogService.getDetailedBlog(blogId));
        return "blog :: commentList";
    }


    /**
     * @Description: 提交评论
     * @Param: [comment, session]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getDetailedBlog(blogId));
        comment.setBlogId(blogId);
        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
        }
        System.out.println(comment);
        commentService.saveComment(comment);
        return "redirect:/comments/"+blogId;
    }




}
