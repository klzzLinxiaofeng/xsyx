package com.xunyunedu.student.controller;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.student.pojo.*;
import com.xunyunedu.student.service.MicroManagerService;
import com.xunyunedu.student.service.impl.LockMicroClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 微课管理
 *
 * @author: yhc
 * @Date: 2020/10/17 16:51
 * @Description:
 */
@RestController
@RequestMapping("/micro")
public class MicroManageController {

    @Autowired
    private MicroManagerService microManagerService;

    @Autowired
    private LockMicroClass lockMicroClass;

    /**
     * 微课类型
     *
     * @param schoolId 学校id
     * @return
     */
    @GetMapping("/getMicroType")
    @Authorization
    public ApiResult getMicroType(@RequestParam("schoolId") Integer schoolId) {
        if (schoolId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<MicroTypePojo> list = microManagerService.getMicroTypeBySchoolId(schoolId);
        return new ApiResult(ResultCode.SUCCESS, list);
    }


    /**
     * 微课年级
     *
     * @param schoolId 学校id
     * @return
     */
    @GetMapping("/getMicroGrade")
    @Authorization
    public ApiResult getMicroGrade(@RequestParam("schoolId") Integer schoolId) {
        if (schoolId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<GradePojo> list = microManagerService.getMicroGradeBySchoolId(schoolId);
        return new ApiResult(ResultCode.SUCCESS, list);
    }


    /**
     * 微课列表 和微课详情接口
     *
     * @param commonPojo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getMicroList")
    @Authorization
    public ApiResult getStuAsking(CommonPojo commonPojo, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (commonPojo == null || commonPojo.getSchoolId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PageInfo<MicroManagerPojo> list = microManagerService.getMicroByCondition(commonPojo, pageNum, pageSize);

        return new ApiResult(ResultCode.SUCCESS, list);
    }

    /**
     * 获取当前微课的评论信息
     *
     * @param userCommentsPojo 需要微课id 学校id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getMicroComments")
    @Authorization
    public ApiResult getMicroComments(UserCommentsPojo userCommentsPojo, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (userCommentsPojo == null || userCommentsPojo.getSchoolId() == null || userCommentsPojo.getMicroId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PageInfo<UserCommentsPojo> list = microManagerService.getMicroCommentsMsg(userCommentsPojo, pageNum, pageSize);
        return new ApiResult(ResultCode.SUCCESS, list);
    }

    /**
     * 新增评论
     *
     * @param userCommentsPojo 评论内容 必填:学校id, 微课id, 用户id,评论内容，用户角色
     * @return
     */
    @PostMapping("/addComment")
    @Authorization
    public ApiResult addCommentsInfo(@RequestBody UserCommentsPojo userCommentsPojo) {
        if (userCommentsPojo == null || userCommentsPojo.getSchoolId() == null || userCommentsPojo.getMicroId() == null
                || userCommentsPojo.getUserId() == null || userCommentsPojo.getUserType() == null || StrUtil.isEmpty(userCommentsPojo.getComments())) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        microManagerService.addComments(userCommentsPojo);
        return new ApiResult(ResultCode.SUCCESS);
    }


    /**
     * 获取当前学生点赞和收藏状态和点赞、收藏数量
     *
     * @param collectionCommentsPojo
     * @return
     */
    @GetMapping("/getCollectType")
    @Authorization
    public ApiResult getCollectType(CollectionCommentsPojo collectionCommentsPojo) {
        if (collectionCommentsPojo == null || collectionCommentsPojo.getSchoolId() == null || collectionCommentsPojo.getMicroId() == null
                || collectionCommentsPojo.getUserId() == null || collectionCommentsPojo.getUserType() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        return new ApiResult(ResultCode.SUCCESS, microManagerService.getCollectType(collectionCommentsPojo));
    }


    /**
     * 点赞收藏功能
     *
     * @return
     */
    @PostMapping("/addKeepCollect")
    @Authorization
    public ApiResult addKeepCollect(@RequestBody CollectionCommentsPojo collectionCommentsPojo) {
        if (collectionCommentsPojo == null || collectionCommentsPojo.getSchoolId() == null || collectionCommentsPojo.getMicroId() == null
                || collectionCommentsPojo.getUserId() == null || collectionCommentsPojo.getUserType() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        lockMicroClass.addKeepCollect(collectionCommentsPojo);
        return new ApiResult(ResultCode.SUCCESS);
    }


    /**
     * 获取我的收藏
     *
     * @param commonPojo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getMyCollection")
    @Authorization
    public ApiResult getMyCollection(CommonPojo commonPojo, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (commonPojo == null || commonPojo.getSchoolId() == null || commonPojo.getUserType() == null || commonPojo.getUserId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PageInfo<MicroManagerPojo> list = microManagerService.getMyCollection(commonPojo, pageNum, pageSize);
        return new ApiResult(ResultCode.SUCCESS, list);
    }

}