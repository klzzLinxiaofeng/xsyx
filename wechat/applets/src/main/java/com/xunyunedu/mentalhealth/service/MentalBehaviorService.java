package com.xunyunedu.mentalhealth.service;

import com.xunyunedu.mentalhealth.vo.MentalBehavior;
import com.xunyunedu.mentalhealth.vo.MentalIndicators;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface MentalBehaviorService {
    List<MentalIndicators> findByaLL(String schoolYear, String schoolTrem);
    Integer create(MentalBehavior mentalBehavior);
    Integer findByFenShu( Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem);
}
