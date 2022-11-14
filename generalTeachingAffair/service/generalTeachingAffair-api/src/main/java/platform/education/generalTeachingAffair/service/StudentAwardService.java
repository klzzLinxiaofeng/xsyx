package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.StudentAward;
import platform.education.generalTeachingAffair.vo.StudentAwardCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentAwardService {
    StudentAward findStudentAwardById(Integer id);
	   
	StudentAward add(StudentAward studentAward);
	   
	StudentAward modify(StudentAward studentAward);
	   
	void remove(StudentAward studentAward);
	   
	List<StudentAward> findStudentAwardByCondition(StudentAwardCondition studentAwardCondition, Page page, Order order);
	
	List<StudentAward> findStudentAwardByCondition(StudentAwardCondition studentAwardCondition);
	
	List<StudentAward> findStudentAwardByCondition(StudentAwardCondition studentAwardCondition, Page page);
	
	List<StudentAward> findStudentAwardByCondition(StudentAwardCondition studentAwardCondition, Order order);
	
	Long count();
	
	Long count(StudentAwardCondition studentAwardCondition);
	
	void remove(StudentAwardCondition studentAwardCondition);
	
}
