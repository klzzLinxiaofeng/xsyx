package platform.education.oa.service;
import platform.education.oa.model.Leave;
import platform.education.oa.vo.LeaveCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LeaveService {
    Leave findLeaveById(Integer id);
	   
	Leave add(Leave leave);
	   
	Leave modify(Leave leave);
	   
	void remove(Leave leave);
	   
	List<Leave> findLeaveByCondition(LeaveCondition leaveCondition, Page page, Order order);
	List<Leave> findLeaveByConditionAppr(LeaveCondition leaveCondition, Page page, Order order);
	List<Leave> findLeaveByLeaveAndApprToUser(Integer leavestate,Integer schoolId,Integer userId, Page page, Order order);
	List<Leave> findLeaveByCondition(LeaveCondition leaveCondition);
	
	List<Leave> findLeaveByCondition(LeaveCondition leaveCondition, Page page);
	
	List<Leave> findLeaveByCondition(LeaveCondition leaveCondition, Order order);
	
	Long count();
	
	Long count(LeaveCondition leaveCondition);

	Leave findByUUID(String uuid);
	
}
