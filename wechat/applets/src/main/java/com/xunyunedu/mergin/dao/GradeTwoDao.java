package com.xunyunedu.mergin.dao;


import com.xunyunedu.mergin.vo.Grade;
import framework.generic.dao.GenericDao;
import org.apache.ibatis.annotations.Param;


public interface GradeTwoDao extends GenericDao<Grade, Integer> {
    public Grade findById(@Param("id") Integer id);
}
