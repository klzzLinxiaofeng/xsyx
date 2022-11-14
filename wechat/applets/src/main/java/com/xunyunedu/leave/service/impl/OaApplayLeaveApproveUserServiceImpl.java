package com.xunyunedu.leave.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.leave.dao.OaApplayLeaveApproveUserDao;
import com.xunyunedu.leave.pojo.OaApplayLeaveApproveUser;
import com.xunyunedu.leave.service.OaApplayLeaveApproveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OaApplayLeaveApproveUserServiceImpl implements OaApplayLeaveApproveUserService {

    @Autowired
    OaApplayLeaveApproveUserDao dao;


    @Override
    public ApiResult add(OaApplayLeaveApproveUser oaApplayLeaveApproveUser){
        return dao.insert(oaApplayLeaveApproveUser)>0? ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

}
