package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.StudentScoreDao;
import platform.education.generalTeachingAffair.model.StudentScore;
import platform.education.generalTeachingAffair.service.StudentScoreService;
import platform.education.generalTeachingAffair.vo.StudentScoreCondition;

import java.util.Date;
import java.util.List;

public class StudentScoreServiceImpl implements StudentScoreService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private StudentScoreDao studentScoreDao;

    public void setStudentScoreDao(StudentScoreDao studentScoreDao) {
        this.studentScoreDao = studentScoreDao;
    }

    @Override
    public StudentScore findStudentScoreById(Integer id) {
        try {
            return studentScoreDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public StudentScore add(StudentScore studentScore) {
        if (studentScore == null) {
            return null;
        }
        Date createDate = studentScore.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        if(StringUtils.isEmpty(studentScore.getScore())){
            studentScore.setScore("0");
        }
        studentScore.setIsDelete(false);
        studentScore.setCreateDate(createDate);
        studentScore.setModifyDate(createDate);
        return studentScoreDao.create(studentScore);
    }

    @Override
    public StudentScore modify(StudentScore studentScore) {
        if (studentScore == null) {
            return null;
        }
        Date modify = studentScore.getModifyDate();
        studentScore.setModifyDate(modify != null ? modify : new Date());
        if(StringUtils.isEmpty(studentScore.getScore())){
            studentScore.setScore(null);
        }
        return studentScoreDao.update(studentScore);
    }

    @Override
    public void remove(StudentScore studentScore) {
        studentScoreDao.delete(studentScore);
    }

    @Override
    public void removeByExamTeamSubjectId(StudentScore studentScore) {
        studentScoreDao.deleteByExamTeamSubjectId(studentScore);
    }


    @Override
    public List<StudentScore> findStudentScoreByCondition(StudentScoreCondition studentScoreCondition, Page page, Order order) {
        return studentScoreDao.findStudentScoreByCondition(studentScoreCondition, page, order);
    }

    @Override
    public List<StudentScore> findStudentScoreByCondition(StudentScoreCondition studentScoreCondition) {
        return studentScoreDao.findStudentScoreByCondition(studentScoreCondition, null, null);
    }

    @Override
    public List<StudentScore> findStudentScoreByCondition(StudentScoreCondition studentScoreCondition, Page page) {
        return studentScoreDao.findStudentScoreByCondition(studentScoreCondition, page, null);
    }

    @Override
    public List<StudentScore> findStudentScoreByCondition(StudentScoreCondition studentScoreCondition, Order order) {
        return studentScoreDao.findStudentScoreByCondition(studentScoreCondition, null, order);
    }

    @Override
    public Long count() {
        return this.studentScoreDao.count(null);
    }

    @Override
    public Long count(StudentScoreCondition studentScoreCondition) {
        return this.studentScoreDao.count(studentScoreCondition);
    }


    @Override
    public void modifyByexamTeamSubjectId(StudentScore studentScore) {
        Date modify = studentScore.getModifyDate();
        studentScore.setModifyDate(modify != null ? modify : new Date());
        studentScoreDao.updateByexamTeamSubjectId(studentScore);
    }

}
