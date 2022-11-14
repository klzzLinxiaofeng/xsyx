package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentScore;
import platform.education.generalTeachingAffair.vo.StudentScoreCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentScoreDao extends GenericDao<StudentScore, java.lang.Integer> {

	List<StudentScore> findStudentScoreByCondition(StudentScoreCondition studentScoreCondition, Page page, Order order);
	
	StudentScore findById(Integer id);
	
	Long count(StudentScoreCondition studentScoreCondition);

	void deleteByExamTeamSubjectId(StudentScore studentScore);

	void updateByexamTeamSubjectId(StudentScore studentScore);
	StudentScore findByStudentIdAndExamId(Integer studentId,Integer examId);
	List<StudentScore> findByYearAndTrem(Integer studentId,String schoolYear,String schoolTrem,String subjectCode,Integer examType,Integer gradeId,Integer teamId,Integer examName);
}
