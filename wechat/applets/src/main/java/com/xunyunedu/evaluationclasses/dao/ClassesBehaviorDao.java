package com.xunyunedu.evaluationclasses.dao;

import com.xunyunedu.evaluationclasses.vo.ClassesBehavior;
import com.xunyunedu.evaluationclasses.vo.ClassesIndicators;
import com.xunyunedu.student.pojo.StudentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface ClassesBehaviorDao {
    List<ClassesIndicators> findByaLL(@Param("schoolYear") String schoolYear,
                                      @Param("schoolTrem") String schoolTrem);
    Integer create(@Param("classesBehavior") ClassesBehavior classesBehavior);
    Integer findByFenShu(@Param("studentId") Integer studentId,
                         @Param("zhibiaoId")Integer zhibiaoId,
                         @Param("schoolYear")String schoolYear,
                         @Param("schoolTrem")String schoolTrem);
    ClassesIndicators findById(@Param("zhibiaoId")Integer zhibiaoId);
    List<StudentPojo> findByteamId(@Param("studentName") String studentName,
                                   @Param("teamId") Integer  teamId,
                                   @Param("studentId") Integer  studentId);
}
