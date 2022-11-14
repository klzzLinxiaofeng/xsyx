package com.xunyunedu.mergin.dao;

import com.xunyunedu.mergin.vo.JobControl;
import com.xunyunedu.mergin.vo.JobControlCondition;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobControlDao extends GenericDao<JobControl, Integer> {
    List<JobControl> findJobControlByCondition(@Param("jobControlCondition") JobControlCondition jobControlCondition,
                                               @Param("page") Page page,@Param("order") Order order);

}
