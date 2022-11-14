package com.xunyunedu.evaluationclasses.service;

import com.xunyunedu.evaluationclasses.vo.ClassesBehavior;
import com.xunyunedu.evaluationclasses.vo.ClassesIndicators;
import com.xunyunedu.student.pojo.StudentPojo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface ClassesBehaviorService {
    List<ClassesIndicators> findByaLL(String schoolYear, String schoolTrem);
    Integer create(ClassesBehavior classesBehavior);
    Integer findByFenShu( Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem);

    List<StudentPojo> findById(String studentName, Integer teamId, Integer studentId);
}
