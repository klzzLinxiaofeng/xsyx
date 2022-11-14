package com.xunyunedu.notice.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.notice.condition.PubNoticeCondition;
import com.xunyunedu.notice.param.PubNoticeInsertParam;
import com.xunyunedu.notice.pojo.PubNotice;
import com.xunyunedu.notice.pojo.detail.PubNoticeDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PubNoticeService {
    PageInfo page(PageCondition<PubNoticeCondition> condition);

    PubNotice add(PubNoticeInsertParam param);

    PubNotice updateStatusAndGetDetail(Integer id, Integer userId, Boolean updateRead);

    void addSystemNoticeAndReceiver(PubNotice notice, List receiverList, String userIdPropertyName);
}
