package com.controller;


import com.pojo.Student;
import com.service.HomeWorkAccountService;
import com.service.StudentInformationService;
import com.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/uploadExcel")// 上传学生名单  创建班集体
    public String getExcel(HttpServletRequest request, HttpServletResponse reponse,MultipartFile file){

        List<Student> list = ExcelUtils.getListByExcelFile(file);
        //先进行验证

        for (Student stu : list) {
            studentInformationService.insertStudent(stu);
        }
        return "success";
    }


}
