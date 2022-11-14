package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.vo.*;

import java.util.List;

public interface PjExamService {

    /**
     * 当前接口crud操作 成功时返回的状态值
     */
    public final static String OPERATE_SUCCESS = "success";

    /**
     * 当前接口crud操作 失败时返回的状态值
     */
    public final static String OPERATE_FAIL = "fail";

    /**
     * 系统异常造成的操作失败 系统返回的状态值
     */
    public final static String OPERATE_ERROR = "error";

    PjExam findPjExamById(Integer id);

    PjExam add(PjExam pjExam);

    PjExam modify(PjExam pjExam);

    void remove(PjExam pjExam);

    List<PjExam> findPjExamByCondition(PjExamCondition pjExamCondition, Page page, Order order);

    List<PjExamVo> findPjExamMoreByCondition(PjExamCondition pjExamCondition, Page page, Order order);

    List<PjExam> findPjExamByCondition(PjExamCondition pjExamCondition);

    List<PjExam> findPjExamByCondition(PjExamCondition pjExamCondition, Page page);

    List<PjExam> findPjExamByCondition(PjExamCondition pjExamCondition, Order order);

    Long count();

    Long count(PjExamCondition pjExamCondition);

    /**
     * 功能描述：初始化成绩录入数据，包括创建pj_exam表, pj_exam_student表, pj_exam_stat表
     * 2016-01-06
     *
     * @param pjExam
     * @return
     */
    ExamResult InitExamData(PjExam pjExam);


    /**
     * 功能描述：查找某学期内，某个班级的某个科目的所有考试记录，学期根据学年代码、学期国标代码确定
     * 2016-01-06
     *
     * @param schoolYear
     * @param termValue
     * @param teamId
     * @param subjectCode
     * @return
     */
    List<PjExam> findExamsBySubject(String schoolYear, String termValue, Integer teamId, String subjectCode);

    /**
     * 功能描述：查找某学期内，某个班级的某个考试类型的所有考试成绩记录，所有轮次
     * 2016-01-06
     *
     * @param schoolYear
     * @param termValue
     * @param teamId
     * @param examType
     * @return
     */
    List<PjExam> findExamsByType(String schoolYear, String termValue, Integer teamId, String examType);

    /**
     * 功能描述：查找某学期内，某个班级的某个考试类型的所有考试成绩记录，定位到具体某个轮次
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
     * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
     * 2016-01-07
     *
     * @param pjExam
     * @return
     */
    String abandon(PjExam pjExam);

    /**
     * 功能描述：查询出唯一pj_exam记录，必须提供全部参数
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


    List<ExamQuestionWrongVo> findExamQuestionWrongbyExamId(Integer[] examIds, Integer type, float rate);

    public List<ExamQuestionWrongVo> findExamQuestionWrongForStudent(
            Integer gradeId, Integer teamId, String subjectCode, Integer type, float rated, Order order);

    List<ExamTeamNumber> findTeamNumberOfCode(String code);

    void createBatch(PjExam[] eslist);

    /**
     * 根据一个任务去删除
     *
     * @param code
     */
    void modifyDelectByJointExamCode(String code, String type);

    /**
     * @param code
     * @param type
     * @param order 1为按班级序号排序，0为按平均分排序
     * @return
     */
    StatisticsTeamScore findTeamAvgByCode(String code, String type, Integer order);

}
