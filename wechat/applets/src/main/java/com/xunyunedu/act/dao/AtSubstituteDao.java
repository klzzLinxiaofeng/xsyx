package com.xunyunedu.act.dao;

import com.xunyunedu.act.pojo.AtSubstitute;

import java.util.List;

public interface AtSubstituteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AtSubstitute record);

    int insertSelective(AtSubstitute record);

    AtSubstitute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AtSubstitute record);

    int updateByPrimaryKey(AtSubstitute record);

    List<AtSubstitute> selectBy(AtSubstitute s);
}