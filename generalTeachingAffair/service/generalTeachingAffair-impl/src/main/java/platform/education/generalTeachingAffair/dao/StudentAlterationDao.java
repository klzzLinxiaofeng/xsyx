package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentAlteration;
import platform.education.generalTeachingAffair.vo.StudentAlterationCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentAlterationDao extends GenericDao<StudentAlteration, java.lang.Integer> {

	List<StudentAlteration> findStudentAlterationByCondition(StudentAlterationCondition studentAlterationCondition, Page page, Order order);
	
	StudentAlteration findById(Integer id);
	
	Long count(StudentAlterationCondition studentAlterationCondition);
	
	StudentAlteration findStudentRecord(Integer teamId, Integer studentId, String alterType);
}
