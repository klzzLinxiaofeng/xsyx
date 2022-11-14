package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.StudentScore;
import platform.education.generalTeachingAffair.vo.StudentScoreCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentScoreService {
    StudentScore findStudentScoreById(Integer id);
	   
	StudentScore add(StudentScore studentScore);
	   
	StudentScore modify(StudentScore studentScore);

	void modifyByexamTeamSubjectId(StudentScore studentScore);

	void remove(StudentScore studentScore);

	void removeByExamTeamSubjectId(StudentScore studentScore);

	List<StudentScore> findStudentScoreByCondition(StudentScoreCondition studentScoreCondition, Page page, Order order);
	
	List<StudentScore> findStudentScoreByCondition(StudentScoreCondition studentScoreCondition);
	
	List<StudentScore> findStudentScoreByCondition(StudentScoreCondition studentScoreCondition, Page page);
	
	List<StudentScore> findStudentScoreByCondition(StudentScoreCondition studentScoreCondition, Order order);
	
	Long count();
	
	Long count(StudentScoreCondition studentScoreCondition);
	
}
