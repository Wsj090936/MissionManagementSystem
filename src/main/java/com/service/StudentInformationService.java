package com.service;

import com.pojo.Student;

import java.util.List;

/**
 * 用于修改学生信息的Service
 */
public interface StudentInformationService {
    boolean insertStudent(Student student);// 插入学生

    Student selectStudentById(String studentId);

    List<Student> getStudentList(String studentId);

}
