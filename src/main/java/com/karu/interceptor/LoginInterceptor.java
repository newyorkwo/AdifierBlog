package com.karu.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: create interceptor
 * @author: StevenWu
 * @create: 2019-05-15 16:24
 **/

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user")== null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
