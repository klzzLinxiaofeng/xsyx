package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksGrade;
import platform.education.generalTeachingAffair.vo.ExamWorksGradeCondition;

import java.util.List;
import java.util.Map;

public interface ExamWorksGradeService {
    ExamWorksGrade findExamWorksGradeById(Integer id);
	   
	ExamWorksGrade add(ExamWorksGrade examWorksGrade);
	   
	ExamWorksGrade modify(ExamWorksGrade examWorksGrade);
	   
	void remove(ExamWorksGrade examWorksGrade);
	   
	List<ExamWorksGrade> findExamWorksGradeByCondition(ExamWorksGradeCondition examWorksGradeCondition, Page page, Order order);
	
	List<ExamWorksGrade> findExamWorksGradeByCondition(ExamWorksGradeCondition examWorksGradeCondition);
	
	List<ExamWorksGrade> findExamWorksGradeByCondition(ExamWorksGradeCondition examWorksGradeCondition, Page page);
	
	List<ExamWorksGrade> findExamWorksGradeByCondition(ExamWorksGradeCondition examWorksGradeCondition, Order order);
	
	Long count();
	
	Long count(ExamWorksGradeCondition examWorksGradeCondition);

	List<ExamWorksGrade> findOfExamWorks(Integer examWorksId);

	ExamWorksGrade findUnique(Integer examWorksId, Integer gradeId);

	ExamWorksGrade findUnique(Integer examWorksId, Integer gradeId, String jointExamCode);

	List<Map<String, Object>> findOfExamWorksWithScore(Integer examWorksId);

	Map<String, Object> findOfExamWorksWithScore(Integer examWorksId, Integer gradeId);

}
