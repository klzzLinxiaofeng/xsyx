package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.StudentEmployment;
import platform.education.generalTeachingAffair.vo.StudentEmploymentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentEmploymentService {
    StudentEmployment findStudentEmploymentById(Integer id);
	   
	StudentEmployment add(StudentEmployment studentEmployment);
	   
	StudentEmployment modify(StudentEmployment studentEmployment);
	   
	void remove(StudentEmployment studentEmployment);
	   
	List<StudentEmployment> findStudentEmploymentByCondition(StudentEmploymentCondition studentEmploymentCondition, Page page, Order order);
	
	List<StudentEmployment> findStudentEmploymentByCondition(StudentEmploymentCondition studentEmploymentCondition);
	
	List<StudentEmployment> findStudentEmploymentByCondition(StudentEmploymentCondition studentEmploymentCondition, Page page);
	
	List<StudentEmployment> findStudentEmploymentByCondition(StudentEmploymentCondition studentEmploymentCondition, Order order);
	
	Long count();
	
	Long count(StudentEmploymentCondition studentEmploymentCondition);
	
}
