package com.service.impl;


import com.dao.TaskMapper;
import com.pojo.Student;
import com.pojo.Task;
import com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    StudentInformationServiceImpl studentInformationService;

    @Override
    public Task getTaskById(Integer id) {

        Task task = taskMapper.getTaskById(id);

        return task;
    }

    @Override
    public boolean insertTask(Task task) {

        //这里要进行对象非空的判定

        boolean res = false;

        if(task != null){
            int i = taskMapper.insertTaskAccount(task);

            res = i == 1 ? true : false;

        }

        return res;
    }

    @Override
    public List<Task> getTaskList(String studentId) {

        Student student = studentInformationService.selectStudentById(studentId);
        if(student != null){
            List<Task> taskList = taskMapper.getTaskList(student.getClassId());
            return taskList;
        }

        return new ArrayList<>();
    }
}
