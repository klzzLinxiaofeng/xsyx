package com.xunyunedu.india.service;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.india.condition.OaApplayIndiaDepartmentCountCondition;
import com.xunyunedu.india.param.OaApplayIndiaInsertParam;
import com.xunyunedu.india.pojo.OaApplayIndia;
import com.xunyunedu.india.pojo.OaApplayIndiaDepartmentCount;

import java.util.List;

public interface OaApplayIndiaDepartmentCountService {
    List<OaApplayIndiaDepartmentCount> getByCondition(OaApplayIndiaDepartmentCountCondition condition);

    OaApplayIndiaDepartmentCount getOneByCondition(OaApplayIndiaDepartmentCountCondition condition);

    ApiResult add(OaApplayIndiaDepartmentCount oaApplayIndiaDepartmentCount);

    ApiResult update(OaApplayIndiaDepartmentCount oaApplayIndiaDepartmentCount);

    void saveOrUpdate(OaApplayIndiaInsertParam param, OaApplayIndia applayIndia);
}
