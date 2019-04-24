package com.dao;

import com.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int insertStudent(Student student);
    int updateClassId(@Param("classId") String classId, @Param("studentIdList") List<String> studentIdList);
    Student selectStudentById(String StudentId);

    List<Student> getStudentList(String classId);


}
