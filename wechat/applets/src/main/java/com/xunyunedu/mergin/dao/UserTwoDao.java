package com.xunyunedu.mergin.dao;

import com.xunyunedu.mergin.vo.User;
import framework.generic.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

public interface UserTwoDao extends GenericDao<User, Integer> {
    User findByUsername(@Param("var1") String var1);
}
