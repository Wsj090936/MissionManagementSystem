package com.controller;


import com.pojo.Student;
import com.pojo.Task;
import com.pojo.dto.TaskDto;
import com.service.HomeWorkAccountService;
import com.service.StudentInformationService;
import com.service.TaskService;
import com.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentInformationService studentInformationService;

    @Autowired
    HomeWorkAccountService homeWorkAccountService;

    @Autowired
    TaskService taskService;

    @RequestMapping("/studentInformation")
    public String getStudentInfo(Model model,Long studentId){
        if(studentId != null){
            Student student = studentInformationService.selectStudentById(studentId);
            model.addAttribute("stu",student);
            return "stu_info";
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
    @RequestMapping("/getTaskList")
    public String getTaskList(Model model,Long userId){
        // 学生的任务列表
            if(userId != null){
                List<TaskDto> taskDtoList = taskService.getTaskList(userId);
                model.addAttribute("taskDtoList",taskDtoList);
                return "stu_task_list";
            }

        return "error";
    }
    /**
     * 学生下载任务附件
     * @return
     */
    @RequestMapping("/downloadTaskFile")
    public String downloadTaskFile(HttpServletRequest request, HttpServletResponse response, Integer id, Model model){
        Task taskById = taskService.getTaskById(id);
        String fileUrl = taskById.getFileUrl();
        if(fileUrl.equals("") || fileUrl == null){// 说明没有附件
            model.addAttribute("haveFile",false);
            return "stu_task_list";
        }
        model.addAttribute("haveFile",true);
        return FileUtils.downloadFile(fileUrl,response,request);
    }

    @RequestMapping("/taskDetail")
    public String taskDetail(Model model,Integer id){
        if(id != null){
            Task task = taskService.getTaskById(id);
            model.addAttribute("task",task);
            return "stu_task_info";

        }
        return "error";
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
    /**
     * 学生上传作业的接口
     * @param file
     * @return
     */
    @RequestMapping("/uploadHomeWork")
    public String uploadHomeWork(Model model, MultipartFile file, Integer taskId, HttpServletRequest request, HttpServletResponse response) throws  Exception{
        Long studentId = (Long) request.getSession().getAttribute("studentId");
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
            model.addAttribute("isSuccess",true);
            return "stu_task_info";
        }
        model.addAttribute("isSuccess",false);
        return "stu_task_info";
    }
}
