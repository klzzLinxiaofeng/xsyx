package com.xunyunedu.activitieshome.service;

import com.xunyunedu.activitieshome.vo.HomeBehavior;
import com.xunyunedu.activitieshome.vo.HomeIndicators;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface HomeBehaviorService {
    List<HomeIndicators> findByaLL(String schoolYear, String schoolTrem);
    Integer create( HomeBehavior homeBehavior);
    Integer findByFenShu( Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem);
}
