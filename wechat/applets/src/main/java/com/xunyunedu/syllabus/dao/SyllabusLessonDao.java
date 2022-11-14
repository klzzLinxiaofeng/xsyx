package com.xunyunedu.syllabus.dao;

import com.xunyunedu.syllabus.pojo.SyllabusLessonPojo;
import com.xunyunedu.syllabus.vo.SyllabusLessonVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SyllabusLessonDao {


    SyllabusLessonVo queryById(Integer id);

    List<SyllabusLessonPojo> queryAll(@Param("id") Integer syllabusId);

    void insert(SyllabusLessonPojo syllabusLessonPojo);

    List<SyllabusLessonPojo> listBySyllabusLesson(@Param("startDate") Date startDate, @Param("teacherId") Integer teacherId, @Param("effectiveDate") Date effectiveDate, @Param("defaultFlag") Integer defaultFlag);

    void deleteByTeacher(@Param("teacherId") Integer teacherId, @Param("startDate") Date startDate);

    SyllabusLessonPojo listByLesson(@Param("lesson")Object lesson,@Param("syllabusId") Integer syllabusId,@Param("startDate") String startDate,@Param("week") Integer week);

    SyllabusLessonVo getByTeacherId(@Param("teacherId") Integer teacherId,@Param("startDate")String startDate,@Param("lesson")Integer lesson,@Param("week")Integer week);

    SyllabusLessonVo getLesson(@Param("syllabusId") Integer syllabusId,@Param("lesson") Integer lesson,@Param("setDate") String setDate,@Param("week") Integer week);

    //    SyllabusLessonPojo getByWeekLesson(@Param("lesson") Object lesson,@Param("week") Integer week,@Param("appDate") String appDate,@Param("applicantId") Integer applicantId);

    //    SyllabusLessonPojo getByLessonWeek(@Param("lesson") Object lesson,@Param("setWeek") Integer setWeek,@Param("sDate") String sDate,@Param("approverId") Integer approverId);

    int selectCount(@Param("teacherId") Integer teacherId,@Param("startDate") String startDate);

    SyllabusLessonVo getByTeacher(@Param("teacherId") Integer teacherId,@Param("startDate") String startDate,@Param("lesson") Integer lesson,@Param("week") Integer week);

    List<SyllabusLessonPojo> listByTeacherId(@Param("teacherId") Integer teacherId,@Param("startDate") String startDate,@Param("defaultFlag") Integer defaultFlag);

    void deleteById(Integer id);
}




