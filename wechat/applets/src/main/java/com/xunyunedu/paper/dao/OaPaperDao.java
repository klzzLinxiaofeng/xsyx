package com.xunyunedu.paper.dao;

import com.xunyunedu.paper.condition.OaPaperCondition;
import com.xunyunedu.paper.pojo.OaPaper;

import java.util.List;

public interface OaPaperDao {

    Integer insert(OaPaper oaPaper);

    List<OaPaper> selectByCondition(OaPaperCondition condition);

    OaPaper selectById(Integer id);
}
