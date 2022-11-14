package com.xunyunedu.leave.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.leave.condition.OaApplayLeaveDepartmentCountCondition;
import com.xunyunedu.leave.dao.OaApplayLeaveDepartmentCountDao;
import com.xunyunedu.leave.param.OaApplayLeaveInsertParam;
import com.xunyunedu.leave.pojo.OaApplayLeave;
import com.xunyunedu.leave.pojo.OaApplayLeaveDepartmentCount;
import com.xunyunedu.leave.service.OaApplayLeaveDepartmentCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OaApplayLeaveDepartmentCountServiceImpl implements OaApplayLeaveDepartmentCountService {

    @Autowired
    OaApplayLeaveDepartmentCountDao dao;


    @Override
    public ApiResult add(OaApplayLeaveDepartmentCount oaApplayLeaveDepartmentCount){
        return dao.insert(oaApplayLeaveDepartmentCount)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public ApiResult update(OaApplayLeaveDepartmentCount oaApplayLeaveDepartmentCount){
        return dao.update(oaApplayLeaveDepartmentCount)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public List<OaApplayLeaveDepartmentCount> getByCondition(OaApplayLeaveDepartmentCountCondition condition){
        return dao.selectByCondition(condition);
    }

    @Override
    public OaApplayLeaveDepartmentCount getOneByCondition(OaApplayLeaveDepartmentCountCondition condition) {

        List<OaApplayLeaveDepartmentCount> list = dao.selectByCondition(condition);
        if(list == null){
            return null;
        }
        return list.get(0);
    }


    /*
     * 如果当前的请假对象所属部门在oa_applayLeave_department_count表中已经存在数据，
     * 则只要对该数据的count字段进行+1操作
     * 否则在该表中新增一条数据
     * TODO 事务啊
     */
    // 保存发布请假对象有部门的相关部门的相关数据
    @Override
    @Transactional
    public void addOrUpdate(OaApplayLeaveInsertParam param, OaApplayLeave leave) {
        OaApplayLeaveDepartmentCountCondition dcondition = new OaApplayLeaveDepartmentCountCondition();
        dcondition.setDepartmentId(param.getPropserDepartmentId());
        dcondition.setOwnerId(param.getSchoolId());
        dcondition.setOwnerType("1");
        dcondition.setAuditStatus(leave.getAuditStatus());
        OaApplayLeaveDepartmentCount applayLeaveDepartmentCount = this.getOneByCondition(dcondition);

        if (applayLeaveDepartmentCount != null) {
            applayLeaveDepartmentCount.setCount(applayLeaveDepartmentCount.getCount()+1);
            this.update(applayLeaveDepartmentCount);
        } else {

            applayLeaveDepartmentCount = new OaApplayLeaveDepartmentCount();
            applayLeaveDepartmentCount.setDepartmentId(param.getPropserDepartmentId());
            applayLeaveDepartmentCount.setOwnerId(param.getSchoolId());
            applayLeaveDepartmentCount.setOwnerType("1");
            applayLeaveDepartmentCount.setCount(1);
            applayLeaveDepartmentCount.setAuditStatus(leave.getAuditStatus());
            this.add(applayLeaveDepartmentCount);
        }
    }
}
