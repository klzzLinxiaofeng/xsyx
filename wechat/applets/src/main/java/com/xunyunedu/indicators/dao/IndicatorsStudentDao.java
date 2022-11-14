package com.xunyunedu.indicators.dao;

import com.xunyunedu.indicators.pojo.IndicatorsPojo;
import com.xunyunedu.indicators.pojo.IndicatorsStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndicatorsStudentDao {
   List<IndicatorsPojo> findBystudents(@Param("studentId") Integer studentId,
                                       @Param("schoolYear") String schoolYear,
                                       @Param("schoolId") Integer schoolId);
    IndicatorsStudent findByIndicaStudent(@Param("schoolId") Integer schoolId,
                                          @Param("studentId")Integer studentId);
    List<Integer>  findByQingJia(@Param("studentId")Integer studentId,
                                 @Param("schoolId") Integer schoolId);
}
