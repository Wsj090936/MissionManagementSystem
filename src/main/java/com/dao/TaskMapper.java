package com.dao;

import com.pojo.Task;

public interface TaskMapper {

    Task getTaskById(Integer id);

    int insertTaskAccount(Task task);

}
