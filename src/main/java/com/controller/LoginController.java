package com.controller;


import com.dao.StudentMapper;
import com.pojo.Student;
import com.pojo.Teacher;
import com.resultResponse.ResultMap;
import com.service.LoginService;
import com.service.StudentInformationService;
import com.service.TeacherService;
import com.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 登陆用的Controller
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    StudentInformationService studentInformationService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping("/loginpage")
    public String getPage(){
        return "login";
    }
    //学生登陆
    @RequestMapping("/studentLogin")
    public String studentLogin(HttpServletRequest request, HttpServletResponse response, Long userName, String password){

        try {
            boolean res = loginService.studentLogin(userName, password, request, response);

            Student student = studentInformationService.selectStudentById(userName);
            request.getSession().setAttribute("studentId",userName);
            request.getSession().setAttribute("user",student);
            request.getSession().setAttribute("type",1);

            String uuid = UUID.randomUUID().toString();
            request.getSession().setAttribute("token",uuid);// 设置token


            CookieUtils.setCookie(request,response,"token",uuid);
            if(res){
                return "main";//成功后直接跳转
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "error";
    }

    @RequestMapping("/teacherLoginPage")
    public String teacherLoginPage(){
        return "tea_login";
    }

    @RequestMapping("/studentLoginPage")
    public String studentLoginPage(){
        return "login";
    }

    //教师登陆
    @RequestMapping("/teacherLogin")
    public String teacherLogin(HttpServletRequest request, HttpServletResponse response, Long userName, String password){

        try{
            boolean res = loginService.teacherLogin(userName,password,request,response);
            Teacher teacher = teacherService.getTeacherByTeacherId(userName);
            String token = UUID.randomUUID().toString();
            request.getSession().setAttribute("token",token);
            request.getSession().setAttribute("user",teacher);
            request.getSession().setAttribute("type",2);

            CookieUtils.setCookie(request,response,"token",token);
            request.getSession().setAttribute("teacherId",userName);

            if(res){

                return "main";//成功后直接跳转
            }

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }

        return "error";
    }
    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session != null){// 注销
            session.invalidate();
        }
        return "redirect:/login/loginpage";// 完后重定向到登陆界面
    }
    @RequestMapping("/permissionPage")
    public String toPermissionPage(){
        return "permission";
    }
}
