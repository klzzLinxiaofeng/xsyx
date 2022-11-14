package platform.education.oa.dao;

import platform.education.oa.model.PaperDepartmentCount;
import platform.education.oa.vo.PaperDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaperDepartmentCountDao extends GenericDao<PaperDepartmentCount, java.lang.Integer> {

	List<PaperDepartmentCount> findPaperDepartmentCountByCondition(PaperDepartmentCountCondition paperDepartmentCountCondition, Page page, Order order);
	
	PaperDepartmentCount findById(Integer id);
	
	Long count(PaperDepartmentCountCondition paperDepartmentCountCondition);
	
}
