package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksSubject;
import platform.education.generalTeachingAffair.model.ExamWorksSubjectTemplate;
import platform.education.generalTeachingAffair.vo.ExamWorksSubjectTemplateCondition;

import java.util.List;

public interface ExamWorksSubjectTemplateService {
    ExamWorksSubjectTemplate findExamWorksSubjectTemplateById(Integer id);
	   
	ExamWorksSubjectTemplate add(ExamWorksSubjectTemplate examWorksSubjectTemplate);
	   
	ExamWorksSubjectTemplate modify(ExamWorksSubjectTemplate examWorksSubjectTemplate);
	   
	void remove(ExamWorksSubjectTemplate examWorksSubjectTemplate);
	   
	List<ExamWorksSubjectTemplate> findExamWorksSubjectTemplateByCondition(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition, Page page, Order order);
	
	List<ExamWorksSubjectTemplate> findExamWorksSubjectTemplateByCondition(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition);
	
	List<ExamWorksSubjectTemplate> findExamWorksSubjectTemplateByCondition(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition, Page page);
	
	List<ExamWorksSubjectTemplate> findExamWorksSubjectTemplateByCondition(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition, Order order);
	
	Long count();
	
	Long count(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition);

	ExamWorksSubjectTemplate findSubjectTemplate(Integer schoolId, Integer gradeId, String subjectCode);

	ExamWorksSubjectTemplate findUnique(Integer schoolId, Integer gradeId, String gradeCode, String subjectCode);

	List<ExamWorksSubjectTemplate> acquireTemplateOfGrade(Integer schoolId, Integer gradeId);

	List<ExamWorksSubjectTemplate> acquireTemplateOfGrade(Integer schoolId, Integer gradeId, String gradeCode);

	/**
	 * 同步学校三率一分模板
	 * @param schoolId
	 * @param stages
	 */
	void syncSchoolTemplate(Integer schoolId, String stages);

}
