package com.xunyunedu.mergin.dao;

import com.xunyunedu.mergin.vo.UserBinding;
import com.xunyunedu.mergin.vo.UserBindingCondition;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBindingDao extends GenericDao<UserBinding, Integer> {
    List<UserBinding> findUserBindingByCondition(@Param("var1") UserBindingCondition var1,
                                                 @Param("var2")Page var2,
                                                 @Param("var3")Order var3);
    UserBinding findByBindingName(@Param("var1") String var1);

}
