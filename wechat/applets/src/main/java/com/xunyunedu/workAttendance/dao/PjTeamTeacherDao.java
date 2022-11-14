package com.xunyunedu.workAttendance.dao;


import com.xunyunedu.workAttendance.model.PjTeamTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PjTeamTeacherDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PjTeamTeacher record);

    int insertSelective(PjTeamTeacher record);

    List<PjTeamTeacher> findTeamTeacherByUserId(@Param("userId") Integer userId);

    int updateByPrimaryKeySelective(PjTeamTeacher record);

    int updateByPrimaryKey(PjTeamTeacher record);

    /**
     * 获取当前老师对应的班主任信息--判断是否为班主任
     *
     * @param teacherId
     * @return
     */
    List<PjTeamTeacher> findTeacherByTeacherId(@Param("teacherId") Integer teacherId);

}