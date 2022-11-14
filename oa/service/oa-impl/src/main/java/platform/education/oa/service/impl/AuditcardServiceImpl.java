package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Auditcard;
import platform.education.oa.vo.AuditcardCondition;
import platform.education.oa.service.AuditcardService;
import platform.education.oa.dao.AuditcardDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class AuditcardServiceImpl implements AuditcardService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private AuditcardDao auditcardDao;

	public void setAuditcardDao(AuditcardDao auditcardDao) {
		this.auditcardDao = auditcardDao;
	}
	
	@Override
	public Auditcard findAuditcardById(Integer id) {
		try {
			return auditcardDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Auditcard add(Auditcard auditcard) {
		if(auditcard == null) {
    		return null;
    	}
    	Date createDate = auditcard.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	auditcard.setCreateDate(createDate);
    	auditcard.setModifyDate(createDate);
		return auditcardDao.create(auditcard);
	}

	@Override
	public Auditcard modify(Auditcard auditcard) {
		if(auditcard == null) {
    		return null;
    	}
    	Date modify = auditcard.getModifyDate();
    	auditcard.setModifyDate(modify != null ? modify : new Date());
		return auditcardDao.update(auditcard);
	}
	
	@Override
	public void remove(Auditcard auditcard) {
		try {
			auditcardDao.delete(auditcard);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", auditcard.getId(), e);
			}
		}
	}
	
	@Override
	public List<Auditcard> findAuditcardByCondition(AuditcardCondition auditcardCondition, Page page, Order order) {
		return auditcardDao.findAuditcardByCondition(auditcardCondition, page, order);
	}
	
	@Override
	public List<Auditcard> findAuditcardByCondition(AuditcardCondition auditcardCondition) {
		return auditcardDao.findAuditcardByCondition(auditcardCondition, null, null);
	}
	
	@Override
	public List<Auditcard> findAuditcardByCondition(AuditcardCondition auditcardCondition, Page page) {
		return auditcardDao.findAuditcardByCondition(auditcardCondition, page, null);
	}
	
	@Override
	public List<Auditcard> findAuditcardByCondition(AuditcardCondition auditcardCondition, Order order) {
		return auditcardDao.findAuditcardByCondition(auditcardCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.auditcardDao.count(null);
	}

	@Override
	public Long count(AuditcardCondition auditcardCondition) {
		return this.auditcardDao.count(auditcardCondition);
	}

}
