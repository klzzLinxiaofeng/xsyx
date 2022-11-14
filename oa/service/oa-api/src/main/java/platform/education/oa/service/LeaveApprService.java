package platform.education.oa.service;
import platform.education.oa.model.LeaveAppr;
import platform.education.oa.vo.LeaveApprCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LeaveApprService {
    LeaveAppr findLeaveApprById(Integer id);
    LeaveAppr findLeaveApprByLeaveId(Integer id);
	   
	LeaveAppr add(LeaveAppr leaveAppr);
	   
	LeaveAppr modify(LeaveAppr leaveAppr);
	   
	void remove(LeaveAppr leaveAppr);
	   
	List<LeaveAppr> findLeaveApprByCondition(LeaveApprCondition leaveApprCondition, Page page, Order order);
	
	List<LeaveAppr> findLeaveApprByCondition(LeaveApprCondition leaveApprCondition);
	
	List<LeaveAppr> findLeaveApprByCondition(LeaveApprCondition leaveApprCondition, Page page);
	
	List<LeaveAppr> findLeaveApprByCondition(LeaveApprCondition leaveApprCondition, Order order);
	
	Long count();
	
	Long count(LeaveApprCondition leaveApprCondition);
	
}
