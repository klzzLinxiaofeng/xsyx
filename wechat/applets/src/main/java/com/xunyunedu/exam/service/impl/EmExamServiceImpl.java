package com.xunyunedu.exam.service.impl;

import com.xunyunedu.exam.dao.EmExamDao;
import com.xunyunedu.exam.pojo.EmExam;
import com.xunyunedu.exam.service.EmExamService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmExamServiceImpl implements EmExamService {

    @Autowired
    EmExamDao dao;


    @Override
    public ApiResult add(EmExam emExam){
        return dao.insert(emExam)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public EmExam getById(Integer id){
        return dao.selectById(id);
    }

}
