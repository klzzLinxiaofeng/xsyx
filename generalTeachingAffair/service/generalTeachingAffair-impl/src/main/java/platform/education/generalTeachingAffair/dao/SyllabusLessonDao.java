package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.SyllabusLesson;
import platform.education.generalTeachingAffair.vo.SyllabusLessonCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SyllabusLessonDao extends GenericDao<SyllabusLesson, java.lang.Integer> {

	List<SyllabusLesson> findSyllabusLessonByCondition(SyllabusLessonCondition syllabusLessonCondition, Page page, Order order);
	
	SyllabusLesson findById(Integer id);
	
	Long count(SyllabusLessonCondition syllabusLessonCondition);
	
	List<SyllabusLesson> findBySyllabusId(Integer syllabusId);
	
	List<SyllabusLesson> findTeacherSyllabus(Integer teacherId, String termCode);
	
	Integer deleteBySyllabusId(Integer syllabusId);

	Integer deleteByCondition(SyllabusLessonCondition condition);

	SyllabusLesson create(SyllabusLesson syllabusLesson);

}
