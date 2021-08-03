package com.blog.controller.admin;/**
 * @author Lu Jianqiang
 * @date 2021/7/29 18 51
 * discription
 */
import com.blog.pojo.Tag;
import com.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.DocFlavor;
import java.util.List;

/**
 * @ClassName TagController
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/29 18:51
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagService tagService;


    /**
     * @Description:获取全部标签
     * @Param: [pagenum, model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/tags")
    public String tags(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){

        PageHelper.startPage(pagenum,5);
        List<Tag> allTag = tagService.getAllTag();

        PageInfo pageInfo = new PageInfo(allTag);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    /**
     * @Description: 跳转到添加标签界面
     * @Param: [model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/tags/input")
    public String toAddTag(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    /**
     * @Description: 跳转到标签编辑界面
     * @Param: [id, model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/tags/{id}/input")
    public String toEditTag(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    /**
     * @Description: 添加标签
     * @Param: [tag, attributes]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @PostMapping("/tags")
    public String addTag(Tag tag, RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        if (t != null){
            attributes.addFlashAttribute("msg","不能添加重复标签");
            return "redirect:/admin/tags/input";
        }else{
            attributes.addFlashAttribute("msg","添加成功");
        }
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }
    /**
     * @Description: 修改标签
     * @Param: [id, tag, attributes]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id,Tag tag,RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        if (t != null){
            attributes.addFlashAttribute("msg","不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }else{
            attributes.addFlashAttribute("msg","修改成功");
        }
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }
    /**
     * @Description: 删除标签
     * @Param: [id, attributes]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("msg","删除成功");
        return "redirect:/admin/tags";
    }

}
