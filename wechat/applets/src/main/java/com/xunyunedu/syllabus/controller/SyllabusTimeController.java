package com.xunyunedu.syllabus.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.school.service.PjSchoolService;
import com.xunyunedu.syllabus.pojo.SyllabusTime;
import com.xunyunedu.syllabus.service.SyllabusTimeService;
import com.xunyunedu.team.pojo.SchoolTermPojo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 学校作息时间表(SyllabusTime)表控制层
 *
 * @author sjz
 * @since 2021-04-07 11:13:24
 */
@RestController
@RequestMapping("/syllabusTime")
public class SyllabusTimeController {
    /**
     * 服务对象
     */
    @Resource
    private SyllabusTimeService syllabusTimeService;

    @Resource
    PjSchoolService pjSchoolService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public SyllabusTime selectOne(Integer id) {
        return this.syllabusTimeService.queryById(id);
    }

    /**
     * 查询课程时间
     *
     * @param schoolId
     * @return
     */
    @GetMapping("/getBySchoolId")
    public ApiResult getBySchoolId(@RequestParam Integer schoolId) {
        JSONObject result = new JSONObject();
        //根据当前时间和schoolId查询学年码
        Date date = new Date();
        String day = DateUtil.format(date,"yyyy-MM-dd");
        SchoolTermPojo schoolTermPojo = pjSchoolService.getBySchoolId(schoolId,day);
        if (schoolTermPojo != null){
            //根据学年码和schoolId查询课程时间
            String code = schoolTermPojo.getCode();
            SyllabusTime syllabusTime = this.syllabusTimeService.getBySchoolId(schoolId,code);
            if (syllabusTime != null){
                String lessonTimes = syllabusTime.getLessonTimes();
                JSONArray jsonArray = JSON.parseArray(lessonTimes);
                result.put("lessonTimes",jsonArray);
            }
        }
        return new ApiResult(ResultCode.SUCCESS,result);
    }

}
