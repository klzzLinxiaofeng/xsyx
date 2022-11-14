package com.xunyunedu.life.service;

import com.xunyunedu.life.vo.LifeBehavior;
import com.xunyunedu.life.vo.LifeIndicators;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 9:35
 * @Version 1.0
 */
public interface LifeXingweiService {
    List<LifeIndicators> findByaLL( String schoolYear, String schoolTrem);
    Integer create( LifeBehavior lifeBehavior);
    Integer findByFenShu( Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem);
}
