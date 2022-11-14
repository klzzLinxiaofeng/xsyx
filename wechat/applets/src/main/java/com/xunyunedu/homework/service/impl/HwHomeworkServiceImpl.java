package com.xunyunedu.homework.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.homework.dao.HwHomeworkDao;
import com.xunyunedu.homework.pojo.HwHomework;
import com.xunyunedu.homework.service.HwHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HwHomeworkServiceImpl implements HwHomeworkService {

    @Autowired
    HwHomeworkDao dao;

    @Override
    public ApiResult add(HwHomework homework){
        return dao.insert(homework)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }
}
