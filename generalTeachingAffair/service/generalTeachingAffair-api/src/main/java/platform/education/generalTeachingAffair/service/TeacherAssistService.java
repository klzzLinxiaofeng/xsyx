package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.TeacherAssist;
import platform.education.generalTeachingAffair.vo.TeacherAssistCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface TeacherAssistService {
    TeacherAssist findTeacherAssistById(Integer id);
	   
	TeacherAssist add(TeacherAssist teacherAssist);
	   
	TeacherAssist modify(TeacherAssist teacherAssist);
	   
	void remove(TeacherAssist teacherAssist);
	   
	List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition, Page page, Order order);
	
	List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition);
	
	List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition, Page page);
	
	List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition, Order order);
	
	Long count();
	
	Long count(TeacherAssistCondition teacherAssistCondition);
	
	TeacherAssist findOfUser(Integer schoolId, Integer userId);
	
	TeacherAssist updateTeacherAssist(Integer schoolId, Integer userId, TeacherAssist teacherAssist);

	void removeByUserId(Integer id);
	
}
