package com.xunyunedu.login.dao;

import com.xunyunedu.login.pojo.PjTeacher;
/**
* @Description:教师dao
* @Author: cmb
* @Date: 2020/10/15
*/
public interface PjTeacherDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PjTeacher record);

    int insertSelective(PjTeacher record);

    PjTeacher findPjTeacherById(String  name);

    int updateByPrimaryKeySelective(PjTeacher record);

    int updateByPrimaryKey(PjTeacher record);
}