package com.xunyunedu.homeWorkZy.service;

import com.xunyunedu.PublishAndAcceptJob.pojo.StudentHomeWoke;
import com.xunyunedu.homeWorkZy.pojo.HomeWokePojo;

import java.util.List;

public interface HomeWokeService {
    List<HomeWokePojo> findByAll(Integer schoolId,Integer teacherId);
    List<StudentHomeWoke> findByStudentHomeWokeAll(Integer id,String studentName);
    Integer updateStudentHomeWokePing(StudentHomeWoke studentHomeWoke);

    List<HomeWokePojo> findByAllStudentId(Integer schoolId,Integer studentId,Integer subjectId);
    StudentHomeWoke findById(Integer id,Integer studentId,Integer schoolId);
}
