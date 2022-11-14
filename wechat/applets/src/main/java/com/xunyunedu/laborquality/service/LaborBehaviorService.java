package com.xunyunedu.laborquality.service;

import com.xunyunedu.laborquality.vo.LaborBehavior;
import com.xunyunedu.laborquality.vo.LaborIndicators;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface LaborBehaviorService {
    List<LaborIndicators> findByaLL(String schoolYear, String schoolTrem);
    Integer create(LaborBehavior laborBehavior);
    Integer findByFenShu( Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem);
}
