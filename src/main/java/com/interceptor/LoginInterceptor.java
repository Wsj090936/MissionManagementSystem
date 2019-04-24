package com.interceptor;

import com.pojo.Student;
import com.pojo.Teacher;
import com.utils.SessionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登陆用的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Teacher teacher = (Teacher) request.getSession().getAttribute(SessionUtils.SESSION_TEACHER);
        Student student = (Student) request.getSession().getAttribute(SessionUtils.SESSION_STUDENT);
        if(teacher == null && student == null){
            response.sendRedirect("/test");
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
