package com.service.impl;


import com.dao.HomeWorkAccountMapper;
import com.dao.StudentMapper;
import com.pojo.HomeWorkAccount;
import com.pojo.Student;
import com.pojo.Task;
import com.service.HomeWorkAccountService;
import com.service.TaskService;
import com.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作作业记录表的Service
 */
@Service
public class HomeWorkAccountServiceImpl implements HomeWorkAccountService {

    @Autowired
    HomeWorkAccountMapper homeWorkAccountMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TaskService taskService;

    @Override
    public boolean insertAccount(String studentId,Integer taskId,String workUrl) {
        //先根据studentId查询到上传作业的学生信息
        //Student student = studentMapper.selectStudentById(studentId);
        //然后插入记录
        HomeWorkAccount homeWorkAccount = new HomeWorkAccount();
        homeWorkAccount.setStudentId(studentId);
        homeWorkAccount.setWorkUrl(workUrl);
        //这里要加一个任务创建的时间，应该从任务流水表中取
        Task taskById = taskService.getTaskById(taskId);
        homeWorkAccount.setCreated(taskById.getCreated());
        homeWorkAccount.setUpdated(DateUtils.getNowDate());

        int col = homeWorkAccountMapper.insertAccount(homeWorkAccount);

        return col == 1;

    }
}
