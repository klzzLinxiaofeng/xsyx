package com.xunyunedu.paper.service;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.paper.condition.OaPaperUserCondition;
import com.xunyunedu.paper.pojo.OaPaper;
import com.xunyunedu.paper.pojo.OaPaperUser;

import java.util.List;

public interface OaPaperUserService {

    List<OaPaperUser> getByCondition(OaPaperUserCondition condition);

    OaPaperUser getOneByCondition(OaPaperUserCondition condition);

    ApiResult add(OaPaperUser oaPaperUser);

    ApiResult update(OaPaperUser oaPaperUser);
}
