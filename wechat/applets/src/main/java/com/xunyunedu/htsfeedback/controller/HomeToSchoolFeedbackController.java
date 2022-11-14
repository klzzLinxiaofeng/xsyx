package com.xunyunedu.htsfeedback.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.htsfeedback.pojo.HtsFeedbackPojo;
import com.xunyunedu.htsfeedback.service.HtsFeedbackService;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.student.pojo.ParentPojo;
import com.xunyunedu.student.pojo.StudentPojo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 家校反馈
 *
 * @author Eternityhua
 * @create 2020-12-09 0:47
 */
@RestController
@RequestMapping("/htsFeedback")
public class HomeToSchoolFeedbackController {

    @Autowired
    private HtsFeedbackService htsFeedbackService;


    /**
     * 反馈内容发布
     *
     * @param htsFeedbackPojo
     * @return
     */
    @PostMapping("/addFeedback")
    @Authorization
    public ApiResult addFeedback(@RequestBody HtsFeedbackPojo htsFeedbackPojo) {

        if (htsFeedbackPojo == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }


        String uuids = null;
        List<String> uuid = htsFeedbackPojo.getUuids();
        if (CollUtil.isNotEmpty(uuid)) {
            uuids = StringUtils.join(uuid.toArray(), ",");

        }
        //@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")

        StudentPojo student = htsFeedbackService.getStudent(htsFeedbackPojo.getStuId());
        if (student != null) {
            ParentPojo parent = htsFeedbackService.getParentByStuId(htsFeedbackPojo.getStuId());
            htsFeedbackPojo.setCreateDate(new Date());
            htsFeedbackPojo.setClassName(student.getTeamName());
            htsFeedbackPojo.setSchoolId(student.getSchoolId());
            htsFeedbackPojo.setName(student.getName());
            htsFeedbackPojo.setPhone(parent.getMobile());
            htsFeedbackPojo.setParentName(parent.getName());
            htsFeedbackPojo.setIsDelete(0);
            htsFeedbackPojo.setIsReply(0);
            htsFeedbackPojo.setUuid(uuids);
            htsFeedbackService.addFeedback(htsFeedbackPojo);
            return new ApiResult(ResultCode.SUCCESS);
        }
        throw new BusinessRuntimeException(ResultCode.STU_NO_EXISTS);

    }


    /**
     * 反馈内容查询
     *
     * @param htsFeedbackPojo
     * @return
     */
    @GetMapping("/getFeedbackMsg")
    @Authorization
    public ApiResult getFeedbackMsg(HtsFeedbackPojo htsFeedbackPojo) {
        if (htsFeedbackPojo == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<HtsFeedbackPojo> list = htsFeedbackService.getFeedbackMsg(htsFeedbackPojo);

        return new ApiResult(ResultCode.SUCCESS, list);
    }


    /**
     * 根据学生id查询家校反馈内容
     * @param stuId
     * @return
     */
    @GetMapping("/getFeedbackByStuId")
    @Authorization
    public ApiResult getFeedbackbyStuId(@Param("stuId") Integer stuId){
        List<HtsFeedbackPojo> Pojo = htsFeedbackService.queryByStuId(stuId);
        return new ApiResult(ResultCode.SUCCESS, Pojo);

    }
}