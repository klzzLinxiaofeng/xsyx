package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.vo.*;

import java.util.List;

public interface PjExamDao extends GenericDao<PjExam, java.lang.Integer> {

    List<PjExam> findPjExamByCondition(PjExamCondition pjExamCondition, Page page, Order order);

    List<PjExamVo> findPjExamMoreByCondition(PjExamCondition pjExamCondition, Page page, Order order);

    PjExam findById(Integer id);

    Long count(PjExamCondition pjExamCondition);

    /**
     * 功能描述：查找某学期内，某个班级的某个科目的所有考试记录，学期根据学年代码、学期国标代码确定
     * 2015-01-06
     *
     * @param schoolYear
     * @param termValue
     * @param teamId
     * @param subjectCode
     * @return
     */
    List<PjExam> findExamsBySubject(String schoolYear, String termValue, Integer teamId, String subjectCode);

    /**
     * 功能描述：查找某学期内，某个班级的某个考试类型的所有考试成绩记录，传入参数（examRound）可定位到具体某个轮次
     * 2016-01-06
     *
     * @param schoolYear
     * @param termValue
     * @param teamId
     * @param examType
     * @param examRound
     * @return
     */
    List<PjExam> findExamsByType(String schoolYear, String termValue, Integer teamId, String examType, Integer examRound);

    /**
     * 功能描述：查询出唯一pj_exam记录
     * 2016-01-07
     *
     * @param schoolYear
     * @param termValue
     * @param teamId
     * @param examType
     * @param examRound
     * @param subjectCode
     * @return
     */
    PjExam findUnique(String schoolYear, String termValue, Integer teamId, String examType, Integer examRound, String subjectCode);

    List<ExamTeamNumber> findTeamNumberOfCode(String code);

    void createBatch(PjExam[] eslist);

    void updateDelectByJointExamCode(String code, String type);

    List<TeamAvgScroeVo> findTeamAvgByCode(String code, String type, Integer order);
}
