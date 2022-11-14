package com.xunyunedu.PublishAndAcceptJob.controller;

import com.xunyunedu.PublishAndAcceptJob.pojo.TeamPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.TeamTeacherPojo;
import com.xunyunedu.PublishAndAcceptJob.service.TeamTeacherService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 老师发布作业控制层实现类
 * @author lee
 * @Date 2020/12/06
 */
@RestController
@RequestMapping("/TeamTeacher")
public class TeamTeacherController {

    @Autowired
    private TeamTeacherService teamTeacherService;


    /**
     * 根据老师id查询对应的学科
     * @param teacherId 老师id
     * @return 返回学科数据
     */
    @GetMapping("/subject")
    @Authorization
    public ApiResult getTeamTeacherSubject(@RequestParam Integer teacherId) {
        if (teacherId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<TeamTeacherPojo> list = teamTeacherService.getTeamTeacherSubject(teacherId);
        return new ApiResult(ResultCode.SUCCESS, list);
    }

    /**
     *
     *实现根据老师id，科目id查询对应班级
     * @Param teacherId
     * @Param subjectId
     * @Return
     */
    @GetMapping("/team")
    @Authorization
    public ApiResult getTeamByTeacherIdAndSubjectId(@RequestParam Integer teacherId,@RequestParam Integer subjectId) {
        if (teacherId == null || subjectId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<TeamPojo> list = teamTeacherService.getTeamByTeacherIdAndSubjectId(teacherId,subjectId);
        return new ApiResult(ResultCode.SUCCESS, list);
    }
}
