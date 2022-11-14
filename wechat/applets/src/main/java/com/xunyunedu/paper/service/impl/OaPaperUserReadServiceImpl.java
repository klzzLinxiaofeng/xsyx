package com.xunyunedu.paper.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.paper.dao.OaPaperUserReadDao;
import com.xunyunedu.paper.param.OaPaperInsertParam;
import com.xunyunedu.paper.pojo.OaPaperUserRead;
import com.xunyunedu.paper.service.OaPaperUserReadService;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OaPaperUserReadServiceImpl implements OaPaperUserReadService {

    @Autowired
    OaPaperUserReadDao dao;

    @Override
    public ApiResult add(OaPaperUserRead oaPaperUserRead){
        return dao.insert(oaPaperUserRead)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public ApiResult add(OaPaperInsertParam param, List<Integer> userIds, Integer paperId){
        Date now=new Date();
        for (Integer userId : userIds) {
            OaPaperUserRead puRead = new OaPaperUserRead();
            puRead.setOwnerId(param.getSchoolId());
            puRead.setOwnerType(param.getGroupType());
            puRead.setPaperId(paperId);
            puRead.setReadStatus(false);
            puRead.setUserId(userId);
            puRead.setCreateDate(now);
            puRead.setModifyDate(now);
            add(puRead);
        }

        return ApiResult.of(ResultCode.SUCCESS);
    }

    @Override
    public void updateToRead(Integer receiverId, Integer paperId) {
        dao.updateToRead(receiverId,paperId);
    }

    @Override
    public boolean isRead(Integer userId, Integer paperId) {
        return dao.isExists(userId,paperId)>0;
    }
}
