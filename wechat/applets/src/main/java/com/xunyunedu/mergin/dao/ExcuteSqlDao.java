package com.xunyunedu.mergin.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExcuteSqlDao {
    List<Map<String, Object>> findBySql(@Param("var1") String var1);

    Map<String, Object> findUniqueBySql( @Param("var1") String var1);
}
