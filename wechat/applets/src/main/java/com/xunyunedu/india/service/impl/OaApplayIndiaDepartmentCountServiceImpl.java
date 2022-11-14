package com.xunyunedu.india.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.india.condition.OaApplayIndiaDepartmentCountCondition;
import com.xunyunedu.india.dao.OaApplayIndiaDepartmentCountDao;
import com.xunyunedu.india.param.OaApplayIndiaInsertParam;
import com.xunyunedu.india.pojo.OaApplayIndia;
import com.xunyunedu.india.pojo.OaApplayIndiaDepartmentCount;
import com.xunyunedu.india.service.OaApplayIndiaDepartmentCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OaApplayIndiaDepartmentCountServiceImpl implements OaApplayIndiaDepartmentCountService {

    @Autowired
    OaApplayIndiaDepartmentCountDao dao;


    @Override
    public List<OaApplayIndiaDepartmentCount> getByCondition(OaApplayIndiaDepartmentCountCondition condition){
        return dao.selectByCondition(condition);
    }

    @Override
    public OaApplayIndiaDepartmentCount getOneByCondition(OaApplayIndiaDepartmentCountCondition condition){

        List<OaApplayIndiaDepartmentCount> list = dao.selectByCondition(condition);
        if(list == null){
            return null;
        }
        return list.get(0);
    }

    @Override
    public ApiResult add(OaApplayIndiaDepartmentCount oaApplayIndiaDepartmentCount){
        return dao.insert(oaApplayIndiaDepartmentCount) > 0 ? ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public ApiResult update(OaApplayIndiaDepartmentCount oaApplayIndiaDepartmentCount){
        return dao.update(oaApplayIndiaDepartmentCount) > 0 ? ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }


    /**
     * 添加 or 更改
     * TODO 事務啊
     * @param param
     * @param applayIndia
     */
    @Override
    @Transactional
    public void saveOrUpdate(OaApplayIndiaInsertParam param, OaApplayIndia applayIndia){

        OaApplayIndiaDepartmentCountCondition dcondition = new OaApplayIndiaDepartmentCountCondition();
        dcondition.setDepartmentId(applayIndia.getDepartmentId());
        dcondition.setOwnerId(param.getSchoolId());
        dcondition.setOwnerType(param.getGroupType());
        dcondition.setIsDeleted(false);
        dcondition.setIndiaStatus("1");
        OaApplayIndiaDepartmentCount applayIndiaDepartmentCount = this.getOneByCondition(dcondition);
        /*判断该条数据是否存在,如果存在，则在该数据的基础上对count字段进行+1操作，
         * 否则对oa_applayindia_department_count表进行新增操作
         */
        if (applayIndiaDepartmentCount != null) {
            //对oa_applayindia_department_count表进行+1操作
            applayIndiaDepartmentCount.setNumber(applayIndiaDepartmentCount.getNumber()+1);
            applayIndiaDepartmentCount.setModifyDate(new Date());
            update(applayIndiaDepartmentCount);

        } else {

            applayIndiaDepartmentCount = new OaApplayIndiaDepartmentCount();
            applayIndiaDepartmentCount.setNumber(1);
            applayIndiaDepartmentCount.setOwnerId(param.getSchoolId());
            applayIndiaDepartmentCount.setOwnerType(param.getGroupType());
            applayIndiaDepartmentCount.setDepartmentId(param.getDepartmentId());
            applayIndiaDepartmentCount.setIndiaStatus(applayIndia.getIndiaStatus());
            applayIndiaDepartmentCount.setCreateDate(new Date());
            applayIndiaDepartmentCount.setModifyDate(new Date());
            add(applayIndiaDepartmentCount);
        }

    }
}
