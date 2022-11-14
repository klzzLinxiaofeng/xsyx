package com.xunyunedu.syllabus.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.school.service.PjSchoolService;
import com.xunyunedu.syllabus.dto.SyllabusDTO;
import com.xunyunedu.syllabus.pojo.SyllabusLessonPojo;
import com.xunyunedu.syllabus.pojo.SyllabusPojo;
import com.xunyunedu.syllabus.service.SyllabusLessonService;
import com.xunyunedu.syllabus.service.SyllabusService;
import com.xunyunedu.team.pojo.SchoolTermPojo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 课程表(syllabusController)表控制层
 *
 * @author sjz
 * @since 2021-03-30 14:20:30
 */
@RestController
@RequestMapping("/syllabus")
public class syllabusController {


    @Resource
    SyllabusService syllabusService;

    @Resource
    SyllabusLessonService syllabusLessonService;

    @Resource
    PjSchoolService pjSchoolService;

    /**
     * 查询班级课表
     *
     * @param syllabusDTO
     * @return
     */
    @GetMapping("/getSyllabus")
    public ApiResult getSyllabus(SyllabusDTO syllabusDTO) {
        JSONArray monday = new JSONArray();
        JSONArray tuesday = new JSONArray();
        JSONArray wednesday = new JSONArray();
        JSONArray thursday = new JSONArray();
        JSONArray friday = new JSONArray();
        JSONObject jsonObject = new JSONObject(6,true);
        //根据学校id查询学年
        SchoolTermPojo schoolTermPojo = pjSchoolService.getBySchoolId(syllabusDTO.getSchoolId(),syllabusDTO.getStartDate());
        if (schoolTermPojo == null){
            return errorResult("没有找到该班级的课程表");
        } else {
            //根据学校id、班级id、学期、开始日期查询班级课程表
            String schoolTermCode = schoolTermPojo.getCode();
            List<SyllabusPojo> syllabusPojoList = syllabusService.getSyllabus(syllabusDTO,schoolTermCode);
            if (syllabusPojoList != null && syllabusPojoList.size() > 0){
                SyllabusPojo syllabusPojo = syllabusPojoList.get(0);
                Integer syllabusId = syllabusPojo.getId();
                //获取班级课程表节次
                String lessonTimes = syllabusPojo.getLessonTimes();
                JSONArray lessonList = JSON.parseArray(lessonTimes);
                for (int i = 0; i < lessonList.size(); i++) {
                    JSONObject lessonTime = lessonList.getJSONObject(i);
                    Object lesson = lessonTime.get("lesson");
                    //根据星期节次来查询课程
                    SyllabusLessonPojo syllabusLessonPojo = syllabusLessonService.listByLesson(lesson,syllabusId,syllabusDTO.getStartDate(),Constant.MONDAY);
                    monday.add(syllabusLessonPojo == null ? new JSONObject() : syllabusLessonPojo);
                    SyllabusLessonPojo lessonPojo = syllabusLessonService.listByLesson(lesson,syllabusId,syllabusDTO.getStartDate(),Constant.TUESDAY);
                    tuesday.add(lessonPojo == null ? new JSONObject() : lessonPojo);
                    SyllabusLessonPojo pojo = syllabusLessonService.listByLesson(lesson,syllabusId,syllabusDTO.getStartDate(),Constant.WEDNESDAY);
                    wednesday.add(pojo == null ? new JSONObject() : pojo);
                    SyllabusLessonPojo byLesson = syllabusLessonService.listByLesson(lesson,syllabusId,syllabusDTO.getStartDate(),Constant.THURSDAY);
                    thursday.add(byLesson == null ? new JSONObject() : byLesson);
                    SyllabusLessonPojo list = syllabusLessonService.listByLesson(lesson,syllabusId,syllabusDTO.getStartDate(),Constant.FRIDAY);
                    friday.add(list == null ? new JSONObject() : list);
                }
                jsonObject.put("monday",monday);
                jsonObject.put("tuesday",tuesday);
                jsonObject.put("wednesday",wednesday);
                jsonObject.put("thursday",thursday);
                jsonObject.put("friday",friday);
                jsonObject.put("lessonTimes",lessonList);
            } else {
                return errorResult("没有找到该班级的课程表");
            }
        }
        return new ApiResult(ResultCode.SUCCESS,jsonObject);

    }

    ApiResult errorResult(String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(400);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
