package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.vo.CommonScoreRank;
import platform.education.generalTeachingAffair.vo.ExamStudentCondition;
import platform.education.generalTeachingAffair.vo.ExamStudentVo;

import java.util.List;
import java.util.Map;

public interface ExamStudentService {
    ExamStudent findExamStudentById(Integer id);

    ExamStudent add(ExamStudent examStudent);

    ExamStudent modify(ExamStudent examStudent);

    void remove(ExamStudent examStudent);

    List<ExamStudent> findExamStudentByCondition(ExamStudentCondition examStudentCondition, Page page, Order order);

    List<ExamStudent> findExamStudentByCondition(ExamStudentCondition examStudentCondition);

    List<ExamStudent> findExamStudentByCondition(ExamStudentCondition examStudentCondition, Page page);

    List<ExamStudent> findExamStudentByCondition(ExamStudentCondition examStudentCondition, Order order);

    Long count();

    Long count(ExamStudentCondition examStudentCondition);

    /**
     * 功能描述：根据examId查询出与其相关的所有学生成绩名单
     * 2016-01-07
     *
     * @param examId
     * @return
     */
    List<ExamStudent> findExamStudentsByExamId(Integer examId);

    List<ExamStudent> findExamStudentsByExamId(Integer examId, Order order);

    /**
     * 功能描述：查询某个学期、某个班级和某个科目的考试学生成绩名单
     * 2016-01-07
     *
     * @param examId
     * @param SchoolYear
     * @param termValue
     * @param subjectCode
     * @return
     */
    List<ExamStudent> findExamStudents(String SchoolYear, String termValue, Integer teamId, String subjectCode);

    /**
     * 功能描述：根据examId查询并清空所有学生的成绩记录，即恢复到未录入成绩的状态，
     * 并更改pj_exam_stat对应的实际应试人数 student_count = 0
     * 2016-01-07
     *
     * @param examId
     */
    void clearExamScore(Integer examId);

    /**
     * 功能描述：删除某一科成绩后传入参数（examId）查询pj_exam_student表中与其相关记录并删除
     * 2016-01-07
     *
     * @param examId
     */
    void removeExamStudents(Integer examId);

    /**
     * 批量保存学生分数
     * 2016-01-07
     *
     * @param examId
     */
    void addExamStudents(List<ExamStudent> examStudengList);

    /**
     * 功能描述：根据考试ID计算学生分数总数
     * 2016-01-07
     *
     * @param examId
     */
    Long countTeamTotalScore(Integer examId);

    /**
     * @param examId 考试的id pj_exam
     * @return
     * @Function 统计某次考试的考试人数
     * @date 2016年1月26日
     * @author hmzhang
     */
    Long countTotalStudent(Integer examId);

    /**
     * @param examId
     * @param fullScore 满分
     * @param highScore 优秀
     * @param lowScore  良好
     * @param passScore 及格
     * @return
     * @function 统计各分数段的人数
     * @date 2016年1月26日
     * @author hmzhang
     */
    CommonScoreRank countScoreRate(Integer examId, Float fullScore, Float highScore, Float lowScore, Float passScore);

    void updateTeamRank(Integer examId);

    void updateGradeRank(Integer[] ids);

    /**
     * @param examId
     * @param userId
     * @return List<ExamStudent>    返回类型
     * @throws
     * @Title: findExamStudentByExamIdAndUserId
     * @author pantq
     * @Description: 通过examId和userId查询examStudent 记录。
     */
    ExamStudent findExamStudentByExamIdAndUserId(Integer examId, Integer userId);


    /**
     * @param paperUuid
     * @param ownerId
     * @param type
     * @param examId    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateExamStudents
     * @author pantq
     * @Description: 批量更新学生成绩
     */
    void updateExamStudents(String paperUuid, Integer ownerId, Integer type, Integer examId);


    /**
     * @param paperUuid 试卷UUID
     * @param ownerId   任务ID
     * @param type      类型
     * @param examId    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateExamStudentAnswerCount
     * @author pantq
     * @Description: 批量更新学生正答题数、总应答题数
     */
    void updateExamStudentAnswerCount(String paperUuid, Integer ownerId, Integer type, Boolean isCorrect, Integer examId);

    /**
     * 查询本班最高分
     *
     * @param examId
     * @return
     */
    Float findExamStudentHighestScoreByExamId(Integer examId);

    /**
     * 查询本班最低分
     *
     * @param examId
     * @return
     */
    Float findExamStudentLowestScoreByExamId(Integer examId);


    void batchUpdateExamStudents(Object[] list);


    void batchUpdateTeamRank(Object[] list);

    void batchUpdateExamStudentCorrectAnswerCount(Object[] list);

    void batchUpdateExamStudentAnswerCount(Object[] list);

    List<Map<String, Object>> findExamStudentTeamRank(Integer examId);

    void batchUpdateExamStudent(List<Map<String, Object>> examStudentsList, List<Map<String, Object>> examStudentTeamRankList, List<Map<String, Object>> examStudentCorrectAnswerCountList, List<Map<String, Object>> examStudentAnswerCountList);

    void batchUpdateExamStudentTeamRank(List<PjExam> pjExamList);

    void batchUpdateExamStudentCorrectAnswerCountNew(List<PjExam> pjExamList, String paperUuid, Integer ownerId, Integer paperType, Integer correct);

    //void batchUpdateExamStudentAnswerCountNew(List<PjExam> pjExamList,String paperUuid,Integer ownerId, Integer paperType);

    //void batchUpdateExamStudentGradeRank(List<PjExam> pjExamList);
    Map<Integer, Long> countTotalStudentByExamIds(Object[] examIdObj);

    Map<Integer, List<ExamStudent>> findExamStudentByExamIdObj(Object[] examIdObj);


    Map<Integer, Float> findExamStudentHighestScoreByExamIdObj(Object[] examIdObj);

    Map<Integer, Float> findExamStudentLowestScoreByExamIdObj(Object[] examIdObj);

    void createBatch(ExamStudent[] eslist);

    void batchUpdateExamStudentScore(Object[] list);

    void batchUpdateExamStudentGradeRankByScore(Object[] list);

    List<ExamStudent> findExamStudentFinishByExamIds(Integer[] examIds);

    List<ExamStudent> findExamStudentByJointExamCode(String jointExamCode, String subjectCode);

    Integer findGradeStudentCountByGradeId(Integer gradeId);

    List<ExamStudent> findGradeRankByScoreJointCode(float score, String jointExamCode, String subjectCode);

    /**
     * 根据examId返回学生答题情况
     *
     * @param examIds
     * @return
     */
    List<ExamStudentVo> findExamStudentVoByExamIds(Integer[] examIds);

    List<ExamStudentVo> findExamStudentVoByExamIdsWithType(Integer[] examIds, Integer groupByType);
} 
