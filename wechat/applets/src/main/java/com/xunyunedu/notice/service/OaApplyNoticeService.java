package com.xunyunedu.notice.service;

import com.xunyunedu.notice.pojo.OaApplyNotice;

public interface OaApplyNoticeService {

    void add(OaApplyNotice oaApplyNotice);

    void updateApplyResult(Integer receiverId,Integer dataId,Integer applyResult);

}
