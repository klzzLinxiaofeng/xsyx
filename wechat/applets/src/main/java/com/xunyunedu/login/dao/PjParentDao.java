package com.xunyunedu.login.dao;


import com.xunyunedu.login.pojo.PjParent;
import org.apache.ibatis.annotations.Param;
/**
* @Description:家长dao
* @Author: cmb
* @Date: 2020/10/15
*/
public interface PjParentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PjParent record);

    int insertSelective(PjParent record);

    PjParent findPjParentByUserId(@Param("id")Integer id);
    PjParent findParentByName(@Param("userName")String  userName);

    int updateByPrimaryKeySelective(PjParent record);

    int updateByPrimaryKey(PjParent record);

        }