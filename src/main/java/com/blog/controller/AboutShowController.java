package com.blog.controller;/**
 * @author Lu Jianqiang
 * @date 2021/7/28 21 53
 * discription
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName AboutShowController
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/28 21:53
 * @Version 1.0
 */

@Controller
public class AboutShowController {
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}
