package com.xunyunedu.medal.dao;


import com.xunyunedu.medal.model.PjSchoolTerm;

import java.util.Map;

public interface PjSchoolTermDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PjSchoolTerm record);

    int insertSelective(PjSchoolTerm record);

    PjSchoolTerm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PjSchoolTerm record);

    int updateByPrimaryKey(PjSchoolTerm record);
    PjSchoolTerm findYearTerm(Map map);
}