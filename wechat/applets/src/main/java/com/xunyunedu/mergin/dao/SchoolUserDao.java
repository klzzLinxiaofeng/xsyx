package com.xunyunedu.mergin.dao;

import com.xunyunedu.mergin.util.SchoolUserCondition;
import com.xunyunedu.mergin.vo.SchoolUser;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolUserDao extends GenericDao<SchoolUser, Integer> {
    List<SchoolUser> findSchoolUserByCondition(@Param("schoolUserCondition") SchoolUserCondition schoolUserCondition,
                                               @Param("page") Page page,@Param("order") Order order);

}
