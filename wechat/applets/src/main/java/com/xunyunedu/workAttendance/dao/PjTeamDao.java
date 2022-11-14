package com.xunyunedu.workAttendance.dao;


import com.xunyunedu.workAttendance.model.PjTeam;

public interface PjTeamDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PjTeam record);

    int insertSelective(PjTeam record);

    PjTeam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PjTeam record);

    int updateByPrimaryKey(PjTeam record);
}