package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.SchoolServerManage;
import platform.education.generalTeachingAffair.vo.SchoolServerManageCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SchoolServerManageDao extends GenericDao<SchoolServerManage, java.lang.Integer> {

	List<SchoolServerManage> findSchoolServerManageByCondition(SchoolServerManageCondition schoolServerManageCondition, Page page, Order order);
	
	SchoolServerManage findById(Integer id);
	
	Long count(SchoolServerManageCondition schoolServerManageCondition);
	
	SchoolServerManage findBySchoolId(Integer schoolId);

	List<SchoolServerManage> findBySchoolName(String schoolName);
	
}
