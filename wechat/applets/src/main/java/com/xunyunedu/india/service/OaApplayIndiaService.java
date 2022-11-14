package com.xunyunedu.india.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.india.condition.OaApplayIndiaCondition;
import com.xunyunedu.india.param.OaApplayIndiaInsertParam;
import com.xunyunedu.india.pojo.OaApplayIndia;

public interface OaApplayIndiaService {
    PageInfo page(PageCondition<OaApplayIndiaCondition> condition);

    OaApplayIndia getDetailById(Integer id);

    OaApplayIndia add(OaApplayIndiaInsertParam param);
}
