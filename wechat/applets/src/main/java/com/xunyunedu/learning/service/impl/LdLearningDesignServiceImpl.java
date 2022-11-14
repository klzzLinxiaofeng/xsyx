package com.xunyunedu.learning.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.learning.dao.LdLearningDesignDao;
import com.xunyunedu.learning.pojo.LdLearningDesign;
import com.xunyunedu.learning.service.LdLearningDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LdLearningDesignServiceImpl implements LdLearningDesignService {

    @Autowired
    LdLearningDesignDao dao;

    @Override
    public LdLearningDesign getById(Integer id){
        return dao.selectById(id);
    }

    @Override
    public ApiResult add(LdLearningDesign ldLearningDesign){
        return dao.insert(ldLearningDesign)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

}
