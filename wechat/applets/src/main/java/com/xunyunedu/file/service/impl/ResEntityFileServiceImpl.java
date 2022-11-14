package com.xunyunedu.file.service.impl;

import com.xunyunedu.file.dao.ResEntityFileDao;
import com.xunyunedu.file.pojo.ResEntityFile;
import com.xunyunedu.file.service.ResEntityFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResEntityFileServiceImpl implements ResEntityFileService {

    @Autowired
    ResEntityFileDao dao;


    @Override
    public ResEntityFile getById(Integer id) {
        return dao.selectById(id);
    }

    @Override
    public ResEntityFile getByUuid(String uuid){
        return dao.selectByUuid(uuid);
    }
}
