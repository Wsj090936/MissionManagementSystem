package com.controller;


import com.pojo.Student;
import com.resultResponse.ResultMap;
import com.service.StudentInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * created by wushijia
 * 学生班级相关的Coltroller
 */
@Controller
@RequestMapping("/class")
public class StudentClassController {

    @Autowired
    StudentInformationService studentInformationService;


    @RequestMapping("/createClass")
    public ResultMap createClass(String classId){

        return null;
    }



    @RequestMapping("/getStudentList")//查询学生列表，对应页面中学生服务中的我的班级
    public String getStudentList(Model model,String studentId){
        if(studentId != null){
            List<Student> studentList = studentInformationService.getStudentList(studentId);
            model.addAttribute("stuList",studentList);
            return "stu_class_info";
        }
        return "error";
    }

    @RequestMapping("/studentClassInformation")
    public String getClassInformation(Model model,String studentId){
        if(studentId != null){
            Student student = studentInformationService.selectStudentById(studentId);
            int studnetCount = studentInformationService.getStudnetCount(student.getClassId());
            model.addAttribute("stu",student);
            model.addAttribute("stuCount",studnetCount);
            return "stu_class";
        }
        return "error";
    }
}
