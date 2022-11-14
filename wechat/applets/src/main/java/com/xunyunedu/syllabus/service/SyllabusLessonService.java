package com.xunyunedu.syllabus.service;

import com.xunyunedu.syllabus.pojo.SyllabusLessonPojo;
import com.xunyunedu.syllabus.vo.SyllabusLessonVo;



public interface SyllabusLessonService {

    SyllabusLessonVo queryById(Integer id);

    void insert(SyllabusLessonPojo syllabusLessonPojo);

    SyllabusLessonPojo listByLesson(Object lesson,Integer syllabusId,String startDate,Integer week);

    SyllabusLessonVo getByTeacherId(Integer teacherId,String startDate,Integer lesson,Integer week);

    SyllabusLessonVo getLesson(Integer syllabusId,Integer lesson,String setDate,Integer week);

    int selectCountByTeacherId(Integer teacherId,String startDate);

    SyllabusLessonVo getByTeacher(Integer teacherId,String startDate,Integer lesson,Integer week);
}
