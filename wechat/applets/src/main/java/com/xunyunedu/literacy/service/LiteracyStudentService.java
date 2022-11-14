package com.xunyunedu.literacy.service;

import com.xunyunedu.PublishAndAcceptJob.pojo.SubjectPojo;
import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.mergin.vo.Team;

import java.util.List;
import java.util.Map;

public interface LiteracyStudentService {
    List<SubjectPojo> findByStuId( Integer schoolId,Integer studentId,String schoolYear);
    Map<String,Object> findByStudentId(Integer studentId,Integer subjectId,String schoolTrem, String schoolYear);

    List<Team> findByTeacher( Integer schoolId,Integer teacherId,String schoolYear);

    List<SubjectPojo> fidByTeacherId(Integer schoolId,Integer teamId,Integer teacherId);

   List<Student> getByStudent(Integer schoolId,Integer teamId,String studentName);

}
