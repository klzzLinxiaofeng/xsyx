package platform.education.oa.dao;

import platform.education.oa.model.ApplayLeaveApproveUser;
import platform.education.oa.vo.ApplayLeaveApproveUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApplayLeaveApproveUserDao extends GenericDao<ApplayLeaveApproveUser, java.lang.Integer> {

	List<ApplayLeaveApproveUser> findApplayLeaveApproveUserByCondition(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition, Page page, Order order);
	
	ApplayLeaveApproveUser findById(Integer id);
	
	Long count(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition);
	
}
