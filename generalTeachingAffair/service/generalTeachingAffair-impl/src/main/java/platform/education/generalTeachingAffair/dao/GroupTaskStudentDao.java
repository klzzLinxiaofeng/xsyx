package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.GroupTaskStudent;
import platform.education.generalTeachingAffair.vo.GroupTaskStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface GroupTaskStudentDao extends GenericDao<GroupTaskStudent, Integer> {

	List<GroupTaskStudent> findGroupTaskStudentByCondition(GroupTaskStudentCondition groupTaskStudentCondition, Page page, Order order);
	
	GroupTaskStudent findById(Integer id);
	
	Long count(GroupTaskStudentCondition groupTaskStudentCondition);

	void batchCreate(GroupTaskStudent[] list);
}
