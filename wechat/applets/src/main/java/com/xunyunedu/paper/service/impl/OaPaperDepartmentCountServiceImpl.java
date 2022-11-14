package com.xunyunedu.paper.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.paper.condition.OaPaperDepartmentCountCondition;
import com.xunyunedu.paper.dao.OaPaperDepartmentCountDao;
import com.xunyunedu.paper.param.OaPaperInsertParam;
import com.xunyunedu.paper.pojo.OaPaperDepartmentCount;
import com.xunyunedu.paper.pojo.OaPaperUser;
import com.xunyunedu.paper.service.OaPaperDepartmentCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OaPaperDepartmentCountServiceImpl implements OaPaperDepartmentCountService {

    @Autowired
    OaPaperDepartmentCountDao dao;


    @Override
    public ApiResult add(OaPaperDepartmentCount oaPaperDepartmentCount){
        return dao.insert(oaPaperDepartmentCount)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public ApiResult update(OaPaperDepartmentCount oaPaperDepartmentCount){
        return dao.update(oaPaperDepartmentCount)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }


    @Override
    public List<OaPaperDepartmentCount> getByCondition(OaPaperDepartmentCountCondition condition){
        return dao.selectByCondition(condition);
    }


    @Override
    public OaPaperDepartmentCount getOneByCondition(OaPaperDepartmentCountCondition condition){
        List<OaPaperDepartmentCount> list = dao.selectByCondition(condition);
        if(list == null || list.size()==0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public void addOrUpdate(OaPaperInsertParam param,Integer departId){

        OaPaperDepartmentCountCondition pdcCondition = new OaPaperDepartmentCountCondition();
        pdcCondition.setOwnerId(param.getSchoolId());
        pdcCondition.setOwnerType(param.getGroupType());
        pdcCondition.setIsDeleted(false);
        pdcCondition.setDepartmentId(departId);
        OaPaperDepartmentCount oaPaperDepartmentCount = getOneByCondition(pdcCondition);
        if (oaPaperDepartmentCount != null) {

            oaPaperDepartmentCount.setCount(oaPaperDepartmentCount.getCount()+1);
            oaPaperDepartmentCount.setModifyDate(new Date());
            update(oaPaperDepartmentCount);
        } else {

            oaPaperDepartmentCount = new OaPaperDepartmentCount();
            oaPaperDepartmentCount.setOwnerId(param.getSchoolId());
            oaPaperDepartmentCount.setOwnerType(param.getGroupType());
            oaPaperDepartmentCount.setCount(1);
            oaPaperDepartmentCount.setDepartmentId(departId);
            add(oaPaperDepartmentCount);
        }
    }

}
