package com.service.impl;

import com.pojo.Student;
import com.pojo.Teacher;
import com.resultResponse.ResultMap;
import com.service.LoginService;
import com.service.StudentInformationService;
import com.service.TeacherService;
import com.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    StudentInformationService studentInformationService;

    @Autowired
    TeacherService teacherService;

    @Override
    public boolean studentLogin(Long userName, String password, HttpServletRequest request, HttpServletResponse response) {
        //首先校验用户名和密码
        //校验成功后将用户信息存入Session/cookie中，完成后返回成功的状态吗
        Student student = studentInformationService.selectStudentById(userName);
        if(password.equals(student.getPassword())){//证明验证成功
            request.getSession().setAttribute(SessionUtils.SESSION_STUDENT,student);//将学生信息放入Session
            return true;
        }
        return false;
    }

    @Override
    public boolean teacherLogin(Long userName, String password, HttpServletRequest request, HttpServletResponse response) {
        Teacher teacher = teacherService.getTeacherByTeacherId(userName);

        if(password.equals(teacher.getPassword())){
            request.getSession().setAttribute(SessionUtils.SESSION_TEACHER,teacher);
            return true;
        }

        return false;
    }
}
