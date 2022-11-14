package platform.education.oa.service;
import platform.education.oa.model.PaperDepartmentCount;
import platform.education.oa.vo.PaperDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaperDepartmentCountService {
    PaperDepartmentCount findPaperDepartmentCountById(Integer id);
	   
	PaperDepartmentCount add(PaperDepartmentCount paperDepartmentCount);
	   
	PaperDepartmentCount modify(PaperDepartmentCount paperDepartmentCount);
	   
	void remove(PaperDepartmentCount paperDepartmentCount);
	   
	List<PaperDepartmentCount> findPaperDepartmentCountByCondition(PaperDepartmentCountCondition paperDepartmentCountCondition, Page page, Order order);
	
	List<PaperDepartmentCount> findPaperDepartmentCountByCondition(PaperDepartmentCountCondition paperDepartmentCountCondition);
	
	List<PaperDepartmentCount> findPaperDepartmentCountByCondition(PaperDepartmentCountCondition paperDepartmentCountCondition, Page page);
	
	List<PaperDepartmentCount> findPaperDepartmentCountByCondition(PaperDepartmentCountCondition paperDepartmentCountCondition, Order order);
	
	Long count();
	
	
	Long count(PaperDepartmentCountCondition paperDepartmentCountCondition);
	
}
