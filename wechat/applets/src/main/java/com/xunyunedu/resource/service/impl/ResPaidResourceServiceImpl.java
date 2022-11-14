package com.xunyunedu.resource.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.file.pojo.ResEntityFile;
import com.xunyunedu.resource.dao.ResPaidResourceDao;
import com.xunyunedu.resource.param.ResourceUploadParam;
import com.xunyunedu.resource.pojo.ResPaidResource;
import com.xunyunedu.resource.service.ResPaidResourceService;
import com.xunyunedu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResPaidResourceServiceImpl implements ResPaidResourceService {

    @Autowired
    ResPaidResourceDao dao;


    @Override
    public ResPaidResource getById(Integer id){
        return dao.selectById(id);
    }

    @Override
    public ResPaidResource getByUuid(String uuid){
        return dao.selectByUuid(uuid);
    }

    @Override
    public ApiResult add(ResPaidResource resource){

        return dao.insert(resource)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);

    }


}
