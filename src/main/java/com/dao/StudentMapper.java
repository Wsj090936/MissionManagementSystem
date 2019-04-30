package com.dao;

import com.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int insertStudent(Student student);
    int updateClassId(@Param("classId") Long classId, @Param("studentIdList") List<Long> studentIdList);
    Student selectStudentById(Long StudentId);

    List<Student> getStudentList(@Param("classId") Long classId);

    int getStudentCount(@Param("classId") Long classId);


}
