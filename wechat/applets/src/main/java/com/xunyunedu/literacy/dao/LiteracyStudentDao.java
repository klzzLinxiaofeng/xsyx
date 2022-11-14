package com.xunyunedu.literacy.dao;

import com.xunyunedu.PublishAndAcceptJob.pojo.SubjectPojo;
import com.xunyunedu.literacy.pojo.LiteracyStudent;
import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.mergin.vo.Team;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LiteracyStudentDao {
    List<SubjectPojo> findByStuId(@Param("schoolId") Integer schoolId,
                                  @Param("studentId")Integer studentId,
                                  @Param("schoolYear")String schoolYear);
    List<LiteracyStudent> findByStudentScore(@Param("subjectId") Integer subjectId,
                                             @Param("studentId") Integer studentId,
                                             @Param("schoolYear") String schoolYear,
                                             @Param("schoolTrem") String  schoolTrem);
    List<LiteracyStudent> findByGradeScore(@Param("subjectId") Integer subjectId,
                                           @Param("studentId") Integer studentId,
                                           @Param("schoolYear") String schoolYear,
                                           @Param("schoolTrem") String  schoolTrem);
    List<LiteracyStudent> findByTeamScore(@Param("subjectId") Integer subjectId,
                                          @Param("studentId") Integer studentId,
                                          @Param("schoolYear") String schoolYear,
                                          @Param("schoolTrem") String  schoolTrem);
    List<Team> findByTeacher(@Param("schoolId") Integer schoolId, @Param("teacherId")Integer teacherId,@Param("schoolYear")String schoolYear);
    List<SubjectPojo> fidByTeacherId(@Param("schoolId") Integer schoolId,
                                     @Param("teamId")Integer teamId,
                                     @Param("teacherId") Integer teacherId);

   List<Student> findByStudent(@Param("schoolId") Integer schoolId,
                               @Param("teamId")Integer teamId,
                               @Param("studentName")String  studentName );
}
