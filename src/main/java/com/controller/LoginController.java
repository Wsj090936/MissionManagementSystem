package com.controller;


import com.dao.StudentMapper;
import com.resultResponse.ResultMap;
import com.service.LoginService;
import com.service.StudentInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆用的Controller
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;


    @RequestMapping("/loginpage")
    public String getPage(){
        return "login";
    }
    //学生登陆
    @RequestMapping("/studentLogin")
    public String studentLogin(HttpServletRequest request, HttpServletResponse response, String userName, String password){

        try {
            boolean res = loginService.studentLogin(userName, password, request, response);
            request.getSession().setAttribute("studentId",userName);
            if(res){
                return "main";//成功后直接跳转
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "error";
    }



    //教师登陆
    @RequestMapping("/teacherLogin")
    public String teacherLogin(HttpServletRequest request, HttpServletResponse response, String userName, String password){

        try{
            boolean res = loginService.teacherLogin(userName,password,request,response);
            if(res){
                return "main";//成功后直接跳转
            }

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }

        return "error";    }
}
