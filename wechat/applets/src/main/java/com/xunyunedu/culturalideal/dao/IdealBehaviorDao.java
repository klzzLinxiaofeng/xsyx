package com.xunyunedu.culturalideal.dao;

import com.xunyunedu.culturalideal.vo.IdealBehavior;
import com.xunyunedu.culturalideal.vo.IdealIndicators;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface IdealBehaviorDao {
    List<IdealIndicators> findByaLL(@Param("schoolYear") String schoolYear,
                                    @Param("schoolTrem") String schoolTrem);
    Integer create(@Param("idealBehavior") IdealBehavior idealBehavior);
    Integer findByFenShu(@Param("studentId") Integer studentId,
                         @Param("zhibiaoId")Integer zhibiaoId,
                         @Param("schoolYear")String schoolYear,
                         @Param("schoolTrem")String schoolTrem);
    IdealIndicators findById(  @Param("zhibiaoId")Integer zhibiaoId);
}
