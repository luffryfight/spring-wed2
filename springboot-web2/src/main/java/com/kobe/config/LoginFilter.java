package com.kobe.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作用：
 * 2020/11/12
 */

public class LoginFilter implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        Object teacher = request.getSession().getAttribute("Teacher");
        if (teacher==null){
            request.setAttribute("msg","用户未登录，请先登录！");
            request.getRequestDispatcher("/index.html").forward(request,response);
            //request.getRequestDispatcher("/need_login.do").forward(request, response);
            return false;
        }
        return true;
    }
}
