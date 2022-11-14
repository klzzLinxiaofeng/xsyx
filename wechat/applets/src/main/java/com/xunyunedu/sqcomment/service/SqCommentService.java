package com.xunyunedu.sqcomment.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.sqcomment.condition.SqCommentCondition;
import com.xunyunedu.sqcomment.pojo.SqComment;

public interface SqCommentService {
    PageInfo page(PageCondition<SqCommentCondition> condition);

    ApiResult add(SqComment comment);
}
