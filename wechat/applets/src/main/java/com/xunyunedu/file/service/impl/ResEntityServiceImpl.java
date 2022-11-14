package com.xunyunedu.file.service.impl;

import com.xunyunedu.file.dao.ResEntityDao;
import com.xunyunedu.file.dao.ResEntityFileDao;
import com.xunyunedu.file.pojo.ResEntity;
import com.xunyunedu.file.service.ResEntityFileService;
import com.xunyunedu.file.service.ResEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResEntityServiceImpl implements ResEntityService {

    @Autowired
    ResEntityDao dao;

    @Override
    public ResEntity getById(Integer id){
        return dao.selectById(id);
    }

}
