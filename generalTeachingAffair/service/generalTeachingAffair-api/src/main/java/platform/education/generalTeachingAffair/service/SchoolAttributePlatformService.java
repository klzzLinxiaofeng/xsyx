package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.SchoolAttributePlatform;
import platform.education.generalTeachingAffair.vo.SchoolAttributePlatformCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SchoolAttributePlatformService {
    SchoolAttributePlatform findSchoolAttributePlatformById(Integer id);
	   
	SchoolAttributePlatform add(SchoolAttributePlatform schoolAttributePlatform);
	   
	SchoolAttributePlatform modify(SchoolAttributePlatform schoolAttributePlatform);
	   
	void remove(SchoolAttributePlatform schoolAttributePlatform);
	   
	List<SchoolAttributePlatform> findSchoolAttributePlatformByCondition(SchoolAttributePlatformCondition schoolAttributePlatformCondition, Page page, Order order);
	
	List<SchoolAttributePlatform> findSchoolAttributePlatformByCondition(SchoolAttributePlatformCondition schoolAttributePlatformCondition);
	
	List<SchoolAttributePlatform> findSchoolAttributePlatformByCondition(SchoolAttributePlatformCondition schoolAttributePlatformCondition, Page page);
	
	List<SchoolAttributePlatform> findSchoolAttributePlatformByCondition(SchoolAttributePlatformCondition schoolAttributePlatformCondition, Order order);
	
	Long count();
	
	Long count(SchoolAttributePlatformCondition schoolAttributePlatformCondition);
	
}
