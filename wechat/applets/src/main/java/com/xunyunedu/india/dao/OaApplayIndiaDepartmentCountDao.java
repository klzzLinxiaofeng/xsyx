package com.xunyunedu.india.dao;

import com.xunyunedu.india.condition.OaApplayIndiaDepartmentCountCondition;
import com.xunyunedu.india.pojo.OaApplayIndiaDepartmentCount;

import java.util.List;

public interface OaApplayIndiaDepartmentCountDao {

    List<OaApplayIndiaDepartmentCount> selectByCondition(OaApplayIndiaDepartmentCountCondition condition);

    OaApplayIndiaDepartmentCount selectById(Integer id);

    Integer insert(OaApplayIndiaDepartmentCount oaApplayIndiaDepartmentCount);

    Integer update(OaApplayIndiaDepartmentCount oaApplayIndiaDepartmentCount);

}
