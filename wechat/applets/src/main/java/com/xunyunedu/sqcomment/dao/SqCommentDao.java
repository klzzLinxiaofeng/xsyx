package com.xunyunedu.sqcomment.dao;

import com.xunyunedu.sqcomment.condition.SqCommentCondition;
import com.xunyunedu.sqcomment.pojo.SqComment;

import java.util.List;

public interface SqCommentDao {

    List<SqComment> all();

    List<SqComment> selectByCondition(SqCommentCondition condition);

    Integer insert(SqComment comment);

}
