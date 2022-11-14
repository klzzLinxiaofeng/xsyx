package com.xunyunedu.socialresponsibility.service;

import com.xunyunedu.socialresponsibility.vo.SocialBehavior;
import com.xunyunedu.socialresponsibility.vo.SocialIndicators;
import com.xunyunedu.student.pojo.StudentPojo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface SocialBehaviorService {
    List<SocialIndicators> findByaLL(String schoolYear, String schoolTrem);
    Integer create(SocialBehavior socialBehavior);
    Integer findByFenShu( Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem);

    List<StudentPojo> findById(String studentName, Integer teamId, Integer studentId);
}
