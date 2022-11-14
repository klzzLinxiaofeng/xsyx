package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksSubject;
import platform.education.generalTeachingAffair.vo.ExamWorksSubjectCondition;

import java.util.List;
import java.util.Map;

public interface ExamWorksSubjectDao extends GenericDao<ExamWorksSubject, Integer> {

	List<ExamWorksSubject> findExamWorksSubjectByCondition(ExamWorksSubjectCondition examWorksSubjectCondition, Page page, Order order);
	
	ExamWorksSubject findById(Integer id);
	
	Long count(ExamWorksSubjectCondition examWorksSubjectCondition);

	ExamWorksSubject findUnique(Integer examWorksId, Integer gradeId, String subjectCode);

	List<ExamWorksSubject> findByExamWorksId(Integer examWorksId, Integer gradeId, String subjectCode, Boolean statNeeded);

	List<Map<String,Object>> findStatSubjects(Integer examWorksId, Integer gradeId, String subjectCode, Boolean statNeeded);
}
