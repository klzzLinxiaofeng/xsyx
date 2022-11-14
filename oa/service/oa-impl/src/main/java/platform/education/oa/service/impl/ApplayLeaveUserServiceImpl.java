package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.ApplayLeaveUser;
import platform.education.oa.vo.ApplayLeaveUserCondition;
import platform.education.oa.service.ApplayLeaveUserService;
import platform.education.oa.dao.ApplayLeaveUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ApplayLeaveUserServiceImpl implements ApplayLeaveUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplayLeaveUserDao applayLeaveUserDao;

	public void setApplayLeaveUserDao(ApplayLeaveUserDao applayLeaveUserDao) {
		this.applayLeaveUserDao = applayLeaveUserDao;
	}
	
	@Override
	public ApplayLeaveUser findApplayLeaveUserById(Integer id) {
		try {
			return applayLeaveUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplayLeaveUser add(ApplayLeaveUser applayLeaveUser) {
		if(applayLeaveUser == null) {
    		return null;
    	}
    	Date createDate = applayLeaveUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	applayLeaveUser.setCreateDate(createDate);
    	applayLeaveUser.setModifyDate(createDate);
		return applayLeaveUserDao.create(applayLeaveUser);
	}

	@Override
	public ApplayLeaveUser modify(ApplayLeaveUser applayLeaveUser) {
		if(applayLeaveUser == null) {
    		return null;
    	}
    	Date modify = applayLeaveUser.getModifyDate();
    	applayLeaveUser.setModifyDate(modify != null ? modify : new Date());
		return applayLeaveUserDao.update(applayLeaveUser);
	}
	
	@Override
	public void remove(ApplayLeaveUser applayLeaveUser) {
		try {
			applayLeaveUserDao.delete(applayLeaveUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", applayLeaveUser.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApplayLeaveUser> findApplayLeaveUserByCondition(ApplayLeaveUserCondition applayLeaveUserCondition, Page page, Order order) {
		return applayLeaveUserDao.findApplayLeaveUserByCondition(applayLeaveUserCondition, page, order);
	}
	
	@Override
	public List<ApplayLeaveUser> findApplayLeaveUserByCondition(ApplayLeaveUserCondition applayLeaveUserCondition) {
		return applayLeaveUserDao.findApplayLeaveUserByCondition(applayLeaveUserCondition, null, null);
	}
	
	@Override
	public List<ApplayLeaveUser> findApplayLeaveUserByCondition(ApplayLeaveUserCondition applayLeaveUserCondition, Page page) {
		return applayLeaveUserDao.findApplayLeaveUserByCondition(applayLeaveUserCondition, page, null);
	}
	
	@Override
	public List<ApplayLeaveUser> findApplayLeaveUserByCondition(ApplayLeaveUserCondition applayLeaveUserCondition, Order order) {
		return applayLeaveUserDao.findApplayLeaveUserByCondition(applayLeaveUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.applayLeaveUserDao.count(null);
	}

	@Override
	public Long count(ApplayLeaveUserCondition applayLeaveUserCondition) {
		return this.applayLeaveUserDao.count(applayLeaveUserCondition);
	}

}
