package com.xunyunedu.socialresponsibility.dao;

import com.xunyunedu.socialresponsibility.vo.SocialBehavior;
import com.xunyunedu.socialresponsibility.vo.SocialIndicators;
import com.xunyunedu.student.pojo.StudentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface SocialalBehaviorDao {
    List<SocialIndicators> findByaLL(@Param("schoolYear") String schoolYear,
                                     @Param("schoolTrem") String schoolTrem);
    Integer create(@Param("socialBehavior") SocialBehavior socialBehavior);
    Integer findByFenShu(@Param("studentId") Integer studentId,
                         @Param("zhibiaoId")Integer zhibiaoId,
                         @Param("schoolYear")String schoolYear,
                         @Param("schoolTrem")String schoolTrem);
    SocialIndicators findById(@Param("zhibiaoId")Integer zhibiaoId);
    List<StudentPojo> findByteamId(@Param("studentName") String studentName,
                                   @Param("teamId") Integer  teamId,
                                   @Param("studentId") Integer  studentId);
}
