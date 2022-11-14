package com.xunyunedu.culturalideal.service;

import com.xunyunedu.culturalideal.vo.IdealBehavior;
import com.xunyunedu.culturalideal.vo.IdealIndicators;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
public interface IdealBehaviorService {
    List<IdealIndicators> findByaLL(String schoolYear, String schoolTrem);
    Integer create(IdealBehavior idealBehavior);
    Integer findByFenShu( Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem);
}
