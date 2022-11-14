package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentActivity;
import platform.education.generalTeachingAffair.vo.StudentActivityCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentActivityDao extends GenericDao<StudentActivity, java.lang.Integer> {

	List<StudentActivity> findStudentActivityByCondition(StudentActivityCondition studentActivityCondition, Page page, Order order);
	
	StudentActivity findById(Integer id);
	
	Long count(StudentActivityCondition studentActivityCondition);
	
}
