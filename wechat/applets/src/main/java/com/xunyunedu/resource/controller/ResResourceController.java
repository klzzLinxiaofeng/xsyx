package com.xunyunedu.resource.controller;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.resource.condition.ResResourceCondition;
import com.xunyunedu.resource.condition.ResourceCommentCondition;
import com.xunyunedu.resource.param.ResouceUserParam;
import com.xunyunedu.resource.param.ResourceCommentParam;
import com.xunyunedu.resource.param.ResourceUploadParam;
import com.xunyunedu.resource.pojo.ResResource;
import com.xunyunedu.resource.pojo.detail.ResResourceDetail;
import com.xunyunedu.resource.service.ResResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源管理(校本资源、微盘)
 * @author edison
 */
@RestController
@RequestMapping("/resource")
public class ResResourceController {

    @Autowired
    ResResourceService service;

    /**
     * 分页查询资源
     * @param condition
     * @return
     */
    @PostMapping("/page")
    public ApiResult<PageInfo<List<ResResource>>> page(@RequestBody PageCondition<ResResourceCondition> condition){
        ResResourceCondition resCondition=condition.getCondition();
        if(condition==null || resCondition==null){
            return getErrorResult("缺少condition查询参数");
        }

        if(resCondition.getIsPersonal()==null){
            return getErrorResult("isPersonal不可为空");
        }
        //个人资源
        if(resCondition.getIsPersonal()){
            if(resCondition.getUserId()==null){
                return getErrorResult("当前条件下，userId不可为空");
            }
            resCondition.setResType(null);
            resCondition.setIsFav(null);
        }else{
            //校本资源
            //是否查询收藏
            if(new Boolean(true).equals(resCondition.getIsFav())){
                if(resCondition.getUserId()==null){
                    return getErrorResult("当前条件下，userId不可为空");
                }
            }else{
                resCondition.setIsFav(null);
                resCondition.setUserId(null);
            }

        }
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.page(condition));
        return apiResult;
    }




    /**
     * 分页查询资源评论
     * @param condition
     * @return
     */
    @PostMapping("/pageComment")
    public ApiResult<PageInfo<List<ResourceCommentCondition>>> pageComment(@RequestBody PageCondition<ResourceCommentCondition> condition){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.pagingQueryComment(condition));
        return apiResult;
    }

    /**
     * 评论资源
     */
    @PostMapping("/commentRes")
    public ApiResult commentRes(@RequestBody ResourceCommentParam param){
        service.commentResource(param.getResId(),param.getUserId(),param.getUserName(),param.getCommentContent());
        return ApiResult.of(ResultCode.SUCCESS);
    }


    /**
     * 查询资源详情
     * @return
     */
    @PostMapping("/detail")
    public ApiResult<ResResourceDetail> getDetailById(@RequestBody ResouceUserParam param){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.getDetailById(param.getUserId(),param.getResId()));
        return apiResult;
    }


    /**
     * 删除资源
     * @return
     */
    @PostMapping("/delete")
    public ApiResult delete(@RequestBody ResouceUserParam param){
        service.deleteById(param.getResId());
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        return apiResult;
    }

    /**
     * 点赞资源
     */
    @PostMapping("/likeRes")
    public synchronized ApiResult likeRes(@RequestBody ResouceUserParam param){
        if(service.isLike(param.getUserId(), param.getResId())){
            ApiResult result=new ApiResult();
            result.setCode(400);
            result.setMsg("已点赞");
            return result;
        }
        service.addLikeRes(param.getUserId(), param.getResId());
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 取消点赞
     */
    @PostMapping("/cancelLike")
    public ApiResult cancelLike(@RequestBody ResouceUserParam param){
        service.updateCancelLike(param.getUserId(), param.getResId());
        return ApiResult.of(ResultCode.SUCCESS);
    }


    /**
     * 收藏资源
     */
    @PostMapping("/collectRes")
    public synchronized ApiResult collectRes(@RequestBody ResouceUserParam param){
        if(service.isFavorite(param.getUserId(), param.getResId())){
            ApiResult result=new ApiResult();
            result.setCode(400);
            result.setMsg("已收藏");
            return result;
        }
        service.addFavoriteRes(param.getUserId(), param.getResId());
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 取消收藏
     */
    @PostMapping("/cancelCollect")
    public ApiResult cancelCollect(@RequestBody ResouceUserParam param){
        service.updateCancelFavorite(param.getUserId(), param.getResId());
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 添加资源
     * @param param
     * @return
     */
    @PostMapping("/add")
    public ApiResult uploadResource(@RequestBody ResourceUploadParam param){
        ApiResult apiResult = ApiResult.of(ResultCode.PARAMS_IS_NULL);
        if(StrUtil.isEmpty(param.getFileUuid())){
            apiResult.setMsg("fileUuid必须填写");
            return apiResult;
        }
        return service.uploadResource(param);
    }

    private ApiResult getErrorResult(String msg){
        ApiResult apiResult=new ApiResult();
        apiResult.setMsg(msg);
        apiResult.setCode(400);
        return apiResult;
    }
}
