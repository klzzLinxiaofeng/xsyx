package platform.education.oa.dao;

import platform.education.oa.model.Leave;
import platform.education.oa.vo.LeaveCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LeaveDao extends GenericDao<Leave, java.lang.Integer> {

	List<Leave> findLeaveByCondition(LeaveCondition leaveCondition, Page page, Order order);
	List<Leave> findLeaveByConditionAppr(LeaveCondition leaveCondition, Page page, Order order);
	public List<Leave> findLeaveByLeaveAndApprToUser(Integer leavestate,Integer schoolId,
			Integer userId, Page page, Order order);
	Leave findById(Integer id);
	Leave findByUUID(String uuid);
	Long count(LeaveCondition leaveCondition);
	
	
	
}
