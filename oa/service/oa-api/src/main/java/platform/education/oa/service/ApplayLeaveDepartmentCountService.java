package platform.education.oa.service;
import platform.education.oa.model.ApplayLeaveDepartmentCount;
import platform.education.oa.vo.ApplayLeaveDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ApplayLeaveDepartmentCountService {
    ApplayLeaveDepartmentCount findApplayLeaveDepartmentCountById(Integer id);
	   
	ApplayLeaveDepartmentCount add(ApplayLeaveDepartmentCount applayLeaveDepartmentCount);
	   
	ApplayLeaveDepartmentCount modify(ApplayLeaveDepartmentCount applayLeaveDepartmentCount);
	   
	void remove(ApplayLeaveDepartmentCount applayLeaveDepartmentCount);
	   
	List<ApplayLeaveDepartmentCount> findApplayLeaveDepartmentCountByCondition(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition, Page page, Order order);
	
	List<ApplayLeaveDepartmentCount> findApplayLeaveDepartmentCountByCondition(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition);
	
	List<ApplayLeaveDepartmentCount> findApplayLeaveDepartmentCountByCondition(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition, Page page);
	
	List<ApplayLeaveDepartmentCount> findApplayLeaveDepartmentCountByCondition(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition, Order order);
	
	Long count();
	
	Long count(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition);
	
	List<ApplayLeaveDepartmentCount>findApplayLeaveSqnumByCondition(ApplayLeaveDepartmentCountCondition sqCondition);
	
}
