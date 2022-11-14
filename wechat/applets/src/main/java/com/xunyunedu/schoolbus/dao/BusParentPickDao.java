package com.xunyunedu.schoolbus.dao;

import com.xunyunedu.schoolbus.pojo.BusParentPick;

public interface BusParentPickDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusParentPick record);

}