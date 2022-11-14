package com.xunyunedu.syllabus.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.school.service.PjSchoolService;
import com.xunyunedu.syllabus.pojo.SyllabusPojo;
import com.xunyunedu.syllabus.service.SyllabusLessonService;
import com.xunyunedu.syllabus.service.SyllabusService;
import com.xunyunedu.syllabus.vo.SyllabusLessonVo;
import com.xunyunedu.team.pojo.SchoolTermPojo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 班级课程表的子表,记录课程表中每节课的任课老师
 * 本表是每周固定的课程安排表,如果临时调课不在此表描述
 * (SyllabusLesson)表控制层
 *
 * @author sjz
 * @since 2021-03-30 09:08:42
 */
@RestController
@RequestMapping("/syllabusLesson")
public class SyllabusLessonController {
    /**
     * 服务对象
     */
    @Resource
    private SyllabusLessonService syllabusLessonService;

    @Resource
    SyllabusService syllabusService;

    @Resource
    PjSchoolService pjSchoolService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public ApiResult selectOne(Integer id) {
        //根据主键id查询课程内容
        SyllabusLessonVo lessonVo = this.syllabusLessonService.queryById(id);
        if (lessonVo != null){
            Integer syllabusId = lessonVo.getSyllabusId();
            Integer lessonVoLesson = lessonVo.getLesson();
            //根据课程的课程表id获取节次，开始和结束时间
            SyllabusPojo syllabusPojo = syllabusService.getSyllabusId(syllabusId);
            String lessonTimes = syllabusPojo.getLessonTimes();
            JSONArray lessonList = JSON.parseArray(lessonTimes);
            for (int i = 0; i < lessonList.size(); i++) {
                JSONObject lessonTime = lessonList.getJSONObject(i);
                Integer lesson = Integer.parseInt(lessonTime.get("lesson").toString());
                //判断节次是否相同如果相同则把本节课开始结束时间存进lessonVo
                if (lessonVoLesson.equals(lesson)){
                    String startTime = lessonTime.get("startTime").toString();
                    String endTime = lessonTime.get("endTime").toString();
                    lessonVo.setStartTime(startTime);
                    lessonVo.setEndTime(endTime);
                }
            }
        }
        return new ApiResult(ResultCode.SUCCESS,lessonVo);

    }

