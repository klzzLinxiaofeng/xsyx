package platform.education.oa.dao;

import platform.education.oa.model.LeaveAppr;
import platform.education.oa.vo.LeaveApprCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LeaveApprDao extends GenericDao<LeaveAppr, java.lang.Integer> {

	List<LeaveAppr> findLeaveApprByCondition(LeaveApprCondition leaveApprCondition, Page page, Order order);
	
	LeaveAppr findById(Integer id);
	LeaveAppr findLeaveApprByLeaveId(Integer id);
	
	Long count(LeaveApprCondition leaveApprCondition);
	
}
