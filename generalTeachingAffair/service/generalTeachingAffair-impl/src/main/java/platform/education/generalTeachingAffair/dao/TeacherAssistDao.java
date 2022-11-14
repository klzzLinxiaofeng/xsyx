package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.TeacherAssist;
import platform.education.generalTeachingAffair.vo.TeacherAssistCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface TeacherAssistDao extends GenericDao<TeacherAssist, java.lang.Integer> {

	List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition);
	
	List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition, Page page, Order order);
	
	TeacherAssist findById(Integer id);
	
	Long count(TeacherAssistCondition teacherAssistCondition);
	
	TeacherAssist findOfUser(Integer schoolId, Integer userId);
	
}
