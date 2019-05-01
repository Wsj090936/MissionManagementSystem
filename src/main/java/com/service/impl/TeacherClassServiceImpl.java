package com.service.impl;

import com.dao.TeacherClassMapper;
import com.pojo.TeacherClass;
import com.service.TeacherClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherClassServiceImpl implements TeacherClassService {

    @Autowired
    TeacherClassMapper teacherClassMapper;

    @Override
    public boolean updateAccount(TeacherClass teacherClass) {

        TeacherClass accountByPojo = teacherClassMapper.getAccountByPojo(teacherClass);
        if(accountByPojo != null){// 不为null说明已经添加过
            return false;
        }
        int num = teacherClassMapper.insertAccount(teacherClass);

        return num == 1 ;
    }
}
