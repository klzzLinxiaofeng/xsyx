package com.xunyunedu.notice.dao;

import com.xunyunedu.notice.condition.PubNoticeCondition;
import com.xunyunedu.notice.pojo.PubNotice;

import java.util.List;

public interface PubNoticeDao {

    Integer insert(PubNotice pubNotice);

    List<PubNotice> selectByCondition(PubNoticeCondition condition);

    PubNotice selectById(Integer id);
}
