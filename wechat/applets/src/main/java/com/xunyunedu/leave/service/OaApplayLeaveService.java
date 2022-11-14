package com.xunyunedu.leave.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.leave.condition.OaApplayLeaveCondition;
import com.xunyunedu.leave.param.OaApplayLeaveInsertParam;
import com.xunyunedu.leave.pojo.OaApplayLeave;

import java.util.List;
import java.util.Map;

public interface OaApplayLeaveService {
    PageInfo page(PageCondition<OaApplayLeaveCondition> condition);

    OaApplayLeave add(OaApplayLeaveInsertParam param);

    List<Map<String,Object>>  getLeaveTypes();

    OaApplayLeave getDetailById(Integer id);

}
