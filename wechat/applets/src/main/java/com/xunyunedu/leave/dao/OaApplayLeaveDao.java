package com.xunyunedu.leave.dao;

import com.xunyunedu.leave.condition.OaApplayLeaveCondition;
import com.xunyunedu.leave.pojo.OaApplayLeave;

import java.util.List;
import java.util.Map;

public interface OaApplayLeaveDao {

    OaApplayLeave selectById(Integer id);

    Integer insert(OaApplayLeave oaApplayLeave);

    List<OaApplayLeave> selectByCondition(OaApplayLeaveCondition condition);

    List<Map<String, Object>> selectLeaveTypes();
}
