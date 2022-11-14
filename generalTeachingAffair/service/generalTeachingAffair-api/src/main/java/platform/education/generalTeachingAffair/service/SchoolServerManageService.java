package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.SchoolServerManage;
import platform.education.generalTeachingAffair.vo.SchoolServerManageCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SchoolServerManageService {
    SchoolServerManage findSchoolServerManageById(Integer id);
	   
	SchoolServerManage add(SchoolServerManage schoolServerManage);
	   
	SchoolServerManage modify(SchoolServerManage schoolServerManage);
	   
	void remove(SchoolServerManage schoolServerManage);
	   
	List<SchoolServerManage> findSchoolServerManageByCondition(SchoolServerManageCondition schoolServerManageCondition, Page page, Order order);
	
	List<SchoolServerManage> findSchoolServerManageByCondition(SchoolServerManageCondition schoolServerManageCondition);
	
	List<SchoolServerManage> findSchoolServerManageByCondition(SchoolServerManageCondition schoolServerManageCondition, Page page);
	
	List<SchoolServerManage> findSchoolServerManageByCondition(SchoolServerManageCondition schoolServerManageCondition, Order order);
	
	Long count();
	
	Long count(SchoolServerManageCondition schoolServerManageCondition);
	
	
	SchoolServerManage findSchoolServerManageBySchoolId(Integer schoolId);
	
	List<SchoolServerManage> findSchoolServerManageBySchoolName(String schoolName);
	
}
