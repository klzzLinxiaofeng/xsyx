package com.xunyunedu.visit.dao;

import com.xunyunedu.visit.pojo.VisitApplyCondition;
import com.xunyunedu.visit.pojo.VisitVisitApply;

import java.util.List;

public interface VisitVisitApplyDao {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(VisitVisitApply record);

    VisitVisitApply selectByPrimaryKey(Integer id);

    VisitVisitApply selectDetailByPrimaryKey(Integer id);

    List<VisitVisitApply> selectByCondition(VisitApplyCondition condition);

    List<VisitVisitApply> selectMyNotApproval(VisitApplyCondition condition);

    int updateByPrimaryKeySelective(VisitVisitApply record);

}