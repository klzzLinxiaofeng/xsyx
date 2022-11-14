package com.xunyunedu.paper.dao;

import com.xunyunedu.paper.condition.OaPaperDepartmentCountCondition;
import com.xunyunedu.paper.pojo.OaPaperDepartmentCount;

import java.util.List;

public interface OaPaperDepartmentCountDao {


    Integer insert(OaPaperDepartmentCount count);

    Integer update(OaPaperDepartmentCount count);

    List<OaPaperDepartmentCount> selectByCondition(OaPaperDepartmentCountCondition condition);



}
