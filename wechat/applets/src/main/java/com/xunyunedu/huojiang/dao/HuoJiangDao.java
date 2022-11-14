package com.xunyunedu.huojiang.dao;

import com.xunyunedu.huojiang.vo.ClassLunwen;
import com.xunyunedu.huojiang.vo.HuoJiang;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HuoJiangDao {
    List<HuoJiang> findByAll(@Param("teacherId") Integer teacherId,
                             @Param("type") String type);
    HuoJiang findById(@Param("id") Integer id);

    List<TeacherPojo> findTeacherByLikeNameAndSchool(@Param("name") String name,
                                                     @Param("schoolId") Integer schoolId);
    ClassLunwen findByTongJi(@Param("id") Integer id);
}
