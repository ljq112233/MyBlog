package com.blog.controller;/**
 * @author Lu Jianqiang
 * @date 2021/7/29 21 13
 * discription
 */

import com.blog.pojo.Blog;
import com.blog.pojo.Tag;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName TagShowConotroller
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/29 21:13
 * @Version 1.0
 */

@Controller
public class TagShowConotroller {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String Tags(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pagenum,
                       Model model){
        PageHelper.startPage(pagenum,100);
        List<Tag> tags = tagService.getBlogTag();
        if (id == -1){
            id = tags.get(0).getId();
        }
        List<Blog> blogs = blogService.getByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("tags",tags);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
