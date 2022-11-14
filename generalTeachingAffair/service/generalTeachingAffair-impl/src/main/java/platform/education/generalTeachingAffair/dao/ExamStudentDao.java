package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.vo.CommonScoreRank;
import platform.education.generalTeachingAffair.vo.ExamStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.ExamStudentVo;

import java.util.List;
import java.util.Map;

public interface ExamStudentDao extends GenericDao<ExamStudent, Integer> {

	List<ExamStudent> findExamStudentByCondition(ExamStudentCondition examStudentCondition, Page page, Order order);
	
	ExamStudent findById(Integer id);
	
	Long count(ExamStudentCondition examStudentCondition);
	
	/**
	 * 功能描述：根据examId查询出与其相关的所有学生成绩名单
	 * 2016-01-07
	 * @param examId
	 * @return
	 */
	List<ExamStudent> findExamStudentsByExamId(Integer examId);
	
	List<ExamStudent> findExamStudentsByExamId(Integer examId, Order order);
	
	/**
	 * 功能描述：查询某个学期、某个班级和某个科目的考试学生成绩名单
	 * 2016-01-07
	 * @param examId
	 * @param SchoolYear
	 * @param termValue
	 * @param subjectCode
	 * @return
	 */
	List<ExamStudent> findExamStudents(String SchoolYear, String termValue, Integer teamId, String subjectCode);

	Long countTeamTotalScore(Integer examId);
	
	/**
	 * @Function 统计某次考试的考试人数
	 * @param examId 考试的id pj_exam
	 * @return
	 * @date 2016年1月26日
	 * @author hmzhang
	 */
	Long countTotalStudent(Integer examId);
	
	/**
	 * @function 统计各分数段的人数
	 * @param examId
	 * @param fullScore 满分
	 * @param highScore 优秀
	 * @param lowScore 良好
	 * @param passScore 及格
	 * @return
	 * @date 2016年1月26日
	 * @author hmzhang
	 */
	CommonScoreRank countScoreRate(Integer examId, Float fullScore, Float highScore, Float lowScore, Float passScore);
	
	void updateTeamRank(Integer examId);
	
	void updateGradeRank(Integer[] ids);
	
	/**
	 * 
	* @Title: findExamStudentByExamIdAndUserId
	* @author pantq 
	* @Description: 通过examId和userId查询examStudent 记录。
	* @param examId
	* @param userId
	* @return    设定文件 
	* @return List<ExamStudent>    返回类型 
	* @throws
	 */
	ExamStudent findExamStudentByExamIdAndUserId(Integer examId, Integer userId);
	
	/**
	 * 
	* @Title: updateExamStudents
	* @author pantq 
	* @Description: 批量更新学生成绩
	* @param paperUuid 试卷UUID
	* @param ownerId 任务ID
	* @param type 类型
	* @param examId    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void updateExamStudents(String paperUuid, Integer ownerId, Integer type, Integer examId);
	
	
	/**
	 * 
	* @Title: updateExamStudentAnswerCount
	* @author pantq 
	* @Description: 批量更新学生正答题数、总应答题数
	* @param paperUuid 试卷UUID
	* @param ownerId 任务ID
	* @param type 类型
	* @param isCorrect 对错
	* @param examId    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void updateExamStudentAnswerCount(String paperUuid, Integer ownerId, Integer type, Boolean isCorrect, Integer examId);
	/**
	 * 查询本班最高分
	 * @param examId
	 * @return
	 */
	Float findExamStudentHighestScoreByExamId(Integer examId);
	
	/**
	 * 查询本班最低分
	 * @param examId
	 * @return
	 */
	Float findExamStudentLowestScoreByExamId(Integer examId);
	
	

	void batchUpdateExamStudents(Object[] list);
	
	
	void batchUpdateTeamRank(Object[] list);
	
	
	void batchUpdateExamStudentCorrectAnswerCount(Object[] list);
	
	void batchUpdateExamStudentAnswerCount(Object[] list);
	
	List<Map<String,Object>> findExamStudentTeamRank(Integer examId);
	
	
	
	void batchUpdateExamStudentScore(Object[] list);

	void batchUpdateExamStudentGradeRankByScore(Object[] list);
	
	void batchUpdateExamStudentTeamRank(Object[] examIdObj);
	
	void batchUpdateExamStudentCorrectAnswerCountNew(Object[] examIdObj, String paperUuid, Integer ownerId, Integer paperType, Integer correct);
	
	void batchUpdateExamStudentAnswerCountNew(Object[] examIdObj, String paperUuid, Integer ownerId, Integer paperType);
	
	void batchUpdateExamStudentGradeRank(Object[] examIdObj);
	
	Map<Integer,Long> countTotalStudentByExamIds(Object[] examIdObj);
	
	Map<Integer,Float> findExamStudentHighestScoreByExamIdObj(Object[] examIdObj);
	Map<Integer,Float> findExamStudentLowestScoreByExamIdObj(Object[] examIdObj);
	
	void deleteByExamId(Integer examId);
	
	void createBatch(ExamStudent[] eslist);
	List<ExamStudent> findExamStudentFinishByExamIds(Integer[] examIds);
	
	List<ExamStudent> findExamStudentByJointExamCode(String jointExamCode, String subjectCode);
	
	Integer findGradeStudentCountByGradeId(Integer gradeId);
	
	
	List<ExamStudent> findGradeRankByScoreJointCode(float score, String jointExamCode, String subjectCode);

	List<ExamStudentVo> findExamStudentVoByExamIds(Integer[] examIds);

	List<ExamStudentVo> findExamStudentVoByExamIdsWithType(Integer[] examIds, Integer groupByType);
}
