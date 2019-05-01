package com.service.impl;


import com.dao.HomeWorkAccountMapper;
import com.dao.StudentMapper;
import com.pojo.HomeWorkAccount;
import com.pojo.Student;
import com.pojo.Task;
import com.pojo.dto.HomeWorkAccountDto;
import com.service.HomeWorkAccountService;
import com.service.TaskService;
import com.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public HomeWorkAccount getAccount(Integer taskId, Long studentId) {
        HomeWorkAccount accountByTaskIdAndStudentId = homeWorkAccountMapper.getAccountByTaskIdAndStudentId(taskId, studentId);
        return accountByTaskIdAndStudentId;
    }

    @Override
    public boolean insertAccount(Long studentId,Integer taskId,String workUrl) {
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
        homeWorkAccount.setTaskId(taskId);

        int col = homeWorkAccountMapper.insertAccount(homeWorkAccount);

        return col == 1;

    }

    /**
     * 这里要将已经上传了作业的学生和没有上传作业的学生标识一下
     * @param taskId
     * @return
     */
    @Override
    public List<HomeWorkAccountDto> getuploadDetailListByTaskId(Integer taskId) {

        List<HomeWorkAccountDto> res = new ArrayList<>();
        Task task = taskService.getTaskById(taskId);
        long classId = task.getClassId();// 取到classId后可以查询到这个班级中的所有学生 然后根据查出的上传作业的情况 就可以知道谁提交了，谁没提交
        List<Student> studentList = studentMapper.getStudentList(classId);

        List<HomeWorkAccount> homeWorkAccounts = homeWorkAccountMapper.selectAccountByTaskId(taskId);
        Set<Long> stuIdSet = new HashSet<>();
        for (HomeWorkAccount account : homeWorkAccounts) {// 将上传了文件的学生的Id全部存入HashSet中
            stuIdSet.add(account.getStudentId());
        }
        //开始判断学生是否已经提交  1-已提交 2-未提交
        long stuId;
        HomeWorkAccountDto dto = null;
        for (Student stu : studentList) {
            dto = new HomeWorkAccountDto();
            stuId = stu.getStudentId();
            if(stuIdSet.contains(stuId)){// 如果存在，说明已经上传
                dto.setIsUpload(1);
            }else{// 不存在
                dto.setIsUpload(2);
            }
            // 封装其余信息
            dto.setClassId(classId);
            dto.setName(stu.getName());
            dto.setStudentId(stuId);
            dto.setTaskId(taskId);
            res.add(dto);
        }

        return res;
    }
}
