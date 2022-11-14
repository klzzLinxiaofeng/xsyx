package com.xunyunedu.student.dao;

import com.xunyunedu.student.pojo.StudentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    List<StudentPojo> read(StudentPojo studentPojo);

    void update(StudentPojo studentPojo);

    List<StudentPojo> getStudentByTeacher(StudentPojo studentPojo);

    StudentPojo getStudentById(Integer id);

    List<StudentPojo> getStudentByTeacherId(@Param("studentPojo") StudentPojo studentPojo);


    StudentPojo findStuTeamById(StudentPojo studentPojo);

    List<StudentPojo> getIdByUserId(@Param("name")String name,@Param("teamId")Integer teamId);

    Map<String, Boolean> getAppletsInterrupteur(@Param("interrupter") String interrupter);
    List<StudentPojo> findStudentVoByTeam(@Param("schoolId")Integer schoolId,
                                          @Param("schoolYear")String schoolYear,
                                          @Param("gradeId")Integer gradeId,
                                          @Param("teamId")Integer teamId);
}
