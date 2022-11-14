package com.xunyunedu.life.dao;

import com.xunyunedu.life.vo.LifeBehavior;
import com.xunyunedu.life.vo.LifeIndicators;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 9:36
 * @Version 1.0
 */
public interface LifeXingweiDao {
    List<LifeIndicators> findByaLL(@Param("schoolYear") String schoolYear,
                                   @Param("schoolTrem") String schoolTrem);
    Integer create(@Param("lifeBehavior") LifeBehavior lifeBehavior);
    Integer findByFenShu(@Param("studentId") Integer studentId,
                         @Param("zhibiaoId")Integer zhibiaoId,
                         @Param("schoolYear")String schoolYear,
                         @Param("schoolTrem")String schoolTrem);
    LifeIndicators findById(  @Param("zhibiaoId")Integer zhibiaoId);
}
