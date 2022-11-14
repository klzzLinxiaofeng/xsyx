package com.xunyunedu.paper.service;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.paper.condition.OaPaperDepartmentCountCondition;
import com.xunyunedu.paper.param.OaPaperInsertParam;
import com.xunyunedu.paper.pojo.OaPaperDepartmentCount;

import java.util.List;

public interface OaPaperDepartmentCountService {
    ApiResult add(OaPaperDepartmentCount oaPaperDepartmentCount);

    ApiResult update(OaPaperDepartmentCount oaPaperDepartmentCount);

    List<OaPaperDepartmentCount> getByCondition(OaPaperDepartmentCountCondition condition);

    OaPaperDepartmentCount getOneByCondition(OaPaperDepartmentCountCondition condition);

    void addOrUpdate(OaPaperInsertParam param,Integer departId);
}
