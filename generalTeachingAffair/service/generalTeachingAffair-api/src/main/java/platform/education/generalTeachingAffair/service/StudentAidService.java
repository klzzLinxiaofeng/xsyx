package platform.education.generalTeachingAffair.service;

import java.util.List;

import platform.education.generalTeachingAffair.model.StudentAid;
import platform.education.generalTeachingAffair.vo.StudentAidCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


public interface StudentAidService {
	StudentAid findStudentAidById(Integer id);

	StudentAid add(StudentAid StudentAid);

	StudentAid modify(StudentAid StudentAid);

	void remove(StudentAid StudentAid);

	List<StudentAid> findStudentAidByCondition(StudentAidCondition StudentAidCondition, Page page, Order order);

	List<StudentAid> findStudentAidByCondition(StudentAidCondition StudentAidCondition);

	List<StudentAid> findStudentAidByCondition(StudentAidCondition StudentAidCondition, Page page);

	List<StudentAid> findStudentAidByCondition(StudentAidCondition StudentAidCondition, Order order);

	Long count();

	Long count(StudentAidCondition StudentAidCondition);

}
