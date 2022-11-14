package com.xunyunedu.student.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.student.pojo.PerformancePojo;
import com.xunyunedu.student.pojo.TeamDO;
import com.xunyunedu.student.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生成长 评价
 *
 * @author: yhc
 * @Date: 2021/4/8 14:55
 * @Description:
 */

@RequestMapping("/stuAsses")
@RestController
public class StudentAssessController {

    @Autowired
    private PerformanceService performanceService;

    private static String assesName = "pj_performance_assess";
    private static String assesStuName = "pj_performance_assess_stu";


    /**
     * 分页获取发布历史(教师 学生通用)
     *
     * @param pojo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getReleaseHistory")
    @Authorization
    public ApiResult getReleaseHistory(PerformancePojo pojo, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageInfo<PerformancePojo> pojoPageInfo = performanceService.getTeacherReleaseShowList(pojo, pageNum, pageSize, assesName, assesStuName);
        return new ApiResult(ResultCode.SUCCESS, pojoPageInfo);
    }

    /**
     * 发布表现
     *
     * @param performancePojo
     * @return
     */
    @PostMapping("/releaseShow")
    @Authorization
    public ApiResult releaseShow(@Validated @RequestBody PerformancePojo performancePojo) {
        performanceService.addMatureShow(performancePojo, assesName, assesStuName);
        return new ApiResult(ResultCode.SUCCESS);
    }


    /**
     * 获取发布详情
     *
     * @param pojo
     * @return
     */
    @GetMapping("/getReleaseDetails")
    @Authorization
    public ApiResult getReleaseDetails(PerformancePojo pojo) {
        // 详情id 老师/学生 的id
        if (pojo == null || pojo.getId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PerformancePojo pojoPageInfo = performanceService.getReleaseDetails(pojo, assesName, assesStuName);
        return new ApiResult(ResultCode.SUCCESS, pojoPageInfo);
    }

    /**
     * 获取老师下的班级和学生
     *
     * @param teacherId
     * @return
     */
    @GetMapping("/getTeamStus")
    @Authorization
    public ApiResult getTeamStus(Integer teacherId) {
        // 详情id 老师/学生 的id
        if (teacherId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<TeamDO> list = performanceService.getTeamStus(teacherId);
        return new ApiResult(ResultCode.SUCCESS, list);
    }
}
