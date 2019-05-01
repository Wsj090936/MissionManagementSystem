package com.controller;


import com.dao.TeacherClassMapper;
import com.pojo.Student;
import com.pojo.TeacherClass;
import com.service.HomeWorkAccountService;
import com.service.StudentInformationService;
import com.service.TeacherClassService;
import com.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;


/**
 * created by wushijia
 * 文件相关的Controller
 */
@Controller
@RequestMapping("/upload")
public class UploadExcelController {

    @Autowired
    StudentInformationService studentInformationService;

    @Autowired
    HomeWorkAccountService homeWorkAccountService;

    @Autowired
    TeacherClassService teacherClassService;

    @RequestMapping("/uploadExcel")// 上传学生名单  创建班集体
    public String getExcel(HttpServletRequest request, HttpServletResponse reponse, MultipartFile file, Model model){

        List<Student> list = ExcelUtils.getListByExcelFile(file);
        //先进行验证
        TeacherClass tc = new TeacherClass();
        Long teacherId = (Long) request.getSession().getAttribute("teacherId");
        Long classId = list.get(0).getClassId();
        tc.setClassId(classId);
        tc.setTeacherId(teacherId);
        for (Student stu : list) {

            boolean b = studentInformationService.insertStudent(stu);
        }
        // 完后更新教师-班级表
        boolean res = teacherClassService.updateAccount(tc);

        model.addAttribute("createClass",res);

        return "main";
    }


}
