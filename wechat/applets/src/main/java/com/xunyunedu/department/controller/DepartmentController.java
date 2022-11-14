package com.xunyunedu.department.controller;

import com.xunyunedu.department.condition.DepartmentSearchCondition;
import com.xunyunedu.department.service.DepartmentService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.teacher.condition.TeacherSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理
 */
@RestController
@RequestMapping("/depart")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    /**
     * 根据查询条件获取所有部门
     * @param condition
     * @return
     */
    @PostMapping("/getAll")
    @Authorization
    public ApiResult teacherPage(@RequestBody DepartmentSearchCondition condition){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.getAll(condition));
        return apiResult;
    }

}
