package com.dao;

import com.pojo.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {

    Task getTaskById(Integer id);

    int insertTaskAccount(Task task);

    List<Task> getTaskList(@Param("classId") Long classId);

    List<Task> getTaskListByTeacherId(@Param("teacherId") Long teacherId);

}
