package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.ApplayLeaveApproveUser;
import platform.education.oa.vo.ApplayLeaveApproveUserCondition;
import platform.education.oa.service.ApplayLeaveApproveUserService;
import platform.education.oa.dao.ApplayLeaveApproveUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ApplayLeaveApproveUserServiceImpl implements ApplayLeaveApproveUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplayLeaveApproveUserDao applayLeaveApproveUserDao;

	public void setApplayLeaveApproveUserDao(ApplayLeaveApproveUserDao applayLeaveApproveUserDao) {
		this.applayLeaveApproveUserDao = applayLeaveApproveUserDao;
	}
	
	@Override
	public ApplayLeaveApproveUser findApplayLeaveApproveUserById(Integer id) {
		try {
			return applayLeaveApproveUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplayLeaveApproveUser add(ApplayLeaveApproveUser applayLeaveApproveUser) {
		if(applayLeaveApproveUser == null) {
    		return null;
    	}
    	Date createDate = applayLeaveApproveUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	applayLeaveApproveUser.setCreateDate(createDate);
    	applayLeaveApproveUser.setModifyDate(createDate);
		return applayLeaveApproveUserDao.create(applayLeaveApproveUser);
	}

	@Override
	public ApplayLeaveApproveUser modify(ApplayLeaveApproveUser applayLeaveApproveUser) {
		if(applayLeaveApproveUser == null) {
    		return null;
    	}
    	Date modify = applayLeaveApproveUser.getModifyDate();
    	applayLeaveApproveUser.setModifyDate(modify != null ? modify : new Date());
		return applayLeaveApproveUserDao.update(applayLeaveApproveUser);
	}
	
	@Override
	public void remove(ApplayLeaveApproveUser applayLeaveApproveUser) {
		try {
			applayLeaveApproveUserDao.delete(applayLeaveApproveUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", applayLeaveApproveUser.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApplayLeaveApproveUser> findApplayLeaveApproveUserByCondition(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition, Page page, Order order) {
		return applayLeaveApproveUserDao.findApplayLeaveApproveUserByCondition(applayLeaveApproveUserCondition, page, order);
	}
	
	@Override
	public List<ApplayLeaveApproveUser> findApplayLeaveApproveUserByCondition(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition) {
		return applayLeaveApproveUserDao.findApplayLeaveApproveUserByCondition(applayLeaveApproveUserCondition, null, null);
	}
	
	@Override
	public List<ApplayLeaveApproveUser> findApplayLeaveApproveUserByCondition(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition, Page page) {
		return applayLeaveApproveUserDao.findApplayLeaveApproveUserByCondition(applayLeaveApproveUserCondition, page, null);
	}
	
	@Override
	public List<ApplayLeaveApproveUser> findApplayLeaveApproveUserByCondition(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition, Order order) {
		return applayLeaveApproveUserDao.findApplayLeaveApproveUserByCondition(applayLeaveApproveUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.applayLeaveApproveUserDao.count(null);
	}

	@Override
	public Long count(ApplayLeaveApproveUserCondition applayLeaveApproveUserCondition) {
		return this.applayLeaveApproveUserDao.count(applayLeaveApproveUserCondition);
	}

}
