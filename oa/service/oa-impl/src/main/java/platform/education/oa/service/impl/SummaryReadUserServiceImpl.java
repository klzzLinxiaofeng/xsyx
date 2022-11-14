package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.SummaryReadUser;
import platform.education.oa.vo.SummaryReadUserCondition;
import platform.education.oa.service.SummaryReadUserService;
import platform.education.oa.dao.SummaryReadUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SummaryReadUserServiceImpl implements SummaryReadUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SummaryReadUserDao summaryReadUserDao;

	public void setSummaryReadUserDao(SummaryReadUserDao summaryReadUserDao) {
		this.summaryReadUserDao = summaryReadUserDao;
	}
	
	@Override
	public SummaryReadUser findSummaryReadUserById(Integer id) {
		try {
			return summaryReadUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SummaryReadUser add(SummaryReadUser summaryReadUser) {
		if(summaryReadUser == null) {
    		return null;
    	}
    	Date createDate = summaryReadUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	summaryReadUser.setCreateDate(createDate);
    	summaryReadUser.setModifyDate(createDate);
		return summaryReadUserDao.create(summaryReadUser);
	}

	@Override
	public SummaryReadUser modify(SummaryReadUser summaryReadUser) {
		if(summaryReadUser == null) {
    		return null;
    	}
    	Date modify = summaryReadUser.getModifyDate();
    	summaryReadUser.setModifyDate(modify != null ? modify : new Date());
		return summaryReadUserDao.update(summaryReadUser);
	}
	
	@Override
	public void remove(SummaryReadUser summaryReadUser) {
		try {
			summaryReadUserDao.delete(summaryReadUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", summaryReadUser.getId(), e);
			}
		}
	}
	
	@Override
	public List<SummaryReadUser> findSummaryReadUserByCondition(SummaryReadUserCondition summaryReadUserCondition, Page page, Order order) {
		return summaryReadUserDao.findSummaryReadUserByCondition(summaryReadUserCondition, page, order);
	}
	
	@Override
	public List<SummaryReadUser> findSummaryReadUserByCondition(SummaryReadUserCondition summaryReadUserCondition) {
		return summaryReadUserDao.findSummaryReadUserByCondition(summaryReadUserCondition, null, null);
	}
	
	@Override
	public List<SummaryReadUser> findSummaryReadUserByCondition(SummaryReadUserCondition summaryReadUserCondition, Page page) {
		return summaryReadUserDao.findSummaryReadUserByCondition(summaryReadUserCondition, page, null);
	}
	
	@Override
	public List<SummaryReadUser> findSummaryReadUserByCondition(SummaryReadUserCondition summaryReadUserCondition, Order order) {
		return summaryReadUserDao.findSummaryReadUserByCondition(summaryReadUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.summaryReadUserDao.count(null);
	}

	@Override
	public Long count(SummaryReadUserCondition summaryReadUserCondition) {
		return this.summaryReadUserDao.count(summaryReadUserCondition);
	}

	@Override
	public SummaryReadUser findBySummaryAndUserId(Integer sid, Integer uid) {
	 
		return this.summaryReadUserDao.findBySummaryAndUserId(sid, uid);
	}

}
