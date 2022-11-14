package com.xunyunedu.character.dao;

import com.xunyunedu.character.pojo.Evaluation;
import com.xunyunedu.character.pojo.Records;
import com.xunyunedu.character.pojo.StudentContion;
import com.xunyunedu.student.pojo.StudentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationDao {
    List<Evaluation> findByAll();

    Integer defenqingkuang(@Param("studentId") Integer studentId,
                           @Param("evalenId") Integer evalenId);

    Evaluation findById(@Param("id")Integer id);
    StudentContion findByStu(@Param("score") Integer score,
                                   @Param("schoolId") Integer schoolId,
                                   @Param("evaluationId") Integer evaluationId,
                                   @Param("studentId") Integer studentId,
                                   @Param("teamId") Integer teamId);

    List<StudentContion> findByNianJi(@Param("score") Integer score,
                                   @Param("schoolId") Integer schoolId,
                                   @Param("evaluationId") Integer evaluationId,
                                   @Param("gradeId") Integer gradeId);

    List<Records> findByJiLu(@Param("schoolId") Integer schoolId,
                             @Param("studentId") Integer studentId,
                             @Param("evaluationId") Integer evaluationId);
    List<StudentContion> evaluationDaofindByquanxiao(@Param("schoolId") Integer schoolId,
                                       @Param("gradeId") Integer gradeId,
                                       @Param("teamId") Integer teamId);


   Integer  findByAllStudent(@Param("schoolId") Integer schoolId,
                             @Param("studentId") Integer studentId);

    Integer  findByAllStudent2(@Param("schoolId") Integer schoolId,
                               @Param("studentId") Integer studentId,
                               @Param("schoolYear") String schoolYear);

    Integer findByasd(@Param("schoolId") Integer schoolId,
                      @Param("gradeId") Integer gradeId,
                      @Param("evaluationId") Integer evaluationId);

    Integer findByGrsa (@Param("schoolId") Integer schoolId,
                        @Param("studentId") Integer studentId,
                        @Param("schoolYear") String schoolYear);

//班级
    Integer findByeeee(@Param("schoolId") Integer schoolId,
                       @Param("studentId") Integer studentId,
                      @Param("evaluationId") Integer evaluationId);


    Integer getByStudentIda(@Param("schoolId") Integer schoolId,
                            @Param("studentId") Integer studentId,
                            @Param("evaluationId") Integer evaluationId);

    StudentPojo findBySaoMa(@Param("schoolId") Integer schoolId,
                            @Param("studentId") Integer studentId);


}
