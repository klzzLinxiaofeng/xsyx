package com.xunyunedu.laborquality.dao;

import com.xunyunedu.laborquality.vo.LaborBehavior;
import com.xunyunedu.laborquality.vo.LaborIndicators;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface LaborBehaviorDao{
    List<LaborIndicators> findByaLL(@Param("schoolYear") String schoolYear,
                                    @Param("schoolTrem") String schoolTrem);
    Integer create(@Param("laborBehavior") LaborBehavior laborBehavior);
    Integer findByFenShu(@Param("studentId") Integer studentId,
                         @Param("zhibiaoId")Integer zhibiaoId,
                         @Param("schoolYear")String schoolYear,
                         @Param("schoolTrem")String schoolTrem);
    LaborIndicators findById(@Param("zhibiaoId")Integer zhibiaoId);
}
