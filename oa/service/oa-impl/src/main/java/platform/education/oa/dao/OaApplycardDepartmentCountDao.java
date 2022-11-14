package platform.education.oa.dao;

import platform.education.oa.model.OaApplycardDepartmentCount;
import platform.education.oa.vo.OaApplycardDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface OaApplycardDepartmentCountDao extends GenericDao<OaApplycardDepartmentCount, java.lang.Integer> {

	List<OaApplycardDepartmentCount> findOaApplycardDepartmentCountByCondition(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition, Page page, Order order);
	
	OaApplycardDepartmentCount findById(Integer id);
	
	Long count(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition);

	List<OaApplycardDepartmentCount> findOaApplycardSqnumByCondition(
			OaApplycardDepartmentCountCondition sqCondition);
	
}
