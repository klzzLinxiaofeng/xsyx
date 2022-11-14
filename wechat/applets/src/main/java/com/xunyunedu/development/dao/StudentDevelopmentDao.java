package com.xunyunedu.development.dao;

import com.xunyunedu.development.vo.DevelopmentIndicators;
import com.xunyunedu.development.vo.StudentDevelopment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 10:09
 * @Version 1.0
 */
public interface StudentDevelopmentDao {
   List<DevelopmentIndicators> findByAll(
           @Param("schoolYear") String schoolYear,
           @Param("schoolTrem") String schoolTrem,
           @Param("code") String code);
   List<StudentDevelopment> findByStudentAll(@Param("schoolYear") String schoolYear,
                                             @Param("schoolTrem") String schoolTrem,
                                             @Param("pingjiaId") Integer pingjiaId);
    StudentDevelopment findByStudentId(@Param("id")Integer id);

   Integer create(@Param("studentDevelopment") StudentDevelopment studentDevelopment);
}
