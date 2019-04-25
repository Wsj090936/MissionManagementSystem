package com.service;

import com.pojo.Task;

import java.util.List;

public interface TaskService {

    Task getTaskById(Integer id);

    boolean insertTask(Task task);

    List<Task> getTaskList(String studentId);

}
