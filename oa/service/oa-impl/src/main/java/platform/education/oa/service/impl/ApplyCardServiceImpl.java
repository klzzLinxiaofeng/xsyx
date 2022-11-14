package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.ApplyCard;
import platform.education.oa.vo.ApplyCardCondition;
import platform.education.oa.vo.ApplyCardVo;
import platform.education.oa.service.ApplyCardService;
import platform.education.oa.dao.ApplyCardDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ApplyCardServiceImpl implements ApplyCardService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplyCardDao applyCardDao;

	public void setApplyCardDao(ApplyCardDao applyCardDao) {
		this.applyCardDao = applyCardDao;
	}
	
	@Override
	public ApplyCard findApplyCardById(Integer id) {
		try {
			return applyCardDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplyCard add(ApplyCard applyCard) {
		if(applyCard == null) {
    		return null;
    	}
    	Date createDate = applyCard.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	applyCard.setCreateDate(createDate);
    	applyCard.setModifyDate(createDate);
		return applyCardDao.create(applyCard);
	}

	@Override
	public ApplyCard modify(ApplyCard applyCard) {
		if(applyCard == null) {
    		return null;
    	}
    	Date modify = applyCard.getModifyDate();
    	applyCard.setModifyDate(modify != null ? modify : new Date());
		return applyCardDao.update(applyCard);
	}
	
	@Override
	public void remove(ApplyCard applyCard) {
		try {
			applyCardDao.delete(applyCard);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", applyCard.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApplyCard> findApplyCardByCondition(ApplyCardCondition applyCardCondition, Page page, Order order) {
		return applyCardDao.findApplyCardByCondition(applyCardCondition, page, order);
	}
	
	@Override
	public List<ApplyCard> findApplyCardByCondition(ApplyCardCondition applyCardCondition) {
		return applyCardDao.findApplyCardByCondition(applyCardCondition, null, null);
	}
	
	@Override
	public List<ApplyCard> findApplyCardByCondition(ApplyCardCondition applyCardCondition, Page page) {
		return applyCardDao.findApplyCardByCondition(applyCardCondition, page, null);
	}
	
	@Override
	public List<ApplyCard> findApplyCardByCondition(ApplyCardCondition applyCardCondition, Order order) {
		return applyCardDao.findApplyCardByCondition(applyCardCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.applyCardDao.count(null);
	}

	@Override
	public Long count(ApplyCardCondition applyCardCondition) {
		return this.applyCardDao.count(applyCardCondition);
	}

	@Override
	public List<ApplyCardVo> findApplyCardAllByCondition(
			ApplyCardCondition condition, Page page, Order order) {
		return applyCardDao.findApplyCardAllByCondition(condition, page, order);
	}

}
