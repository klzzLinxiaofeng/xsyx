package com.xunyunedu.paper.dao;

import com.xunyunedu.paper.condition.OaPaperUserCondition;
import com.xunyunedu.paper.pojo.OaPaperUser;

import java.util.List;

public interface OaPaperUserDao {


    Integer insert(OaPaperUser oaPaperUser);

    Integer update(OaPaperUser oaPaperUser);

    List<OaPaperUser> selectByCondition(OaPaperUserCondition condition);
}
