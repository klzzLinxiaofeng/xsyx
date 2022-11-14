package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksSubjectTemplate;
import platform.education.generalTeachingAffair.vo.ExamWorksSubjectTemplateCondition;

import java.util.List;

public interface ExamWorksSubjectTemplateDao extends GenericDao<ExamWorksSubjectTemplate, Integer> {

	List<ExamWorksSubjectTemplate> findExamWorksSubjectTemplateByCondition(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition, Page page, Order order);
	
	ExamWorksSubjectTemplate findById(Integer id);
	
	Long count(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition);

	ExamWorksSubjectTemplate findUnique(Integer schoolId, Integer gradeId, String gradeCode, String subjectCode);

	List<ExamWorksSubjectTemplate> findTemplateOfGrade(Integer schoolId, Integer gradeId, String gradeCode);

	List<ExamWorksSubjectTemplate> findTemplateByGradeCodes(Integer schoolId, String[] gradeCodes);


}
