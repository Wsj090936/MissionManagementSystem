package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.StudentMapper;
import com.pojo.Student;
import com.service.StudentInformationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentInformationServiceImpl implements StudentInformationService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public boolean insertStudent(Student student) {
        int isSuccess = studentMapper.insertStudent(student);
        if(isSuccess > 0){
            return true;
        }
        return false;
    }

    @Override
    public Student selectStudentById(String studentId) {

        Student student = studentMapper.selectStudentById(studentId);

        return student;
    }

    @Override
    public List<Student> getStudentList(String studentId) {

        Student student = selectStudentById(studentId);
        List<Student> studentList = new ArrayList<>();
        if(student != null){
            studentList = studentMapper.getStudentList(student.getClassId());
        }
        return studentList;
    }
}
