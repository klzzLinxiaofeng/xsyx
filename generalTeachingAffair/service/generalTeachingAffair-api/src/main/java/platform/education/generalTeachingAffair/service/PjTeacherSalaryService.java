package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjTeacherSalary;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PjTeacherSalaryService {
    PjTeacherSalary findPjTeacherSalaryById(Integer id);
	   
	PjTeacherSalary addPjTeacherSalary(PjTeacherSalary pjTeacherSalary);
	   
	PjTeacherSalary modify(PjTeacherSalary pjTeacherSalary);
	   
	void remove(PjTeacherSalary pjTeacherSalary);
	   
	List<PjTeacherSalary> findPjTeacherSalaryByCondition(PjTeacherSalaryCondition pjTeacherSalaryCondition, Page page, Order order);
	
	List<PjTeacherSalary> findPjTeacherSalaryByCondition(PjTeacherSalaryCondition pjTeacherSalaryCondition);
	
	List<PjTeacherSalary> findPjTeacherSalaryByCondition(PjTeacherSalaryCondition pjTeacherSalaryCondition, Page page);
	
	List<PjTeacherSalary> findPjTeacherSalaryByCondition(PjTeacherSalaryCondition pjTeacherSalaryCondition, Order order);
	
	Long count();
	
	Long count(PjTeacherSalaryCondition pjTeacherSalaryCondition);

	List<Integer> findPjTeacherSalaryYearBySchoolId(Integer schoolId);
	
}
