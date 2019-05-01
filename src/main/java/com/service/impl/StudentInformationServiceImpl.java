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
    public boolean insertStudent(Student student) {// 这里要进行学生是否重复的判定

        Student stu = studentMapper.selectStudentById(student.getStudentId());
        if(stu == null){// 表中无此学生才插入
            int isSuccess = studentMapper.insertStudent(student);
            if(isSuccess > 0){
                return true;
            }
        }


        return false;
    }

    @Override
    public Student selectStudentById(Long studentId) {

        Student student = studentMapper.selectStudentById(studentId);

        return student;
    }

    @Override
    public List<Student> getStudentList(Long studentId) {

        Student student = selectStudentById(studentId);
        List<Student> studentList = new ArrayList<>();
        if(student != null){
            studentList = studentMapper.getStudentList(student.getClassId());
        }
        return studentList;
    }

    @Override
    public List<Student> getStudentListByClassId(Long classId) {

        if(classId != null){
            List<Student> list  = studentMapper.getStudentList(classId);
            return list;
        }

        return new ArrayList<>();
    }

    @Override
    public int getStudnetCount(Long classId) {

        int studentCount = studentMapper.getStudentCount(classId);

        return studentCount;
    }

    @Override
    public boolean updateStudent(Student student) {
        if(student != null){
            int i = studentMapper.updateStudent(student);
            return i == 1;
        }
        return false;
    }
}
