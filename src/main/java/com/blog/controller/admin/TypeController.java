package com.blog.controller.admin;/**
 * @author Lu Jianqiang
 * @date 2021/7/29 19 39
 * discription
 */

import com.blog.pojo.Type;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @ClassName TypeController
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/29 19:39
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;
    /**
     * @Description: 获取所有分类
     * @Param: [pagenum, model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum, 5);
        List<Type> allType = typeService.getAllType();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    /**
     * @Description: 跳转至添加分类页面
     * @Param: [model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/types/input")
    public String toAddType(Model model){
        model.addAttribute("type", new Type());   //返回一个type对象给前端th:object
        return "admin/types-input";
    }

    /**
     * @Description: 跳转至编辑分类页面
     * @Param: [id, model]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * @Description: 新增分类
     * @Param: [type, attributes]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes attributes){   //新增
        Type t = typeService.getTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        typeService.saveType(type);
        return "redirect:/admin/types";   //不能直接跳转到types页面，否则不会显示type数据(没经过types方法)
    }

    /**
     * @Description: 修改分类
     * @Param: [id, type, attributes]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id, Type type, RedirectAttributes attributes){  //修改
        Type t = typeService.getTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }else {
            attributes.addFlashAttribute("msg", "修改成功");
        }
        typeService.updateType(type);
        return "redirect:/admin/types";   //不能直接跳转到types页面，否则不会显示type数据(没经过types方法)
    }

    /**
     * @Description: 删除分类
     * @Param: [id, attributes]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/types";
    }
}
