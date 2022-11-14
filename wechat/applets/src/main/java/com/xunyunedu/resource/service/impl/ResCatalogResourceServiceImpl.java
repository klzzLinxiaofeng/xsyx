package com.xunyunedu.resource.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.resource.dao.ResCatalogResourceDao;
import com.xunyunedu.resource.pojo.ResCatalogResource;
import com.xunyunedu.resource.service.ResCatalogResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResCatalogResourceServiceImpl implements ResCatalogResourceService {

    @Autowired
    ResCatalogResourceDao dao;


    @Override
    public ApiResult add(ResCatalogResource resCatalogResource){

        return dao.insert(resCatalogResource)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);

    }



}
