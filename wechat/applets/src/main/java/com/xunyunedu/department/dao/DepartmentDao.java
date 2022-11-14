package com.xunyunedu.department.dao;

import com.xunyunedu.department.condition.DepartmentSearchCondition;

import java.util.List;
import java.util.Map;

/**
 * @author chenjiaxin
 */
public interface DepartmentDao {
    
    List<Map<String,Object>> selectAll(DepartmentSearchCondition condition);
    Integer selectDepartIdByUserId(Integer userId);
}
