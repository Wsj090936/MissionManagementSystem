package com.interceptor;

import com.pojo.Teacher;
import com.utils.CookieUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 权限拦截器
 */
public class StudentInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        String contextPath = request.getContextPath();
        // 这里加个没权限的页面 是教师就让其过
        Integer type = (Integer) request.getSession().getAttribute("type");
        if(type == 2){//如果是教师 就通过
            return true;
        }
        //否则就是学生
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
