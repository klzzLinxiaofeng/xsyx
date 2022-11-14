package com.xunyunedu.syllabus.service.impl;

import com.xunyunedu.syllabus.dao.SyllabusLessonDao;
import com.xunyunedu.syllabus.pojo.SyllabusLessonPojo;
import com.xunyunedu.syllabus.service.SyllabusLessonService;
import com.xunyunedu.syllabus.vo.SyllabusLessonVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SyllabusLessonImpl implements SyllabusLessonService {

    @Resource
    SyllabusLessonDao syllabusLessonDao;


    @Override
    public SyllabusLessonVo queryById(Integer id) {
        return syllabusLessonDao.queryById(id);
    }

    @Override
    public void insert(SyllabusLessonPojo syllabusLessonPojo) {
        syllabusLessonDao.insert(syllabusLessonPojo);
    }

    @Override
    public SyllabusLessonPojo listByLesson(Object lesson,Integer syllabusId,String startDate,Integer week) {
        return syllabusLessonDao.listByLesson(lesson,syllabusId,startDate,week);
    }

    @Override
    public SyllabusLessonVo getByTeacherId(Integer teacherId,String startDate,Integer lesson,Integer week) {
        return syllabusLessonDao.getByTeacherId(teacherId,startDate,lesson,week);
    }

    @Override
    public SyllabusLessonVo getLesson(Integer syllabusId,Integer lesson,String setDate,Integer week) {
        return syllabusLessonDao.getLesson(syllabusId,lesson,setDate,week);
    }

    @Override
    public int selectCountByTeacherId(Integer teacherId,String startDate) {
        return syllabusLessonDao.selectCount(teacherId,startDate);
    }

    @Override
    public SyllabusLessonVo getByTeacher(Integer teacherId,String startDate,Integer lesson,Integer week) {
        return syllabusLessonDao.getByTeacher(teacherId,startDate,lesson,week);
    }
}
