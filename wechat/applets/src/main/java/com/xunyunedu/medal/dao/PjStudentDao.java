package com.xunyunedu.medal.dao;

import com.xunyunedu.medal.model.PjStudent;

import java.util.List;

/**
 * @description: 学生dao
 * @author: cmb
 * @create: 2020-10-15 18:58
 **/
public interface PjStudentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PjStudent record);

    int insertSelective(PjStudent record);
     List<PjStudent> findStudentAll();
    PjStudent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PjStudent record);

    int updateByPrimaryKey(PjStudent record);
}
