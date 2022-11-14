package com.xunyunedu.sqcomment.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.sqcomment.condition.SqCommentCondition;
import com.xunyunedu.sqcomment.pojo.SqComment;
import com.xunyunedu.sqcomment.service.SqCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 评论模块
 */
@RestController
@RequestMapping("/comment")
public class SqCommentController {

    @Autowired
    SqCommentService service;

    /**
     * 评论分页
     * @param condition
     * @return
     */
    @PostMapping("/page")
    ApiResult<PageInfo<List<SqComment>>> page(@RequestBody PageCondition<SqCommentCondition> condition){

        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.page(condition));
        return apiResult;
    }


    /**
     * 用户添加评论
     * @param comment
     * @return
     */
    @PostMapping
    @Authorization
    ApiResult add(@RequestBody SqComment comment){

        if(comment.getResourceId() == null){
          ApiResult apiResult = ApiResult.of(ResultCode.SAVE_FAIL);
          apiResult.setMsg("资源ID必须填");
          return apiResult;
        }
        return service.add(comment);
    }


}
