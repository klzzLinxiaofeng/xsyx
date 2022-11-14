package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.SchoolAttributePlatform;
import platform.education.generalTeachingAffair.vo.SchoolAttributePlatformCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SchoolAttributePlatformDao extends GenericDao<SchoolAttributePlatform, java.lang.Integer> {

	List<SchoolAttributePlatform> findSchoolAttributePlatformByCondition(SchoolAttributePlatformCondition schoolAttributePlatformCondition, Page page, Order order);
	
	SchoolAttributePlatform findById(Integer id);
	
	Long count(SchoolAttributePlatformCondition schoolAttributePlatformCondition);
	
}
