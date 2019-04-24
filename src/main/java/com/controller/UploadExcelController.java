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

    /**
     * 学生上传作业的接口
     * @param file
     * @return
     */
    @RequestMapping("/uploadHomeWork")
    public String uploadHomeWork(MultipartFile file,String studentId,Integer taskId){
        String rootPath = "G:\\UploadFiles";
        String filePath = rootPath + "\\";
        File dir = new File(filePath);
        if(!dir.exists()){
            dir.mkdir();
        }
        String originalFilename = file.getOriginalFilename();
        String newFileName = originalFilename;
        File writeFile = new File(filePath,newFileName);//指定路径和名称
        //写入磁盘
        boolean res = false;
        try {
            file.transferTo(writeFile);

            //完后要将提交作业的记录写入数据库
            res = homeWorkAccountService.insertAccount(studentId, taskId, filePath+newFileName);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        if(res){
            return "success";
        }
        return "error";
    }
    /**F
     * 教师下载作业的接口
     */
    @RequestMapping("/downloadHomeWork")
    public String downloadHomeWork(HttpServletRequest request){
    return "";
    }

}
