package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.GroupTaskStudent;
import platform.education.generalTeachingAffair.vo.GroupTaskStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface GroupTaskStudentService {
    GroupTaskStudent findGroupTaskStudentById(Integer id);
	   
	GroupTaskStudent add(GroupTaskStudent groupTaskStudent);
	   
	GroupTaskStudent modify(GroupTaskStudent groupTaskStudent);
	   
	void remove(GroupTaskStudent groupTaskStudent);
	   
	List<GroupTaskStudent> findGroupTaskStudentByCondition(GroupTaskStudentCondition groupTaskStudentCondition, Page page, Order order);
	
	List<GroupTaskStudent> findGroupTaskStudentByCondition(GroupTaskStudentCondition groupTaskStudentCondition);
	
	List<GroupTaskStudent> findGroupTaskStudentByCondition(GroupTaskStudentCondition groupTaskStudentCondition, Page page);
	
	List<GroupTaskStudent> findGroupTaskStudentByCondition(GroupTaskStudentCondition groupTaskStudentCondition, Order order);
	
	Long count();
	
	Long count(GroupTaskStudentCondition groupTaskStudentCondition);

	void batchCreate(GroupTaskStudent[] list);
}
