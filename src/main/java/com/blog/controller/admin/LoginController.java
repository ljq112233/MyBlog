package com.blog.controller.admin;/**
 * @author Lu Jianqiang
 * @date 2021/7/25 20 34
 * discription
 */

import com.blog.pojo.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/25 20:34
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")

public class LoginController {
    @Autowired
    private UserService userService;
    /**
     * @Description:
     * @Param: []
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/25
     */
    @GetMapping()
    public String loginPage(){
        return "admin/login";
    }
    /**
     * @Description:
     * @Param: [username, password, session, attributes]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/25
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("msg","用户名或密码错误");
            return "redirect:/admin";
        }
    }
    /**
     * @Description: 注销
     * @Param: [session]
     * @return: java.lang.String
     * @Author: Lu Jianqiang
     * @Date: 2021/7/29
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
