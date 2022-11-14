package com.xunyunedu.indicators.service;

import com.xunyunedu.indicators.pojo.IndicatorsStudent;

public interface IndicatorsStudentService {
   IndicatorsStudent findByObject(Integer studentId, String schoolYear, Integer schoolId);
}
