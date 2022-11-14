package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksTeamSubject;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamSubjectCondition;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamSubjectVo;

import java.util.List;
import java.util.Map;

public interface ExamWorksTeamSubjectService {
    ExamWorksTeamSubject findExamWorksTeamSubjectById(Integer id);
	   
	ExamWorksTeamSubject add(ExamWorksTeamSubject examWorksTeamSubject);
	   
	ExamWorksTeamSubject modify(ExamWorksTeamSubject examWorksTeamSubject);
	   
	void remove(ExamWorksTeamSubject examWorksTeamSubject);
	   
	List<ExamWorksTeamSubject> findExamWorksTeamSubjectByCondition(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition, Page page, Order order);
	
	List<ExamWorksTeamSubject> findExamWorksTeamSubjectByCondition(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition);
	
	List<ExamWorksTeamSubject> findExamWorksTeamSubjectByCondition(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition, Page page);
	
	List<ExamWorksTeamSubject> findExamWorksTeamSubjectByCondition(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition, Order order);
	
	Float findGradeTotalScoreBySubjectCode(String jointExamCode, String subjectCode);
	Long count();
	
	Long count(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition);


	ExamWorksTeamSubject findUnique(Integer examWorksId, Integer teamId, String subjectCode);

	/**
	 * 获取考务的所有年级班级及其考试科目设置
	 * @param examWorksId
	 * @return
	 */
	List<ExamWorksTeamSubjectVo> findExamWorksContext(Integer examWorksId);
	
	List<Map<String,Object>> findExamWorksBySubjectCode(Integer teamId,String examType,Integer userId,String subjectCode);

	void createBatch(ExamWorksTeamSubject[] examWorksTeamSubjects);
	
	List<Map<String,Object>> findGradeRankByGradeId(Integer examWorksId,Integer gradeId);
	
	
	
	
	List<Map<String,Object>> findGradeRankBySubjectCode(Integer gradeId,String subjectCode,Integer examWorksId);

	
	Integer findGradeStudentCountBySubjectCode (String jointExamCode,String subjectCode);
	
	/**
	 * 查询一个班级所有科目的大考考务信息（导入情况，发布情况）
	 * @param examWorksId
	 * @param teamId
	 * @return
	 */
	List<Map<String,Object>> findMajorExamWorksByTeam(Integer examWorksId, Integer teamId);

	/**
	 * 查询一个班级单个科目的大考考务信息
	 * @param examWorksId
	 * @param teamId
	 * @param examWorksTeamSubjectId
	 * @return
	 */
	Map<String,Object> findMajorExamWorksByTeam(Integer examWorksId, Integer teamId, Integer examWorksTeamSubjectId);

	/**
	 *	查询一个班级的小考考务信息（导入情况，发布情况）
	 */
	List<Map<String,Object>> findClassExamWorksByTeam(Integer schoolId, String schoolYear, String termCode,
													  Integer teacherId, Integer teamId, Page page, Order order);
	
	/**
	 * 根据CODE和subjectCode 查询年级最大得分
	 * @param jointExamCode
	 * @param subjectCode
	 * @return
	 */
	Float findGradeMaxScore(String jointExamCode,String subjectCode);


	List<Map<String, Object>> findScoreOfStudent(Integer examWorksId, Integer gradeId, Integer teamId, String subjectCode);

	List<Map<String, Object>> findScoreOfStudent(Integer examWorksId, Integer gradeId, Integer teamId, String subjectCode, Page page, Order order);

	List<Map<String, Object>> findTotalScoreOfStudent(Integer examWorksId, Integer gradeId, Integer teamId);

	List<Map<String, Object>> findAvgScoreOfGrade(Integer schoolId, Boolean isJoint, Integer gradeId, Integer teamId, String subjectCode);

	/**
	 * 班级单科总分
	 */
	Float findTeamTotalScoreBySubjectCode(String jointExamCode, String subjectCode, Integer teamId);
	/**
	 * 班级单科人数
	 */
	Integer findTeamStudentCountBySubjectCode (String jointExamCode,String subjectCode, Integer teamId);

	/**
	 * 学生分数和班级平均分（小测，最近的十次）
	 * @param userId
	 * @param teamId
	 * @param subjectCode
	 * @param isEnter	是否包含未录入的分数，不为null时score!=-1
	 * @return
	 */
	List<Map<String, Object>> findAvgScoreOfStudent(Integer userId, Integer teamId, String subjectCode, Boolean isEnter);

	List<Integer> findExamWorkStudentRank(Integer examWorksId, Integer gradeId);

	List<Map<String, Object>> findStudentScore(Integer examWorksId, Integer gradeId, Integer studentId);

	List<Map<String, Object>> findExamWorkStudentRankAndTotalScore(Integer examWorksId, Integer gradeId);

}

