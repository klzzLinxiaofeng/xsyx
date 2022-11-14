package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksSubject;
import platform.education.generalTeachingAffair.vo.ExamWorksSubjectCondition;

import java.util.List;
import java.util.Map;

public interface ExamWorksSubjectService {
    ExamWorksSubject findExamWorksSubjectById(Integer id);
	   
	ExamWorksSubject add(ExamWorksSubject examWorksSubject);
	   
	ExamWorksSubject modify(ExamWorksSubject examWorksSubject);
	   
	void remove(ExamWorksSubject examWorksSubject);
	   
	List<ExamWorksSubject> findExamWorksSubjectByCondition(ExamWorksSubjectCondition examWorksSubjectCondition, Page page, Order order);
	
	List<ExamWorksSubject> findExamWorksSubjectByCondition(ExamWorksSubjectCondition examWorksSubjectCondition);
	
	List<ExamWorksSubject> findExamWorksSubjectByCondition(ExamWorksSubjectCondition examWorksSubjectCondition, Page page);
	
	List<ExamWorksSubject> findExamWorksSubjectByCondition(ExamWorksSubjectCondition examWorksSubjectCondition, Order order);
	
	Long count();
	
	Long count(ExamWorksSubjectCondition examWorksSubjectCondition);

	/**
	 * 获取单科三率一分
	 * @param examWorksId
	 * @param gradeId
	 * @param subjectCode
	 * @return
	 */
	ExamWorksSubject findUnique(Integer examWorksId, Integer gradeId, String subjectCode);

	ExamWorksSubject findUniqueWithTemplate(Integer examWorksId, Integer gradeId, String subjectCode);

	/**
	 * 获取一次统考的所有年级班级及其考试科目设置
	 * @return
	 */
	List<ExamWorksSubject> findByEamWorksId(Integer examWorksId, Integer gradeId, String subjectCode, Boolean statNeeded);

	/**
	 * 查询统考中年级的各个科目的三率一分设置和参考科目
	 * @param examWorksId
	 * @param gradeId
	 * @return
	 */
	List<ExamWorksSubject> findSubjectsOfExamWorks(Integer examWorksId, Integer gradeId);


	List<Map<String,Object>> findStatSubjects(Integer examWorksId, Integer gradeId, String subjectCode, Boolean statNeeded);
	

}
