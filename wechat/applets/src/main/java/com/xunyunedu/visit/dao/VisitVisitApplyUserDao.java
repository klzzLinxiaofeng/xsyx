package com.xunyunedu.visit.dao;

import com.xunyunedu.visit.pojo.VisitVisitApplyUser;

public interface VisitVisitApplyUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitVisitApplyUser record);

    int insertSelective(VisitVisitApplyUser record);

    VisitVisitApplyUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitVisitApplyUser record);

    int updateByPrimaryKey(VisitVisitApplyUser record);
}