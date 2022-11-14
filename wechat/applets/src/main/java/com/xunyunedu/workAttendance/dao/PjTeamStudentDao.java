package com.xunyunedu.workAttendance.dao;

import com.xunyunedu.workAttendance.model.PjTeamStudent;
import com.xunyunedu.workAttendance.model.PjTeamStudentKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PjTeamStudentDao {
    int deleteByPrimaryKey(PjTeamStudentKey key);

    int insert(PjTeamStudent record);

    int insertSelective(PjTeamStudent record);

    PjTeamStudent findPjTeamStudentByStudentId(@Param("studentId") Integer studentId);
    List<PjTeamStudent> findPjTeamStudentByTeamId(@Param("teamId")Integer teamId);

    int updateByPrimaryKeySelective(PjTeamStudent record);

    int updateByPrimaryKey(PjTeamStudent record);
}