package com.xunyunedu.paper.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.paper.condition.OaPaperCondition;
import com.xunyunedu.paper.param.OaPaperInsertParam;
import com.xunyunedu.paper.pojo.OaPaper;

public interface OaPaperService {
    PageInfo page(PageCondition<OaPaperCondition> condition);

    OaPaper add(OaPaperInsertParam param);

    OaPaper getDetailAndUpdateRedById(Integer id,Integer currUserId);

}
