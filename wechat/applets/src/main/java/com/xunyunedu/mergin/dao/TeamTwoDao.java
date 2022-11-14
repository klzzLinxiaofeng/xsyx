package com.xunyunedu.mergin.dao;


import com.xunyunedu.mergin.vo.Team;
import org.apache.ibatis.annotations.Param;

public interface TeamTwoDao {
    public Team findById(@Param("id") Integer id);
}
