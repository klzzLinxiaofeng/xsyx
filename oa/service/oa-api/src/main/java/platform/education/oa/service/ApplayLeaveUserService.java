package platform.education.oa.service;
import platform.education.oa.model.ApplayLeaveUser;
import platform.education.oa.vo.ApplayLeaveUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ApplayLeaveUserService {
    ApplayLeaveUser findApplayLeaveUserById(Integer id);
	   
	ApplayLeaveUser add(ApplayLeaveUser applayLeaveUser);
	   
	ApplayLeaveUser modify(ApplayLeaveUser applayLeaveUser);
	   
	void remove(ApplayLeaveUser applayLeaveUser);
	   
	List<ApplayLeaveUser> findApplayLeaveUserByCondition(ApplayLeaveUserCondition applayLeaveUserCondition, Page page, Order order);
	
	List<ApplayLeaveUser> findApplayLeaveUserByCondition(ApplayLeaveUserCondition applayLeaveUserCondition);
	
	List<ApplayLeaveUser> findApplayLeaveUserByCondition(ApplayLeaveUserCondition applayLeaveUserCondition, Page page);
	
	List<ApplayLeaveUser> findApplayLeaveUserByCondition(ApplayLeaveUserCondition applayLeaveUserCondition, Order order);
	
	Long count();
	
	Long count(ApplayLeaveUserCondition applayLeaveUserCondition);
	
}
