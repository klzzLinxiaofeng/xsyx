package platform.education.oa.service;
import platform.education.oa.model.LeaveApprUser;
import platform.education.oa.vo.LeaveApprUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LeaveApprUserService {
    LeaveApprUser findLeaveApprUserById(Integer id);
	   
	LeaveApprUser add(LeaveApprUser leaveApprUser);
	   
	LeaveApprUser modify(LeaveApprUser leaveApprUser);
	   
	void remove(LeaveApprUser leaveApprUser);
	   
	List<LeaveApprUser> findLeaveApprUserByCondition(LeaveApprUserCondition leaveApprUserCondition, Page page, Order order);
	
	List<LeaveApprUser> findLeaveApprUserByCondition(LeaveApprUserCondition leaveApprUserCondition);
	
	List<LeaveApprUser> findLeaveApprUserByCondition(LeaveApprUserCondition leaveApprUserCondition, Page page);
	
	List<LeaveApprUser> findLeaveApprUserByCondition(LeaveApprUserCondition leaveApprUserCondition, Order order);
	
	Long count();
	
	Long count(LeaveApprUserCondition leaveApprUserCondition);
	
}
