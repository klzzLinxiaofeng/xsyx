package com.xunyunedu.department.service;

import com.xunyunedu.department.condition.DepartmentSearchCondition;

import java.util.List;
import java.util.Map;

/**
 * @author chenjiaxin
 */
public interface DepartmentService {

    List<Map<String,Object>> getAll(DepartmentSearchCondition condition);

    Integer getDepartIdByUserId(Integer userId);

}
