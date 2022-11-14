package com.xunyunedu.leave.service;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.leave.condition.OaApplayLeaveDepartmentCountCondition;
import com.xunyunedu.leave.param.OaApplayLeaveInsertParam;
import com.xunyunedu.leave.pojo.OaApplayLeave;
import com.xunyunedu.leave.pojo.OaApplayLeaveDepartmentCount;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OaApplayLeaveDepartmentCountService {
    ApiResult add(OaApplayLeaveDepartmentCount oaApplayLeaveDepartmentCount);

    ApiResult update(OaApplayLeaveDepartmentCount oaApplayLeaveDepartmentCount);

    List<OaApplayLeaveDepartmentCount> getByCondition(OaApplayLeaveDepartmentCountCondition condition);

    OaApplayLeaveDepartmentCount getOneByCondition(OaApplayLeaveDepartmentCountCondition condition);

    /*
     * 如果当前的请假对象所属部门在oa_applayLeave_department_count表中已经存在数据，
     * 则只要对该数据的count字段进行+1操作
     * 否则在该表中新增一条数据
     * TODO 事务啊
     */
    // 保存发布请假对象有部门的相关部门的相关数据
    @Transactional
    void addOrUpdate(OaApplayLeaveInsertParam param, OaApplayLeave leave);
}
