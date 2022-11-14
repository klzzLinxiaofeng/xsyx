package platform.education.oa.dao;

import platform.education.oa.model.ApplayIndiaDepartmentCount;
import platform.education.oa.vo.ApplayIndiaDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApplayIndiaDepartmentCountDao extends GenericDao<ApplayIndiaDepartmentCount, java.lang.Integer> {

	List<ApplayIndiaDepartmentCount> findApplayIndiaDepartmentCountByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition, Page page, Order order);
	
	ApplayIndiaDepartmentCount findById(Integer id);
	
	Long count(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition);
	
	List<ApplayIndiaDepartmentCount>findApplayIndiaSqnumByCondition(ApplayIndiaDepartmentCountCondition sqCondition);
}
