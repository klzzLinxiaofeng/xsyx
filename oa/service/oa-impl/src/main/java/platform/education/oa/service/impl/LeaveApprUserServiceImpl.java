package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.LeaveApprUser;
import platform.education.oa.vo.LeaveApprUserCondition;
import platform.education.oa.service.LeaveApprUserService;
import platform.education.oa.dao.LeaveApprUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LeaveApprUserServiceImpl implements LeaveApprUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private LeaveApprUserDao leaveApprUserDao;

	public void setLeaveApprUserDao(LeaveApprUserDao leaveApprUserDao) {
		this.leaveApprUserDao = leaveApprUserDao;
	}
	
	@Override
	public LeaveApprUser findLeaveApprUserById(Integer id) {
		try {
			return leaveApprUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public LeaveApprUser add(LeaveApprUser leaveApprUser) {
		if(leaveApprUser == null) {
    		return null;
    	}
    	Date createDate = leaveApprUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	leaveApprUser.setCreateDate(createDate);
    	leaveApprUser.setModifyDate(createDate);
		return leaveApprUserDao.create(leaveApprUser);
	}

	@Override
	public LeaveApprUser modify(LeaveApprUser leaveApprUser) {
		if(leaveApprUser == null) {
    		return null;
    	}
    	Date modify = leaveApprUser.getModifyDate();
    	leaveApprUser.setModifyDate(modify != null ? modify : new Date());
		return leaveApprUserDao.update(leaveApprUser);
	}
	
	@Override
	public void remove(LeaveApprUser leaveApprUser) {
		try {
			leaveApprUserDao.delete(leaveApprUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", leaveApprUser.getId(), e);
			}
		}
	}
	
	@Override
	public List<LeaveApprUser> findLeaveApprUserByCondition(LeaveApprUserCondition leaveApprUserCondition, Page page, Order order) {
		return leaveApprUserDao.findLeaveApprUserByCondition(leaveApprUserCondition, page, order);
	}
	
	@Override
	public List<LeaveApprUser> findLeaveApprUserByCondition(LeaveApprUserCondition leaveApprUserCondition) {
		return leaveApprUserDao.findLeaveApprUserByCondition(leaveApprUserCondition, null, null);
	}
	
	@Override
	public List<LeaveApprUser> findLeaveApprUserByCondition(LeaveApprUserCondition leaveApprUserCondition, Page page) {
		return leaveApprUserDao.findLeaveApprUserByCondition(leaveApprUserCondition, page, null);
	}
	
	@Override
	public List<LeaveApprUser> findLeaveApprUserByCondition(LeaveApprUserCondition leaveApprUserCondition, Order order) {
		return leaveApprUserDao.findLeaveApprUserByCondition(leaveApprUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.leaveApprUserDao.count(null);
	}

	@Override
	public Long count(LeaveApprUserCondition leaveApprUserCondition) {
		return this.leaveApprUserDao.count(leaveApprUserCondition);
	}

}
