package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.StudentPunish;
import platform.education.generalTeachingAffair.vo.StudentPunishCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentPunishService {
    StudentPunish findStudentPunishById(Integer id);
	   
	StudentPunish add(StudentPunish studentPunish);
	   
	StudentPunish modify(StudentPunish studentPunish);
	   
	void remove(StudentPunish studentPunish);
	   
	List<StudentPunish> findStudentPunishByCondition(StudentPunishCondition studentPunishCondition, Page page, Order order);
	
	List<StudentPunish> findStudentPunishByCondition(StudentPunishCondition studentPunishCondition);
	
	List<StudentPunish> findStudentPunishByCondition(StudentPunishCondition studentPunishCondition, Page page);
	
	List<StudentPunish> findStudentPunishByCondition(StudentPunishCondition studentPunishCondition, Order order);
	
	Long count();
	
	Long count(StudentPunishCondition studentPunishCondition);
	
}
