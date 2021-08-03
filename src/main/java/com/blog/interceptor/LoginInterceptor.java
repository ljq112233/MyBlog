package com.blog.interceptor;/**
 * @author Lu Jianqiang
 * @date 2021/7/25 20 17
 * discription
 */

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Description session登录拦截器
 * @Author Lu Jianqiang
 * @Date 2021/7/25 20:17
 * @Version 1.0
 */

//登录拦截器
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null){//利用session验证user身份
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
