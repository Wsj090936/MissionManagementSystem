package com.service.impl;

import com.dao.TeacherMapper;
import com.pojo.Teacher;
import com.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeaherServiceImpl implements TeacherService {


    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherByTeacherId(String teacherId) {

        Teacher teacher = teacherMapper.getTeacherById(teacherId);
        if(teacher == null){
            teacher = new Teacher();
        }

        return teacher;
    }
}
