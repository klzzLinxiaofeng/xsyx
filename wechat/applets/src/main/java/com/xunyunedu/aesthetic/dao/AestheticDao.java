package com.xunyunedu.aesthetic.dao;

import com.xunyunedu.aesthetic.pojo.AestheticPojo;
import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AestheticDao {

    AestheticPojo findByAesthetic(@Param("studentId")  Integer studentId,
                                  @Param("schoolId")  Integer schoolId,
                                  @Param("schoolYear")  String schoolYear,
                                  @Param("schoolTrem")  String schoolTrem);

    Student findByStudentId(@Param("studentId")  Integer studentId,
                            @Param("schoolId")  Integer schoolId);

    /*后面废除*/
    List<Student> findByAllStudent(@Param("studentId")  Integer studentId,
                                   @Param("schoolId")  Integer schoolId);
     void updateStudent(@Param("studentId")  Integer studentId,
                        @Param("schoolId")  Integer schoolId);

    List<TeacherPojo> findByAllTeacher(@Param("teacherId")  Integer teacherId,
                                       @Param("schoolId")  Integer schoolId);

    void updateTeacher(@Param("teacherId")  Integer teacherId,
                       @Param("schoolId")  Integer schoolId);


}
