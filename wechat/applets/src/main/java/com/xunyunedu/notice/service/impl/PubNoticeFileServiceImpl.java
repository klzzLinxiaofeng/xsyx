package com.xunyunedu.notice.service.impl;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.notice.dao.PubNoticeFileDao;
import com.xunyunedu.notice.pojo.PubNoticeFile;
import com.xunyunedu.notice.service.PubNoticeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PubNoticeFileServiceImpl implements PubNoticeFileService {

    @Autowired
    PubNoticeFileDao dao;


    @Override
    public ApiResult add(PubNoticeFile noticeFile){
       return dao.insert(noticeFile)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public List<PubNoticeFile> selectByNoticeId(Integer noticeId){
        return dao.selectByNoticeId(noticeId);
    }


}
