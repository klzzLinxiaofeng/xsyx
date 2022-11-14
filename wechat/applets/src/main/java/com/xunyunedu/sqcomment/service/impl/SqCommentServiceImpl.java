package com.xunyunedu.sqcomment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.resource.pojo.ResResource;
import com.xunyunedu.resource.service.ResResourceService;
import com.xunyunedu.sqcomment.condition.SqCommentCondition;
import com.xunyunedu.sqcomment.dao.SqCommentDao;
import com.xunyunedu.sqcomment.pojo.SqComment;
import com.xunyunedu.sqcomment.service.SqCommentService;
import com.xunyunedu.sqcomment.util.ObjectTypeContansUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SqCommentServiceImpl implements SqCommentService {


    @Autowired
    SqCommentDao dao;

    @Autowired
    ResResourceService resResourceService;

    @Override
    public PageInfo page(PageCondition<SqCommentCondition> condition){

        PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(dao.selectByCondition(condition.getCondition()));
        return pageInfo;
    }


    @Override
    public ApiResult add(SqComment comment){

        ResResource resResource =
                resResourceService.getById(comment.getResourceId());

        if(resResource == null){
            ApiResult apiResult = ApiResult.of(ResultCode.SAVE_FAIL);
            apiResult.setMsg("找不到该资源");
            return apiResult;
        }

        comment.setObjectType(ObjectTypeContansUtil.convertObjectType(resResource.getResType()));
        comment.setObjectId(resResource.getObjectId());
        comment.setPostTime(new Date());
        if(dao.insert(comment)<=0){return ApiResult.of(ResultCode.SAVE_FAIL);}

        return ApiResult.of(ResultCode.SUCCESS);
    }
}
