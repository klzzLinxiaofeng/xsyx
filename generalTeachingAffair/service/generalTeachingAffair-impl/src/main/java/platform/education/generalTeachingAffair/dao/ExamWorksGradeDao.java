package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksGrade;
import platform.education.generalTeachingAffair.vo.ExamWorksGradeCondition;

import java.util.List;
import java.util.Map;

public interface ExamWorksGradeDao extends GenericDao<ExamWorksGrade, Integer> {

	List<ExamWorksGrade> findExamWorksGradeByCondition(ExamWorksGradeCondition examWorksGradeCondition, Page page, Order order);
	
	ExamWorksGrade findById(Integer id);
	
	Long count(ExamWorksGradeCondition examWorksGradeCondition);

	List<ExamWorksGrade> findOfExamWorks(Integer examWorksId);

	ExamWorksGrade findUnique(Integer examWorksId, Integer gradeId, String jointExamCode);

	List<Map<String, Object>> findOfExamWorksWithScore(Integer examWorksId, Integer gradeId);
	
}
