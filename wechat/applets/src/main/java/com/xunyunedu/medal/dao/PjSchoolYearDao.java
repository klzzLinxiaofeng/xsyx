package com.xunyunedu.medal.dao;

import com.xunyunedu.medal.model.PjSchoolYear;

public interface PjSchoolYearDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PjSchoolYear record);

    int insertSelective(PjSchoolYear record);

    PjSchoolYear findSchoolYearBySchoolId(Integer schoolId);

    int updateByPrimaryKeySelective(PjSchoolYear record);

    int updateByPrimaryKey(PjSchoolYear record);
}