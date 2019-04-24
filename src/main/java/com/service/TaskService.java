package com.service;

import com.pojo.Task;

public interface TaskService {

    Task getTaskById(Integer id);

    boolean insertTask(Task task);

}
