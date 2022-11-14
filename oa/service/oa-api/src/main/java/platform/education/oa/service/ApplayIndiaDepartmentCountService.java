package platform.education.oa.service;
import platform.education.oa.model.ApplayIndiaDepartmentCount;
import platform.education.oa.vo.ApplayIndiaDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ApplayIndiaDepartmentCountService {
    ApplayIndiaDepartmentCount findApplayIndiaDepartmentCountById(Integer id);
	   
	ApplayIndiaDepartmentCount add(ApplayIndiaDepartmentCount applayIndiaDepartmentCount);
	   
	ApplayIndiaDepartmentCount modify(ApplayIndiaDepartmentCount applayIndiaDepartmentCount);
	   
	void remove(ApplayIndiaDepartmentCount applayIndiaDepartmentCount);
	   
	List<ApplayIndiaDepartmentCount> findApplayIndiaDepartmentCountByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition, Page page, Order order);
	
	List<ApplayIndiaDepartmentCount> findApplayIndiaDepartmentCountByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition);
	
	List<ApplayIndiaDepartmentCount> findApplayIndiaDepartmentCountByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition, Page page);
	
	List<ApplayIndiaDepartmentCount> findApplayIndiaDepartmentCountByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition, Order order);
	
	Long count();
	
	Long count(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition);
	
	List<ApplayIndiaDepartmentCount>findApplayIndiaSqnumByCondition(ApplayIndiaDepartmentCountCondition sqCondition);
	
}
