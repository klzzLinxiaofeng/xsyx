package platform.education.oa.dao;

import platform.education.oa.model.ApplayLeaveDepartmentCount;
import platform.education.oa.vo.ApplayLeaveDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApplayLeaveDepartmentCountDao extends GenericDao<ApplayLeaveDepartmentCount, java.lang.Integer> {

	List<ApplayLeaveDepartmentCount> findApplayLeaveDepartmentCountByCondition(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition, Page page, Order order);
	
	ApplayLeaveDepartmentCount findById(Integer id);
	
	Long count(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition);
	
	List<ApplayLeaveDepartmentCount>findApplayLeaveSqnumByCondition(ApplayLeaveDepartmentCountCondition sqCondition);
	
}
