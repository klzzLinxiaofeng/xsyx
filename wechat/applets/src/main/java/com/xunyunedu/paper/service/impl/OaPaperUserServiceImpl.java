package com.xunyunedu.paper.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.paper.condition.OaPaperUserCondition;
import com.xunyunedu.paper.dao.OaPaperUserDao;
import com.xunyunedu.paper.pojo.OaPaper;
import com.xunyunedu.paper.pojo.OaPaperUser;
import com.xunyunedu.paper.service.OaPaperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OaPaperUserServiceImpl implements OaPaperUserService {

    @Autowired
    OaPaperUserDao dao;


    @Override
    public List<OaPaperUser> getByCondition(OaPaperUserCondition condition){
        return dao.selectByCondition(condition);
    }


    @Override
    public OaPaperUser getOneByCondition(OaPaperUserCondition condition){
        List<OaPaperUser> list = dao.selectByCondition(condition);
        if(list == null){
            return null;
        }
        return list.get(0);
    }

    @Override
    public ApiResult add(OaPaperUser oaPaperUser){
        return dao.insert(oaPaperUser)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public ApiResult update(OaPaperUser oaPaperUser){
        return dao.update(oaPaperUser)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }
}
