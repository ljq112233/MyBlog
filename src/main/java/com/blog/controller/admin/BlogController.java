package com.blog.controller.admin;/**
 * @author Lu Jianqiang
 * @date 2021/7/28 16 57
 * discription
 */

import com.blog.pojo.Blog;
import com.blog.pojo.User;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName BlogController
 * @Description blog
 * @Author Lu Jianqiang
 * @Date 2021/7/28 16:57
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
    }
    /**
     * @Description: 后台显示博客列表
     * @Param: [pagenum, model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,Model model){
        PageHelper.startPage(pagenum,5);
        List<Blog> allBlog = blogService.getAllBlog();
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo",pageInfo);
        //查询类型和标签
        setTypeAndTag(model);
        return "admin/blogs";
    }
    /**
     * @Description: 按条件查询博客
     * @Param: [blog, pagenum, model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @PostMapping("/blogs/search")
    public String search(@RequestParam String query,@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,Model model){
        PageHelper.startPage(pagenum,5);
        List<Blog> blogs = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("message","查询成功");
        setTypeAndTag(model);
        return "admin/blogs";
    }
    /**
     * @Description: 新增博客页面
     * @Param: [model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */

    @GetMapping("/blogs/input") //去新增博客页面
    public String toAddBlog(Model model){
        model.addAttribute("blog", new Blog());  //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    /**
     * @Description: 编辑博客页面
     * @Param: [id, model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/blogs/{id}/input")
    public String toEditBlog(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return "admin/blogs-input";
    }
    /**
     * @Description:新增、编辑博客
     * @Param: [blog, session, attributes]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @PostMapping("/blogs")
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes) {
//        设置user
        blog.setUser((User) session.getAttribute("user"));
//        设置用户id
        blog.setUserId(blog.getUser().getId());
//        设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //给blog中的List<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));

        if (blog.getId() == null) {
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }
        attributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/admin/blogs";
    }
    /**
     * @Description: 删除博客
     * @Param: [id, attributes]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/blogs/{id}/delete")
    public String deletBlogs(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg","删除成功");
        return "redirect:/admin/blogs";
    }
}
