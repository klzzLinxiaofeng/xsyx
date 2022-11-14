package com.xunyunedu.mergin.dao;

import framework.generic.dao.GenericDao;
import org.apache.ibatis.annotations.Param;
import platform.education.user.model.Profile;

public interface ProfileDao extends GenericDao<Profile, Integer> {
    Profile findByUserId(@Param("var1") Integer var1);

    Profile findByUserName(@Param("var1") String var1);
}