    /**
     * 通过老师id查询个人课程列表
     *
     * @param teacherId
     * @return
     */
    @GetMapping("/listByTeacherId")
    public ApiResult listByTeacherId(@RequestParam Integer teacherId,String startDate) {
        JSONArray monday = new JSONArray();
        JSONArray tuesday = new JSONArray();
        JSONArray wednesday = new JSONArray();
        JSONArray thursday = new JSONArray();
        JSONArray friday = new JSONArray();
        JSONObject object = new JSONObject(5,true);
        //根据前作为查询条件的日期获取本周一的日期
        DateTime parse = DateUtil.parse(startDate);
        Date beginOfWeek = DateUtil.beginOfWeek(parse);
        //定义每天节次
        int[] lessons = {1,2,3,4,5,6,7,8};
        //周一到周五
        int[] weeks = {Constant.MONDAY,Constant.TUESDAY,Constant.WEDNESDAY,Constant.THURSDAY,Constant.FRIDAY};
        //查询本周是否有调课的课程
        int count = this.syllabusLessonService.selectCountByTeacherId(teacherId,startDate);
        //判断本周是否有调课的课程，如果有则查询调过的课程返回，没有则查询默认课程返回
        if (count > 0){
            for (int week : weeks) {
                for (int lesson : lessons) {
                    //根据星期节次查询课程
                    SyllabusLessonVo syllabusLessonVo = this.syllabusLessonService.getByTeacher(teacherId,startDate,lesson,week);
                    if (syllabusLessonVo != null){
                        //根据课程表id查询课程表获取节次时间
                        SyllabusPojo syllabusPojo = syllabusService.getSyllabusId(syllabusLessonVo.getSyllabusId());
                        if (syllabusPojo != null){
                            syllabusLessonVo.setTeamId(syllabusPojo.getTeamId());
                            JSONArray lessonTimeList = JSON.parseArray(syllabusPojo.getLessonTimes());
                            for (int i = 0; i < lessonTimeList.size(); i++) {
                                JSONObject lessonTime = lessonTimeList.getJSONObject(i);
                                int lesson1 = Integer.parseInt(lessonTime.get("lesson").toString());
                                if (lesson == lesson1){
                                    syllabusLessonVo.setStartTime(lessonTime.get("startTime").toString());
                                    syllabusLessonVo.setEndTime(lessonTime.get("endTime").toString());
                                }
                            }
                        }
                    } else {
                        syllabusLessonVo = new SyllabusLessonVo();
                    }
                    //判断这是星期几的课程，放到对应的数组
                    if (week == Constant.MONDAY){
                        syllabusLessonVo.setWeekOfDay(beginOfWeek);
                        monday.add(syllabusLessonVo);
                    }
                    if (week == Constant.TUESDAY){
                        syllabusLessonVo.setWeekOfDay(DateUtil.offsetDay(beginOfWeek,1));
                        tuesday.add(syllabusLessonVo);
                    }
                    if (week == Constant.WEDNESDAY){
                        syllabusLessonVo.setWeekOfDay(DateUtil.offsetDay(beginOfWeek,2));
                        wednesday.add(syllabusLessonVo);
                    }
                    if (week == Constant.THURSDAY){
                        syllabusLessonVo.setWeekOfDay(DateUtil.offsetDay(beginOfWeek,3));
                        thursday.add(syllabusLessonVo);
                    }
                    if (week == Constant.FRIDAY){
                        syllabusLessonVo.setWeekOfDay(DateUtil.offsetDay(beginOfWeek,4));
                        friday.add(syllabusLessonVo);
                    }
                }
            }
        } else {
            for (int week : weeks) {
                for (int lesson : lessons) {
                    //根据星期节次查询课程
                    SyllabusLessonVo syllabusLessonVo = this.syllabusLessonService.getByTeacherId(teacherId,startDate,lesson,week);
                    if (syllabusLessonVo != null){
                        //根据课程表id查询课程表获取节次时间
                        SyllabusPojo syllabusPojo = syllabusService.getSyllabusId(syllabusLessonVo.getSyllabusId());
                        if (syllabusPojo != null){
                            syllabusLessonVo.setTeamId(syllabusPojo.getTeamId());
                            JSONArray lessonTimeList = JSON.parseArray(syllabusPojo.getLessonTimes());
                            for (int i = 0; i < lessonTimeList.size(); i++) {
                                JSONObject lessonTime = lessonTimeList.getJSONObject(i);
                                int lesson1 = Integer.parseInt(lessonTime.get("lesson").toString());
                                if (lesson == lesson1){
                                    syllabusLessonVo.setStartTime(lessonTime.get("startTime").toString());
                                    syllabusLessonVo.setEndTime(lessonTime.get("endTime").toString());
                                }
                            }
                        }
                    } else {
                        syllabusLessonVo = new SyllabusLessonVo();
                    }
                    //判断这是星期几的课程，放到对应的数组
                    if (week == Constant.MONDAY){
                        syllabusLessonVo.setWeekOfDay(beginOfWeek);
                        monday.add(syllabusLessonVo);
                    }
                    if (week == Constant.TUESDAY){
                        syllabusLessonVo.setWeekOfDay(DateUtil.offsetDay(beginOfWeek,1));
                        tuesday.add(syllabusLessonVo);
                    }
                    if (week == Constant.WEDNESDAY){
                        syllabusLessonVo.setWeekOfDay(DateUtil.offsetDay(beginOfWeek,2));
                        wednesday.add(syllabusLessonVo);
                    }
                    if (week == Constant.THURSDAY){
                        syllabusLessonVo.setWeekOfDay(DateUtil.offsetDay(beginOfWeek,3));
                        thursday.add(syllabusLessonVo);
                    }
                    if (week == Constant.FRIDAY){
                        syllabusLessonVo.setWeekOfDay(DateUtil.offsetDay(beginOfWeek,4));
                        friday.add(syllabusLessonVo);
                    }
                }
            }
        }
        object.put("monday",monday);
        object.put("tuesday",tuesday);
        object.put("wednesday",wednesday);
        object.put("thursday",thursday);
        object.put("friday",friday);
        return new ApiResult(ResultCode.SUCCESS,object);
    }

    /**
     * 查询老师名字
     *
     * @param teamId
     * @param schoolId
     * @param lesson
     * @param setDate
     * @return
     */
    @GetMapping("/getLesson")
    public ApiResult getLesson(@RequestParam Integer teamId,Integer schoolId,Integer lesson,String setDate) {
        JSONObject result = new JSONObject();
        //根据学校id和被调课的日期获取学年
        try {
            SchoolTermPojo schoolTermPojo = pjSchoolService.getBySchoolId(schoolId,setDate);
            //根据班级id和学年获取课程表id
            String code = schoolTermPojo.getCode();
            SyllabusPojo syllabusPojo = syllabusService.getSyllabusByTeamIdCode(teamId,code);
            //根据课程表id，节次，日期获取课程内容
            Integer syllabusId = syllabusPojo.getId();
            Date parse = DateUtil.parse(setDate);
            Integer week = DateUtil.dayOfWeek(parse) - 1;
            SyllabusLessonVo syllabusLessonVo = syllabusLessonService.getLesson(syllabusId,lesson,setDate,week);
            result.put("teacherId",syllabusLessonVo.getTeacherId());
            result.put("teacherName",syllabusLessonVo.getTeacherName());
        } catch (Exception e) {
            return errorResult("没有可选的课程，请选择其他日期喔~");
        }
        return new ApiResult(ResultCode.SUCCESS,result);

    }

    ApiResult errorResult(String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(400);
        apiResult.setMsg(msg);
        return apiResult;
    }


}
