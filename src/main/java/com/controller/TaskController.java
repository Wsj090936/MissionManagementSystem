package com.controller;


import com.pojo.Task;
import com.service.TaskService;
import com.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 发布、删除任务的Controller
 * 主要由教师来使用 所以要加个权限
 */

@Controller
@RequestMapping("/task")
public class TaskController {


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
        task.setClassId("04041503");
        task.setDetail("尽快提交");
        task.setTeacherId("9527");
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


}
