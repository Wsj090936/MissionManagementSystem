package com.controller;


import com.pojo.HomeWorkAccount;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
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
    public String getTaskList(Model model,Long userId){

            List<Task> teacherTaskList = null;
            if(userId != null){
                teacherTaskList = taskService.getTeacherTaskList(userId);
            }
            model.addAttribute("taskList",teacherTaskList);
            return "tea_task_list";


    }

    @RequestMapping("/taskDetail")// 这里改为教师的接口
    public String taskDetail(Model model,Integer id){
        if(id != null){
            Task task = taskService.getTaskById(id);
            model.addAttribute("task",task);
            return "tea_task_info";

        }
        return "error";
    }


    /**F
     * 教师下载作业的接口
     */
    @RequestMapping("/downloadHomeWork")
    public String downloadHomeWork(HttpServletResponse response,HttpServletRequest request,Integer taskId,Long studentId){

        HomeWorkAccount account = homeWorkAccountService.getAccount(taskId, studentId);
        String filePath = null;
        if(account != null){
            filePath = account.getWorkUrl();
        }
        if(filePath == null){// 没有附件
            return "";
        }
        return FileUtils.downloadFile(filePath,response,request);
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
            String url = FileUtils.uploadFile(rootPath, file);
            task.setFileUrl(url);// 设置文件路径
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

    /**
     * 修改任务信息
     * @param task
     * @return
     */
    @RequestMapping("/editTask")
    public String editTask(Task task, MultipartFile file, Model model,HttpServletRequest request){
        boolean res = false;
        if(task != null){
            if(file != null && file.getSize() > 0){//看是否有必要更新文件地址
                String rootPath = "G:\\UploadFiles\\taskFiles";
                String url = FileUtils.uploadFile(rootPath, file);
                if(!url.equals("error")){
                    task.setFileUrl(url);
                }
            }
            res = taskService.editTaskInfo(task);
        }
        return "redirect:/task/getTaskList?userId="+request.getSession().getAttribute("teacherId");
    }

}
