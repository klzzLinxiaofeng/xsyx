package platform.education.oa.service;
import platform.education.oa.model.OaApplycardDepartmentCount;
import platform.education.oa.vo.OaApplycardDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface OaApplycardDepartmentCountService {
    OaApplycardDepartmentCount findOaApplycardDepartmentCountById(Integer id);
	   
	OaApplycardDepartmentCount add(OaApplycardDepartmentCount oaApplycardDepartmentCount);
	   
	OaApplycardDepartmentCount modify(OaApplycardDepartmentCount oaApplycardDepartmentCount);
	   
	void remove(OaApplycardDepartmentCount oaApplycardDepartmentCount);
	   
	List<OaApplycardDepartmentCount> findOaApplycardDepartmentCountByCondition(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition, Page page, Order order);
	
	List<OaApplycardDepartmentCount> findOaApplycardDepartmentCountByCondition(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition);
	
	List<OaApplycardDepartmentCount> findOaApplycardDepartmentCountByCondition(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition, Page page);
	
	List<OaApplycardDepartmentCount> findOaApplycardDepartmentCountByCondition(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition, Order order);
	
	Long count();
	
	Long count(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition);

	List<OaApplycardDepartmentCount> findOaApplycardSqnumByCondition(
			OaApplycardDepartmentCountCondition sqCondition);
	
}
