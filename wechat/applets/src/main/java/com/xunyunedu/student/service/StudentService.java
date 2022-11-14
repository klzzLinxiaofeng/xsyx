package com.xunyunedu.student.service;

import com.xunyunedu.student.pojo.StudentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentPojo> getStudentByTeacherId(@Param("studentPojo") StudentPojo studentPojo);

    List<StudentPojo> getIdByUserId(String name,Integer teamId);

    Map<String, Boolean> getAppletsInterrupteur();

    List<StudentPojo> findStudentVoByTeam(Integer schoolId,String schoolYear,Integer gradeId,Integer teamId);



}
