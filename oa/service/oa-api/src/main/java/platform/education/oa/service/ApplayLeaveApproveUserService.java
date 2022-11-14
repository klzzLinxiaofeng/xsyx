package platform.education.oa.service;
import platform.education.oa.model.ApplayLeaveApproveUser;
import platform.education.oa.vo.ApplayLeaveApproveUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ApplayLeaveApproveUserService {
    ApplayLeaveApproveUser findApplayLeaveApproveUserById(Integer id);
	   
	ApplayLeaveApproveUser add(ApplayLeaveApproveUser applayLeaveApproveUser);
	   
	ApplayLeaveApproveUser modify(ApplayLeaveApproveUser applayLeaveApproveUser);
	   
	void remove(ApplayLeaveApproveUser applayLeaveApproveUser);
	   
	List<ApplayLeaveApproveUser> findApplayLeaveApproveUserByCondition(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition, Page page, Order order);
	
	List<ApplayLeaveApproveUser> findApplayLeaveApproveUserByCondition(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition);
	
	List<ApplayLeaveApproveUser> findApplayLeaveApproveUserByCondition(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition, Page page);
	
	List<ApplayLeaveApproveUser> findApplayLeaveApproveUserByCondition(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition, Order order);
	
	Long count();
	
	Long count(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition);
	
}
