package com.blog.controller;/**
 * @author Lu Jianqiang
 * @date 2021/7/29 21 06
 * discription
 */

import com.blog.pojo.Blog;
import com.blog.pojo.Type;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
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
 * @ClassName TypeShowController
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/29 21:06
 * @Version 1.0
 */

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id,
                        @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pagenum,
                        Model model){
        PageHelper.startPage(pagenum,100);
        List<Type> types = typeService.getBlogType();
        if (id == -1){
            id = types.get(0).getId();
        }
        List<Blog> blogs = blogService.getByTypeId(id);
        PageInfo pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);

        return "types";
    }

}
