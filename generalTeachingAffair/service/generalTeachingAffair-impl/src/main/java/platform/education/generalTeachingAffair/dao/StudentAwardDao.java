package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentAward;
import platform.education.generalTeachingAffair.vo.StudentAwardCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentAwardDao extends GenericDao<StudentAward, java.lang.Integer> {

	List<StudentAward> findStudentAwardByCondition(StudentAwardCondition studentAwardCondition, Page page, Order order);
	
	StudentAward findById(Integer id);
	
	Long count(StudentAwardCondition studentAwardCondition);
	
	void deleteByCondition(StudentAwardCondition studentAwardCondition);
}
