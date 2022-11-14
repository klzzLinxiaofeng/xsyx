package com.xunyunedu.leave.dao;

import com.xunyunedu.leave.condition.OaApplayLeaveDepartmentCountCondition;
import com.xunyunedu.leave.pojo.OaApplayLeaveDepartmentCount;

import java.util.List;

public interface OaApplayLeaveDepartmentCountDao {


    Integer insert(OaApplayLeaveDepartmentCount applayLeaveDepartmentCount);

    List<OaApplayLeaveDepartmentCount> selectByCondition(OaApplayLeaveDepartmentCountCondition condition);

    Integer update(OaApplayLeaveDepartmentCount applayLeaveDepartmentCount);

}
