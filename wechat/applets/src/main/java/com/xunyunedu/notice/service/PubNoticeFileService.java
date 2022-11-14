package com.xunyunedu.notice.service;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.notice.pojo.PubNoticeFile;

import java.util.List;

public interface PubNoticeFileService {
    ApiResult add(PubNoticeFile noticeFile);

    List<PubNoticeFile> selectByNoticeId(Integer noticeId);
}
