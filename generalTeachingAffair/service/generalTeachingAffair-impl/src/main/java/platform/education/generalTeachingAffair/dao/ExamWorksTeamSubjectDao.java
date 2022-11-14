package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksTeamSubject;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamSubjectCondition;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamSubjectVo;

import java.util.List;
import java.util.Map;

public interface ExamWorksTeamSubjectDao extends GenericDao<ExamWorksTeamSubject, Integer> {

	List<ExamWorksTeamSubject> findExamWorksTeamSubjectByCondition(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition, Page page, Order order);
	
	ExamWorksTeamSubject findById(Integer id);
	
	Long count(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition);

	ExamWorksTeamSubject findUnique(Integer examWorksId, Integer teamId, String subjectCode);
	
	List<ExamWorksTeamSubjectVo> findExamWorksContext(Integer examWorksId);
	
	List<Map<String,Object>> findExamWorksBySubjectCode(Integer teamId,String examType,Integer userId,String subjectCode);

	void createBatch(ExamWorksTeamSubject[] examWorksTeamSubjects);
	
	
	
	List<Map<String,Object>> findGradeRankByGradeId(Integer examWorksId,Integer gradeId);

	Float findGradeTotalScoreBySubjectCode(String jointExamCode,String subjectCode, Integer teamId);
	
	List<Map<String,Object>> findGradeRankBySubjectCode(Integer gradeId,String subjectCode,Integer examWorksId);
	Integer findGradeStudentCountBySubjectCode (String jointExamCode,String subjectCode, Integer teamId);

	List<Map<String,Object>> findMajorExamWorksByTeam(Integer examWorksId, Integer teamId, Integer examWorksTeamSubjectId, Boolean isStat);

	List<Map<String,Object>> findClassExamWorksByTeam(Integer schoolId, String schoolYear, String termCode, Integer teacherId,
													  Integer teamId, Integer examWorksTeamSubjectId, Page page, Order order);
	
	
	/**
	 * 根据CODE和subjectCode 查询年级最大得分
	 * @param jointExamCode
	 * @param subjectCode
	 * @return
	 */
	Float findGradeMaxScore(String jointExamCode,String subjectCode);


	List<Map<String, Object>> findScoreOfStudent(Integer examWorksId, Integer gradeId, Integer teamId, String subjectCode, Page page, Order order);

//	List<Map<String, Object>> findTotalScoreOfStudent(Integer examWorksId, Integer gradeId, Integer teamId);

	List<Map<String, Object>> findAvgScoreOfGrade(Integer schoolId, Boolean isJoint, Integer gradeId, Integer teamId, String subjectCode);

	List<Map<String, Object>> findAvgScoreOfStudent(Integer userId, Integer teamId, String subjectCode, Boolean isEnter);

	List<Integer> findExamWorkStudentRank(Integer examWorksId, Integer gradeId);
	
	List<Map<String, Object>> findStudentScore(Integer examWorksId, Integer gradeId, Integer studentId);

	List<Map<String, Object>> findExamWorkStudentRankAndTotalScore(Integer examWorksId, Integer gradeId);
}
