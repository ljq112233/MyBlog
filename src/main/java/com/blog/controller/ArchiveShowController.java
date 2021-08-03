package com.blog.controller;/**
 * @author Lu Jianqiang
 * @date 2021/7/29 20 31
 * discription
 */

import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName ArchiveShowController
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/29 20:31
 * @Version 1.0
 */

@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }
}
