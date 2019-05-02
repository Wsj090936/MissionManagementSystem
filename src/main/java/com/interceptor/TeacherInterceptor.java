package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 权限拦截器 被该拦截器拦截到的只有学生才能通过
 */
public class TeacherInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        String contextPath = request.getContextPath();
        // 这里加个没权限的页面
        //如果是学生就让其通过
        Integer type = (Integer) request.getSession().getAttribute("type");
        if(type == 1){//如果是学生 就通过
            return true;
        }
        // 否则就是教师
        response.sendRedirect("/login/permissionPage");

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
