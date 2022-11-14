package platform.education.oa.dao;

import platform.education.oa.model.LeaveApprUser;
import platform.education.oa.vo.LeaveApprUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LeaveApprUserDao extends GenericDao<LeaveApprUser, java.lang.Integer> {

	List<LeaveApprUser> findLeaveApprUserByCondition(LeaveApprUserCondition leaveApprUserCondition, Page page, Order order);
	
	LeaveApprUser findById(Integer id);
	
	Long count(LeaveApprUserCondition leaveApprUserCondition);
	
}
