package com.xunyunedu.homeWorkZy.dao;

import com.xunyunedu.PublishAndAcceptJob.pojo.StudentHomeWoke;
import com.xunyunedu.homeWorkZy.pojo.HomeWokePojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeWorkMapping {
    List<HomeWokePojo> findByAll(@Param("schoolId") Integer schoolId,
                                 @Param("teacherId") Integer teacherId);
    List<StudentHomeWoke> findByStudentHomeWokeAll(@Param("id") Integer id,
                                                   @Param("studentName")String studentName);
    Integer updateStudentHomeWokePing( StudentHomeWoke studentHomeWoke);

    List<HomeWokePojo> findByAllStudentId(@Param("schoolId") Integer schoolId,
                                 @Param("studentId") Integer studentId,
                                          @Param("subjectId") Integer subjectId);

    StudentHomeWoke findById(@Param("id") Integer id,
                             @Param("schoolId") Integer schoolId,
                             @Param("studentId") Integer studentId);
}
