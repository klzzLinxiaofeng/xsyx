package com.xunyunedu.visit.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.visit.pojo.VisitApplyCondition;
import com.xunyunedu.visit.pojo.VisitVisitApply;

public interface VisitVisitApplyService {

    boolean add(VisitVisitApply apply);
    boolean updateSelective(VisitVisitApply apply);
    VisitVisitApply queryDetailById(Integer id);
    VisitVisitApply queryById(Integer id);
    PageInfo<VisitVisitApply> pagingQuery(PageCondition<VisitApplyCondition> pageCondition);

    PageInfo<VisitVisitApply> pagingQueryMyNotApproval(PageCondition<VisitApplyCondition> pageCondition);

}
