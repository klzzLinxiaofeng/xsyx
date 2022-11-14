package com.xunyunedu.act.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.act.pojo.AtSubstitute;
import com.xunyunedu.act.pojo.OutSchoolActivity;
import com.xunyunedu.exception.PageCondition;

public interface AtSubstituteService {
    int deleteByPrimaryKey(Integer id);

    int create(AtSubstitute record);

    int createSelective(AtSubstitute record);

    AtSubstitute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AtSubstitute record);

    int updateByPrimaryKey(AtSubstitute record);

    PageInfo selectList(PageCondition<AtSubstitute> condition);
}