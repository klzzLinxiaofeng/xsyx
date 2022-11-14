package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.LeaveAppr;
import platform.education.oa.vo.LeaveApprCondition;
import platform.education.oa.service.LeaveApprService;
import platform.education.oa.dao.LeaveApprDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LeaveApprServiceImpl implements LeaveApprService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private LeaveApprDao leaveApprDao;

	public void setLeaveApprDao(LeaveApprDao leaveApprDao) {
		this.leaveApprDao = leaveApprDao;
	}
	
	@Override
	public LeaveAppr findLeaveApprById(Integer id) {
		try {
			return leaveApprDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	
	@Override
	public LeaveAppr findLeaveApprByLeaveId(Integer id) {
		try {
			return leaveApprDao.findLeaveApprByLeaveId(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public LeaveAppr add(LeaveAppr leaveAppr) {
		if(leaveAppr == null) {
    		return null;
    	}
    	Date createDate = leaveAppr.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	leaveAppr.setCreateDate(createDate);
    	leaveAppr.setModifyDate(createDate);
		return leaveApprDao.create(leaveAppr);
	}

	@Override
	public LeaveAppr modify(LeaveAppr leaveAppr) {
		if(leaveAppr == null) {
    		return null;
    	}
    	Date modify = leaveAppr.getModifyDate();
    	leaveAppr.setModifyDate(modify != null ? modify : new Date());
		return leaveApprDao.update(leaveAppr);
	}
	
	@Override
	public void remove(LeaveAppr leaveAppr) {
		try {
			leaveApprDao.delete(leaveAppr);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", leaveAppr.getId(), e);
			}
		}
	}
	
	@Override
	public List<LeaveAppr> findLeaveApprByCondition(LeaveApprCondition leaveApprCondition, Page page, Order order) {
		return leaveApprDao.findLeaveApprByCondition(leaveApprCondition, page, order);
	}
	
	@Override
	public List<LeaveAppr> findLeaveApprByCondition(LeaveApprCondition leaveApprCondition) {
		return leaveApprDao.findLeaveApprByCondition(leaveApprCondition, null, null);
	}
	
	@Override
	public List<LeaveAppr> findLeaveApprByCondition(LeaveApprCondition leaveApprCondition, Page page) {
		return leaveApprDao.findLeaveApprByCondition(leaveApprCondition, page, null);
	}
	
	@Override
	public List<LeaveAppr> findLeaveApprByCondition(LeaveApprCondition leaveApprCondition, Order order) {
		return leaveApprDao.findLeaveApprByCondition(leaveApprCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.leaveApprDao.count(null);
	}

	@Override
	public Long count(LeaveApprCondition leaveApprCondition) {
		return this.leaveApprDao.count(leaveApprCondition);
	}

}
