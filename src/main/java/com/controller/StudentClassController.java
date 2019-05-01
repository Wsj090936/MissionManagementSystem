package com.controller;


import com.dao.TeacherClassMapper;
import com.dao.TeacherMapper;
import com.pojo.Student;
import com.pojo.TeacherClass;
import com.resultResponse.ResultMap;
import com.service.StudentInformationService;
import com.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    TeacherService teacherService;

    @Autowired
    TeacherClassMapper teacherClassMapper;

    @RequestMapping("/createClass")
    public ResultMap createClass(String classId){

        return null;
    }



    @RequestMapping("/getStudentList")//查询学生列表，对应页面中学生服务中的我的班级
    public String getStudentList(Model model,Long studentId){
        if(studentId != null){
                List<Student> studentList = studentInformationService.getStudentList(studentId);
                model.addAttribute("stuList",studentList);
                return "stu_class_info";


        }
        return "error";
    }

    @RequestMapping("/teacherClass")
    public String getStudentListByClassId(Model model,Long teacherId){
        if(teacherId != null){
            List<TeacherClass> account = teacherClassMapper.getAccountByTeacherId(teacherId);
            model.addAttribute("accountList",account);

            return "tea_class";
        }

        return "error";
    }
    @RequestMapping("/classDetail")
    public String classDetail(Model model,Long classId){
        if(classId != null){
            List<Student> studentList = studentInformationService.getStudentListByClassId(classId);
            model.addAttribute("stuList",studentList);
            return "tea_class_manage";
        }
        return "error";
    }


    @RequestMapping("/studentClassInformation")
    public String getClassInformation(Model model,Long studentId){
        if(studentId != null){
            Student student = studentInformationService.selectStudentById(studentId);
            int studnetCount = studentInformationService.getStudnetCount(student.getClassId());
            model.addAttribute("stu",student);
            model.addAttribute("stuCount",studnetCount);
            return "stu_class";
        }
        return "error";
    }

    @RequestMapping("/editStudent")
    public String editStudent(Model model,Long studentId){
        if(studentId != null){
            Student student = studentInformationService.selectStudentById(studentId);
            model.addAttribute("stu",student);
            return "tea_class_stuinfo";
        }
        return "error";

    }
    @RequestMapping("/updateStudent")
    public String updateStidemt(Model model,Student student){
        boolean res = studentInformationService.updateStudent(student);
        if(res){
            model.addAttribute("success",true);
            return "main";
        }
        return "error";
    }

}
