package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentPunish;
import platform.education.generalTeachingAffair.vo.StudentPunishCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentPunishDao extends GenericDao<StudentPunish, java.lang.Integer> {

	List<StudentPunish> findStudentPunishByCondition(StudentPunishCondition studentPunishCondition, Page page, Order order);
	
	StudentPunish findById(Integer id);
	
	Long count(StudentPunishCondition studentPunishCondition);
	
}
