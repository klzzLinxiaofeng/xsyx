package com.xunyunedu.department.service.impl;

import com.xunyunedu.department.condition.DepartmentSearchCondition;
import com.xunyunedu.department.dao.DepartmentDao;
import com.xunyunedu.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao dao;

    @Override
    public List<Map<String, Object>> getAll(DepartmentSearchCondition condition) {
        return dao.selectAll(condition);
    }

    @Override
    public Integer getDepartIdByUserId(Integer userId) {
        return dao.selectDepartIdByUserId(userId);
    }
}
