package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.StudentMilitary;
import platform.education.generalTeachingAffair.vo.StudentMilitaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentMilitaryService {
    StudentMilitary findStudentMilitaryById(Integer id);
	   
	StudentMilitary add(StudentMilitary studentMilitary);
	   
	StudentMilitary modify(StudentMilitary studentMilitary);
	   
	void remove(StudentMilitary studentMilitary);
	   
	List<StudentMilitary> findStudentMilitaryByCondition(StudentMilitaryCondition studentMilitaryCondition, Page page, Order order);
	
	List<StudentMilitary> findStudentMilitaryByCondition(StudentMilitaryCondition studentMilitaryCondition);
	
	List<StudentMilitary> findStudentMilitaryByCondition(StudentMilitaryCondition studentMilitaryCondition, Page page);
	
	List<StudentMilitary> findStudentMilitaryByCondition(StudentMilitaryCondition studentMilitaryCondition, Order order);
	
	Long count();
	
	Long count(StudentMilitaryCondition studentMilitaryCondition);
	
}
