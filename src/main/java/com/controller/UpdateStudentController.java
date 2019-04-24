package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.resultResponse.ResultMap;
import com.pojo.Student;
import com.service.StudentInformationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * created by wushijia
 * 修改学生信息相关的Controller
 */

@Controller
@RequestMapping("/student")
public class UpdateStudentController {

    @Autowired
    StudentInformationService studentInformationService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public ResultMap testMethod(HttpServletRequest request){

        return ResultMap.getMsg("1000","插入成功");
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public ResultMap updateStudentInformation(Student stu){


        boolean isSuccess = studentInformationService.insertStudent(stu);
        if(isSuccess){
            return ResultMap.getMsg("1000","插入成功");
        }
        return  ResultMap.getMsg("1001","插入失败");
    }


}
