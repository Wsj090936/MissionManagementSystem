package com.dao;

import com.pojo.Teacher;
import com.pojo.TeacherClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClassMapper {

    List<TeacherClass> getAccountByTeacherId(@Param("teacherId") Long teacherId);

}
