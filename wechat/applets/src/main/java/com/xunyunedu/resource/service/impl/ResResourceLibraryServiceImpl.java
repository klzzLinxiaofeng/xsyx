package com.xunyunedu.resource.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.resource.condition.ResResourceCondition;
import com.xunyunedu.resource.condition.ResResourceLibraryCondition;
import com.xunyunedu.resource.dao.ResResourceLibraryDao;
import com.xunyunedu.resource.param.ResourceUploadParam;
import com.xunyunedu.resource.pojo.ResResourceLibrary;
import com.xunyunedu.resource.service.ResResourceLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResResourceLibraryServiceImpl implements ResResourceLibraryService {

    @Autowired
    ResResourceLibraryDao dao;


    @Override
    public List<ResResourceLibrary> getByCondition(ResResourceLibraryCondition condition){
        return dao.selectByCondition(condition);
    }

    @Override
    public ResResourceLibrary getOneBySchoolIdAndAppId(Integer schoolId, Integer appId) {
        ResResourceLibraryCondition resourceLibraryCondition = new ResResourceLibraryCondition();
        resourceLibraryCondition.setAppId(appId);
        resourceLibraryCondition.setOwerId(schoolId);
        List<ResResourceLibrary> resourceLibraries = getByCondition(resourceLibraryCondition);

        if(resourceLibraries == null){return null;}

        return resourceLibraries.get(0);
    }


    @Override
    public ApiResult add(ResResourceLibrary library){
       return dao.insert(library) > 0 ? ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public ApiResult add(ResourceUploadParam param,String libUuid) {

        ResResourceLibrary resourceLibrary = new ResResourceLibrary();
//        resourceLibrary.setAppId(param.getAppId());
//        resourceLibrary.setOwerId(param.getSchoolId());
//        resourceLibrary.setName(param.getSchoolName());
        resourceLibrary.setUuid(libUuid);// 获取唯一值uuid
        ApiResult apiResult =  add(resourceLibrary);
        return apiResult;
    }


}
