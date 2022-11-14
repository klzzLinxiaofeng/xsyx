package com.xunyunedu.character.service;

import com.xunyunedu.character.pojo.Records;
import com.xunyunedu.student.pojo.StudentPojo;

import java.util.List;
import java.util.Map;

public interface RecordService {
    Boolean create(Records records);
    List<Map<String, Object>> pinGePaiHang (Integer schoolId);

    List<Map<String, Object>> pinGePaiHangBanJi (Integer schoolId, Integer teamId, Integer evaluId);
    List<Map<String, Object>> pinGePaiHangNianJi (Integer schoolId,Integer gradeId,Integer evaluId);
    List<Records> pinGeStudentJiLu (Integer schoolId,Integer studentId, Integer evaluationId);

    List<Map<String, Object>> findByTupian(Integer schoolId,Integer studentId,String schoolYear);
    StudentPojo findBySaoMa(Integer studentId,Integer schoolId);
}
