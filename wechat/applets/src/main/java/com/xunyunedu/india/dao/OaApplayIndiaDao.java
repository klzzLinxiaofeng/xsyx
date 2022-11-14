package com.xunyunedu.india.dao;

import com.xunyunedu.india.condition.OaApplayIndiaCondition;
import com.xunyunedu.india.pojo.OaApplayIndia;

import java.util.List;

public interface OaApplayIndiaDao {

    OaApplayIndia selectById(Integer id);

    List<OaApplayIndia> selectByCondition(OaApplayIndiaCondition oaApplayIndiaCondition);

    Integer insert(OaApplayIndia oaApplayIndia);
}
