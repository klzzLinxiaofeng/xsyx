package com.xunyunedu.core.dao;

import com.xunyunedu.common.pojo.SqlStatement;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BasicSQLDao {
    int update(@Param("sql") String sal);
    List<Map<String,Object>> find(@Param("sql") String sql);
}
