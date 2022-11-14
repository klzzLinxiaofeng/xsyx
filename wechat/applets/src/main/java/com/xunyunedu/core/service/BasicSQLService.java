package com.xunyunedu.core.service;


import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BasicSQLService {
    int update(String sql);
    int updateBatch(String... sqls);
    int updateBatchByStr(String batchSqlStr);
    Map<String,Object> getStudentParentUserInfo(Integer stuId);
    List<Map<String,Object>> find(String sql);
    PageInfo<Map<String,Object>> findByPaging(String sql,int pageNum,int pageSize);
    long findUniqueLong(String sql);
    Object findUnique(String sql);
    String getNowSchoolYear();
}
