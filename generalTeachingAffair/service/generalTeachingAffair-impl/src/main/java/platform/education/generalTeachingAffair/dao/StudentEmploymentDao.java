package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentEmployment;
import platform.education.generalTeachingAffair.vo.StudentEmploymentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentEmploymentDao extends GenericDao<StudentEmployment, java.lang.Integer> {

	List<StudentEmployment> findStudentEmploymentByCondition(StudentEmploymentCondition studentEmploymentCondition, Page page, Order order);
	
	StudentEmployment findById(Integer id);
	
	Long count(StudentEmploymentCondition studentEmploymentCondition);
	
}
