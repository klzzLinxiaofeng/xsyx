package com.xunyunedu.activitieshome.dao;

import com.xunyunedu.activitieshome.vo.HomeBehavior;
import com.xunyunedu.activitieshome.vo.HomeIndicators;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface HomeBehaviorDao {
    List<HomeIndicators> findByaLL(@Param("schoolYear") String schoolYear,
                                   @Param("schoolTrem") String schoolTrem);
    Integer create(@Param("homeBehavior") HomeBehavior homeBehavior);
    Integer findByFenShu(@Param("studentId") Integer studentId,
                         @Param("zhibiaoId")Integer zhibiaoId,
                         @Param("schoolYear")String schoolYear,
                         @Param("schoolTrem")String schoolTrem);
    HomeIndicators findById(  @Param("zhibiaoId")Integer zhibiaoId);
}
