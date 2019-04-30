package com.controller;


import com.pojo.Task;
import com.pojo.dto.HomeWorkAccountDto;
import com.pojo.dto.TaskDto;
import com.service.HomeWorkAccountService;
import com.service.TaskService;
import com.utils.DateUtils;
import com.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 发布、删除任务的Controller
 * 主要由教师来使用 所以要加个权限
 */

@Controller
@RequestMapping("/task")
public class TaskController {


    @Autowired
    HomeWorkAccountService homeWorkAccountService;

    @Autowired
    TaskService taskService;


    /**
     * 分班级发布任务
     * @param classes
     * @return
     */
    @RequestMapping("/madeTask")
    public String madeTaskByTeacher( String[] classes){
        //根据List中的班级Id来进行分班级发布任务
        //从session中取到TeacherId
        Task task = new Task();
        task.setClassId(04041503L);
        task.setDetail("尽快提交");
        task.setTeacherId(9527L);
        task.setTitle("第二次作业");

        String nowTime = DateUtils.getNowDate();
        task.setCreated(nowTime);
        task.setUpdated(nowTime);
        for (String classId : classes) {
            boolean b = taskService.insertTask(task);
            if(b){
                continue;
            }else {
                return "error";
            }
        }

        return "success";
    }


    @RequestMapping("/getTaskList")
    public String getTaskList(Model model,Long userId,Integer type){
        if(type == 1){// 学生的任务列表
            if(userId != null){
                List<TaskDto> taskDtoList = taskService.getTaskList(userId);
                model.addAttribute("taskDtoList",taskDtoList);
                return "stu_task_list";
            }
        }else if(type == 2){// 教师发布的任务列表
            List<Task> teacherTaskList = null;
            if(userId != null){
                teacherTaskList = taskService.getTeacherTaskList(userId);
            }
            model.addAttribute("taskList",teacherTaskList);
            return "tea_task_list";
        }

        return "error";
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

    /**
     * 学生上传作业的接口
     * @param file
     * @return
     */
    @RequestMapping("/uploadHomeWork")
    public String uploadHomeWork(Model model,MultipartFile file, Integer taskId,HttpServletRequest request, HttpServletResponse response) throws  Exception{
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
    /**F
     * 教师下载作业的接口
     */
    @RequestMapping("/downloadHomeWork")
    public String downloadHomeWork(HttpServletRequest request){
        return "";
    }

    /**
     * 学生下载任务附件
     * @return
     */
    @RequestMapping("/downloadTaskFile")
    public String downloadTaskFile(HttpServletRequest request,HttpServletResponse response,Integer id,Model model){
        Task taskById = taskService.getTaskById(id);
        String fileUrl = taskById.getFileUrl();
        if(fileUrl.equals("") || fileUrl == null){// 说明没有附件
            model.addAttribute("haveFile",false);
            return "stu_task_list";
        }
        model.addAttribute("haveFile",true);
        return FileUtils.downloadFile(fileUrl,response,request);
    }

    @RequestMapping("/toAddTask")
    public String toAddTask(){
        return "task_add";
    }


    @RequestMapping("/addTask")
    public String addTask(HttpServletRequest request,MultipartFile file,Task task,Model model){
        Long teacherId = (Long) request.getSession().getAttribute("teacherId");
        if(teacherId == null){
            return "error";
        }
        task.setTeacherId(teacherId);
        String nowTime = DateUtils.getNowDate();
        task.setCreated(nowTime);
        task.setUpdated(nowTime);
        boolean res = false;

        if(file != null && file.getSize() > 0){// 如果上传了文件 就到文件的地址
            String rootPath = "G:\\UploadFiles\\taskFiles";
            String filePath = rootPath + "\\";
            File dir = new File(filePath);
            if(!dir.exists()){
                dir.mkdir();
            }
            String originalFilename = file.getOriginalFilename();
            String newFileName = originalFilename;
            File writeFile = new File(filePath,newFileName);//指定路径和名称
            //写入磁盘
            try {
                file.transferTo(writeFile);
                task.setFileUrl(filePath+newFileName);// 设置文件路径

            }catch (Exception e){
                e.printStackTrace();
                return "error";
            }

        }
        //完后写入数据库
        res = taskService.insertTask(task);// 更新任务表
        if(res){
            model.addAttribute("isSuccess",true);
            return "task_add";
        }else{
            return "error";
        }
    }

    /**
     * 根据任务Id查询相关班级，查询班级中各个成员上传任务的情况
     * @param id
     * @return
     */
    @RequestMapping("/taskFinishDetail")
    public String taskFinishDetail(Integer id, Model model){
        if(id == null){
            return "error";
        }
        List<HomeWorkAccountDto> homeWorkAccountDtos = homeWorkAccountService.getuploadDetailListByTaskId(id);
        model.addAttribute("homeWorkAccountDtos",homeWorkAccountDtos);
        return "work_finish";
    }

}
