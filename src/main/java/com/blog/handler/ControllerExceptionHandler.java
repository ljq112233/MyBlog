package com.blog.handler;
/**
 * @author Lu Jianqiang
 * @date 2021/7/29 15 01
 * discription
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ControllerExceptionHandler
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/29 15:01
 * @Version 1.0
 */

//拦截所有controller抛出的异常，对异常进行统一的处理
    @ControllerAdvice
public class ControllerExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());
        @ExceptionHandler(Exception.class)
        /**
         * @Description:异常统一处理,catch住所有service层、dao层等异常
         * @Param: [request, e]
         * @return: org.springframework.web.servlet.ModelAndView
         * @Author: Lu Jianqiang
         * @Date: 2021/7/29
         */
        public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {


            //日志打印异常信息
            logger.error("请求:" + request.getRequestURI() + "异常: ", e);

            //不处理带有ResponseStatus注解的异常
            if (AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class) != null){
                throw e;
            }

            ModelAndView mv = new ModelAndView();
            mv.addObject("url",request.getRequestURI());
            mv.addObject("Exception",e);
            mv.setViewName("error/error");

            return mv;
        }
}
