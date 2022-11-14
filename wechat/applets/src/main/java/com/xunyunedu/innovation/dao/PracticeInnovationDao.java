package com.xunyunedu.innovation.dao;

import com.xunyunedu.innovation.pojo.PracticeInnovation;
import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.personinfor.pojo.PublicClassPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PracticeInnovationDao {
   PracticeInnovation findByPraInner(@Param("studentId") Integer studentId,
                                     @Param("schoolId") Integer schoolId);
   Student findByStudentId(@Param("studentId") Integer studentId,
                           @Param("schoolId") Integer schoolId);
   List<PublicClassPojo> fidByAllPublicClass(@Param("studentId") Integer studentId,
                                             @Param("schoolId") Integer schoolId);
}
