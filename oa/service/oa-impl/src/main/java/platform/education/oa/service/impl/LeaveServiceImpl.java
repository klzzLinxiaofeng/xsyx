package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Leave;
import platform.education.oa.vo.LeaveCondition;
import platform.education.oa.service.LeaveService;
import platform.education.oa.dao.LeaveDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LeaveServiceImpl implements LeaveService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private LeaveDao leaveDao;

	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}
	
	@Override
	public Leave findLeaveById(Integer id) {
		try {
			return leaveDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Leave findByUUID(String uuid) {
		try {
			return leaveDao.findByUUID(uuid);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", uuid);
		}
		return null;
	}
 
	
	@Override
	public Leave add(Leave leave) {
		if(leave == null) {
    		return null;
    	}
    	Date createDate = leave.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	leave.setCreateDate(createDate);
    	leave.setModifyDate(createDate);
		return leaveDao.create(leave);
	}

	@Override
	public Leave modify(Leave leave) {
		if(leave == null) {
    		return null;
    	}
    	Date modify = leave.getModifyDate();
    	leave.setModifyDate(modify != null ? modify : new Date());
		return leaveDao.update(leave);
	}
	
	@Override
	public void remove(Leave leave) {
		try {
			leaveDao.delete(leave);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", leave.getId(), e);
			}
		}
	}
	
	@Override
	public List<Leave> findLeaveByCondition(LeaveCondition leaveCondition, Page page, Order order) {
		return leaveDao.findLeaveByCondition(leaveCondition, page, order);
	}
	
	@Override
	public List<Leave> findLeaveByConditionAppr(LeaveCondition leaveCondition, Page page, Order order) {
		return leaveDao.findLeaveByConditionAppr(leaveCondition, page, order);
	}
	
	@Override
	public List<Leave> findLeaveByCondition(LeaveCondition leaveCondition) {
		return leaveDao.findLeaveByCondition(leaveCondition, null, null);
	}
	
	@Override
	public List<Leave> findLeaveByCondition(LeaveCondition leaveCondition, Page page) {
		return leaveDao.findLeaveByCondition(leaveCondition, page, null);
	}
	
	@Override
	public List<Leave> findLeaveByCondition(LeaveCondition leaveCondition, Order order) {
		return leaveDao.findLeaveByCondition(leaveCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.leaveDao.count(null);
	}

	@Override
	public Long count(LeaveCondition leaveCondition) {
		return this.leaveDao.count(leaveCondition);
	}

	@Override
	public List<Leave> findLeaveByLeaveAndApprToUser(Integer leavestate,Integer schoolId,
			Integer userId, Page page, Order order) {
		 
		return this.leaveDao.findLeaveByLeaveAndApprToUser(leavestate,schoolId, userId, page, order);
	}

}
