package com.service;

import com.pojo.Task;
import com.pojo.dto.TaskDto;

import java.util.List;

public interface TaskService {

    Task getTaskById(Integer id);

    boolean insertTask(Task task);

    List<TaskDto> getTaskList(Long studentId);

    List<Task> getTeacherTaskList(Long teacherId);

}
