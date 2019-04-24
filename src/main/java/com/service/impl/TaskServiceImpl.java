package com.service.impl;


import com.dao.TaskMapper;
import com.pojo.Task;
import com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

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
}
