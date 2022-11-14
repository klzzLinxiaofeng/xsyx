package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentMilitary;
import platform.education.generalTeachingAffair.vo.StudentMilitaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentMilitaryDao extends GenericDao<StudentMilitary, java.lang.Integer> {

	List<StudentMilitary> findStudentMilitaryByCondition(StudentMilitaryCondition studentMilitaryCondition, Page page, Order order);
	
	StudentMilitary findById(Integer id);
	
	Long count(StudentMilitaryCondition studentMilitaryCondition);
	
}
