package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.ApsMoralEvaluationItem;
import platform.education.generalTeachingAffair.vo.ApsMoralEvaluationItemCondition;
import platform.education.generalTeachingAffair.service.ApsMoralEvaluationItemService;
import platform.education.generalTeachingAffair.dao.ApsMoralEvaluationItemDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ApsMoralEvaluationItemServiceImpl implements ApsMoralEvaluationItemService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApsMoralEvaluationItemDao apsMoralEvaluationItemDao;

	public void setApsMoralEvaluationItemDao(ApsMoralEvaluationItemDao apsMoralEvaluationItemDao) {
		this.apsMoralEvaluationItemDao = apsMoralEvaluationItemDao;
	}
	
	@Override
	public ApsMoralEvaluationItem findApsMoralEvaluationItemById(Integer id) {
		try {
			return apsMoralEvaluationItemDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApsMoralEvaluationItem add(ApsMoralEvaluationItem apsMoralEvaluationItem) {
		if(apsMoralEvaluationItem == null) {
    		return null;
    	}
    	Date createDate = apsMoralEvaluationItem.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	apsMoralEvaluationItem.setCreateDate(createDate);
    	apsMoralEvaluationItem.setModifyDate(createDate);
		return apsMoralEvaluationItemDao.create(apsMoralEvaluationItem);
	}

	@Override
	public ApsMoralEvaluationItem modify(ApsMoralEvaluationItem apsMoralEvaluationItem) {
		if(apsMoralEvaluationItem == null) {
    		return null;
    	}
    	Date modify = apsMoralEvaluationItem.getModifyDate();
    	apsMoralEvaluationItem.setModifyDate(modify != null ? modify : new Date());
		return apsMoralEvaluationItemDao.update(apsMoralEvaluationItem);
	}
	
	@Override
	public void remove(ApsMoralEvaluationItem apsMoralEvaluationItem) {
		try {
			apsMoralEvaluationItemDao.delete(apsMoralEvaluationItem);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", apsMoralEvaluationItem.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApsMoralEvaluationItem> findApsMoralEvaluationItemByCondition(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition, Page page, Order order) {
		return apsMoralEvaluationItemDao.findApsMoralEvaluationItemByCondition(apsMoralEvaluationItemCondition, page, order);
	}
	
	@Override
	public List<ApsMoralEvaluationItem> findApsMoralEvaluationItemByCondition(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition) {
		return apsMoralEvaluationItemDao.findApsMoralEvaluationItemByCondition(apsMoralEvaluationItemCondition, null, null);
	}
	
	@Override
	public List<ApsMoralEvaluationItem> findApsMoralEvaluationItemByCondition(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition, Page page) {
		return apsMoralEvaluationItemDao.findApsMoralEvaluationItemByCondition(apsMoralEvaluationItemCondition, page, null);
	}
	
	@Override
	public List<ApsMoralEvaluationItem> findApsMoralEvaluationItemByCondition(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition, Order order) {
		return apsMoralEvaluationItemDao.findApsMoralEvaluationItemByCondition(apsMoralEvaluationItemCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.apsMoralEvaluationItemDao.count(null);
	}

	@Override
	public Long count(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition) {
		return this.apsMoralEvaluationItemDao.count(apsMoralEvaluationItemCondition);
	}

}
