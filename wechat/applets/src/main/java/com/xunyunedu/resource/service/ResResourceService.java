package com.xunyunedu.resource.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.file.pojo.ResEntityFile;
import com.xunyunedu.resource.condition.ResResourceCondition;
import com.xunyunedu.resource.condition.ResourceCommentCondition;
import com.xunyunedu.resource.param.ResourceUploadParam;
import com.xunyunedu.resource.pojo.ResResource;
import com.xunyunedu.resource.pojo.detail.ResResourceDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface ResResourceService {

    PageInfo page(PageCondition<ResResourceCondition> condition);

    ResResourceDetail getDetailById(Integer userId,Integer id);

    ResResource getById(Integer id);


    ApiResult uploadResource(ResourceUploadParam param);

    ApiResult add(ResResource res);


    boolean isLike(Integer userId,Integer resId);
    boolean addLikeRes(Integer userId,Integer resId);
    boolean updateCancelLike(Integer userId,Integer resId);
    boolean isFavorite(Integer userId,Integer resId);
    boolean addFavoriteRes(Integer userId,Integer resId);
    boolean updateCancelFavorite(Integer userId,Integer resId);
    boolean commentResource(Integer resId,Integer userId,String userName,String comment);

    boolean deleteById(Integer resId);

    long likeCount(Integer userId,Integer resId);

    PageInfo pagingQueryComment(PageCondition<ResourceCommentCondition> condition);



}
