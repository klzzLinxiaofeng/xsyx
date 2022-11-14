package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjTeacherSalary;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjTeacherSalaryDao extends GenericDao<PjTeacherSalary, java.lang.Integer> {

	List<PjTeacherSalary> findPjTeacherSalaryByCondition(PjTeacherSalaryCondition pjTeacherSalaryCondition, Page page, Order order);
	
	PjTeacherSalary findById(Integer id);
	
	Long count(PjTeacherSalaryCondition pjTeacherSalaryCondition);

	List<Integer> findPjTeacherSalaryYearBySchoolId(Integer schoolId);
	
}
