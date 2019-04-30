package com.service.impl;


import com.dao.TaskMapper;
import com.dao.TeacherMapper;
import com.pojo.Student;
import com.pojo.Task;
import com.pojo.Teacher;
import com.pojo.dto.TaskDto;
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
    TeacherMapper teacherMapper;

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
    public List<TaskDto> getTaskList(Long studentId) {
        try {
            List<TaskDto> res = new ArrayList<>();
            Student student = studentInformationService.selectStudentById(studentId);
            Teacher teacher = null;
            TaskDto taskDto = null;
            if (student != null) {
                List<Task> taskList = taskMapper.getTaskList(student.getClassId());
                for (Task task : taskList) {
                    taskDto = new TaskDto();
                    teacher = teacherMapper.getTeacherById(task.getTeacherId());//从数据库查到教师信息
                    taskDto.setClassId(task.getClassId());
                    taskDto.setId(task.getId());
                    taskDto.setTeacherId(task.getTeacherId());
                    taskDto.setTitle(task.getTitle());
                    taskDto.setTeacherName(teacher.getName());
                    taskDto.setCreated(task.getCreated());
                    res.add(taskDto);// 信息储存完后存入List
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Task> getTeacherTaskList(Long teacherId) {

        List<Task> taskListByTeacherId = taskMapper.getTaskListByTeacherId(teacherId);

        return taskListByTeacherId;
    }
}
