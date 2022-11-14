package com.xunyunedu.PublishAndAcceptJob.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.xunyunedu.PublishAndAcceptJob.dao.PublishAndAcceptJobContentDao;
import com.xunyunedu.PublishAndAcceptJob.pojo.PublishAndAcceptJobContentPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.SubjectPojo;
import com.xunyunedu.PublishAndAcceptJob.service.AcceptJobContentService;
import com.xunyunedu.teacher.dao.TeacherHomeDao;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 学生接收作业
 *
 * @author: lee
 */

@Service
public class AcceptJobContentServiceImpl implements AcceptJobContentService {

    Logger log = LoggerFactory.getLogger(AcceptJobContentServiceImpl.class);

    @Autowired
    private PublishAndAcceptJobContentDao publishJobContentDao;

    @Autowired
    private TeacherHomeDao teacherHomeDao;

    @Override
    public List<PublishAndAcceptJobContentPojo> getAcceptJobContentAll(Integer teamId) {
        List<PublishAndAcceptJobContentPojo> acceptJobContentAll = publishJobContentDao.findAcceptJobContentAll(teamId, null);
        for (PublishAndAcceptJobContentPojo pojo : acceptJobContentAll) {
            TeacherPojo teacherPojo = new TeacherPojo();
            teacherPojo.setId(pojo.getTeacherId());
            TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
            if (teacher != null) {
                pojo.setTeacherName(teacher.getName());
            }
            SubjectPojo subjectPojo = new SubjectPojo();
            subjectPojo.setCode(pojo.getSubjectId());
            List<SubjectPojo> subject = publishJobContentDao.findSubject(subjectPojo);
            if (CollUtil.isNotEmpty(subject)) {
                pojo.setSubjectName(subject.get(0).getName());
            }
        }
        return acceptJobContentAll;
    }

    /**
     * 学生接收作业
     *
     * @param teamId
     * @param subjectId
     * @return
     */
    @Override
    public List<PublishAndAcceptJobContentPojo> getContentByStudentIdAndSubjectId(Integer teamId, String subjectId) {
        List<PublishAndAcceptJobContentPojo> acceptJobContentAll = publishJobContentDao.findAcceptJobContentAll(teamId, subjectId);
        for (PublishAndAcceptJobContentPojo pojo : acceptJobContentAll) {
            TeacherPojo teacherPojo = new TeacherPojo();
            teacherPojo.setId(pojo.getTeacherId());
            TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
            if (teacher != null) {
                pojo.setTeacherName(teacher.getName());
            }
            SubjectPojo subjectPojo = new SubjectPojo();
            subjectPojo.setCode(pojo.getSubjectId());
            List<SubjectPojo> subject = publishJobContentDao.findSubject(subjectPojo);
            if (CollUtil.isNotEmpty(subject)) {
                pojo.setSubjectName(subject.get(0).getName());
            }
        }
        return acceptJobContentAll;
    }

    /**
     * 主键查询作业详情
     *
     * @param id
     * @return
     */
    @Override
    public PublishAndAcceptJobContentPojo getContentDetails(Integer id) {
        return publishJobContentDao.findContentDetailsById(id);
    }

    /**
     * 科目信息
     *
     * @param subject
     * @return
     */
    @Override
    public Set<SubjectPojo> getSubject(SubjectPojo subject) {
        Comparator<SubjectPojo> com = new Comparator<SubjectPojo>() {
            @Override
            public int compare(SubjectPojo o1, SubjectPojo o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        };
        Set<SubjectPojo> set = new TreeSet<>(com);
        // 获取当前学生对应班级作业有哪些
        List<PublishAndAcceptJobContentPojo> acceptJobContentAll = publishJobContentDao.findAcceptJobContentAll(subject.getTeamId(), null);
        for (PublishAndAcceptJobContentPojo pojo : acceptJobContentAll) {
            // 作业对应的课程
            SubjectPojo subjectPojo = new SubjectPojo();
            subjectPojo.setCode(pojo.getSubjectId());
            List<SubjectPojo> subjectPojoList = publishJobContentDao.findSubject(subjectPojo);
            if (CollUtil.isNotEmpty(subjectPojoList)) {
                subjectPojo.setName(subjectPojoList.get(0).getName());
                set.add(subjectPojo);
            }
        }
        return set;
    }
}