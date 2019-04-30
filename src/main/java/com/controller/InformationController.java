package com.controller;


import com.pojo.Student;
import com.service.StudentInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    StudentInformationService studentInformationService;

    @RequestMapping("/studentInformation")
    public String getStudentInfo(Model model,Long studentId){
        if(studentId != null){
            Student student = studentInformationService.selectStudentById(studentId);
            model.addAttribute("stu",student);
            return "stu_info";
        }
        return "error";
    }


}
