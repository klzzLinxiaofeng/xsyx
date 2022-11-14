package com.xunyunedu.development.service;

import com.xunyunedu.development.vo.DevelopmentIndicators;
import com.xunyunedu.development.vo.StudentDevelopment;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 17:14
 * @Version 1.0
 */
public interface StudentDevelopmentService {
    List<DevelopmentIndicators> findByAll(
             String schoolYear,
            String schoolTrem,
             String code);
    List<StudentDevelopment> findByStudentAll( String schoolYear,
                                              String schoolTrem,
                                               Integer pingjiaId);
    StudentDevelopment findByStudentId(Integer id);

    Integer create( StudentDevelopment studentDevelopment);
}
