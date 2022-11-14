package com.xunyunedu.mentalhealth.dao;

import com.xunyunedu.mentalhealth.vo.MentalBehavior;
import com.xunyunedu.mentalhealth.vo.MentalIndicators;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 13:53
 * @Version 1.0
 */
public interface MentalBehaviorDao {
    List<MentalIndicators> findByaLL(@Param("schoolYear") String schoolYear,
                                     @Param("schoolTrem") String schoolTrem);
    Integer create(@Param("mentalBehavior") MentalBehavior mentalBehavior);
    Integer findByFenShu(@Param("studentId") Integer studentId,
                         @Param("zhibiaoId")Integer zhibiaoId,
                         @Param("schoolYear")String schoolYear,
                         @Param("schoolTrem")String schoolTrem);
    MentalIndicators findById(@Param("zhibiaoId")Integer zhibiaoId);
}
