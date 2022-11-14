package platform.education.oa.dao;

import platform.education.oa.model.ApplayLeaveUser;
import platform.education.oa.vo.ApplayLeaveUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApplayLeaveUserDao extends GenericDao<ApplayLeaveUser, java.lang.Integer> {

	List<ApplayLeaveUser> findApplayLeaveUserByCondition(ApplayLeaveUserCondition applayLeaveUserCondition, Page page, Order order);
	
	ApplayLeaveUser findById(Integer id);
	
	Long count(ApplayLeaveUserCondition applayLeaveUserCondition);
	
}
