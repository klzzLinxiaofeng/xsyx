package com.xunyunedu.innovation.service;

import com.xunyunedu.innovation.pojo.PracticeInnovation;

public interface PracticeInnovationService {
    PracticeInnovation findByStudentIdAll(Integer studentId,Integer schoolId);
}
