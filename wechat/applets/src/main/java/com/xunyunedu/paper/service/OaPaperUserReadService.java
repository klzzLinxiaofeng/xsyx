package com.xunyunedu.paper.service;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.paper.param.OaPaperInsertParam;
import com.xunyunedu.paper.pojo.OaPaperUserRead;
import com.xunyunedu.teacher.pojo.TeacherPojo;

import java.util.List;

public interface OaPaperUserReadService {
    ApiResult add(OaPaperUserRead oaPaperUserRead);

    ApiResult add(OaPaperInsertParam param, List<Integer> userIds, Integer paperId);

    void updateToRead(Integer userId,Integer paperId);

    boolean isRead(Integer userId,Integer paperId);
}
