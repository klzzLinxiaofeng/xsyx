package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.PjGroupDao;
import platform.education.generalTeachingAffair.model.PjGroup;
import platform.education.generalTeachingAffair.service.PjGroupService;
import platform.education.generalTeachingAffair.vo.PjGroupCondition;

import java.util.Date;
import java.util.List;

public class PjGroupServiceImpl implements PjGroupService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjGroupDao pjGroupDao;

	public void setPjGroupDao(PjGroupDao pjGroupDao) {
		this.pjGroupDao = pjGroupDao;
	}
	
	@Override
	public PjGroup findPjGroupById(Integer id) {
		try {
			return pjGroupDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjGroup add(PjGroup pjGroup) {
		if(pjGroup == null) {
    		return null;
    	}
    	Date createDate = pjGroup.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjGroup.setCreateDate(createDate);
    	pjGroup.setModifyDate(createDate);
		return pjGroupDao.create(pjGroup);
	}

	@Override
	public PjGroup modify(PjGroup pjGroup) {
		if(pjGroup == null) {
    		return null;
    	}
    	Date modify = pjGroup.getModifyDate();
    	pjGroup.setModifyDate(modify != null ? modify : new Date());
		return pjGroupDao.update(pjGroup);
	}
	
	@Override
	public void remove(PjGroup pjGroup) {
		try {
			pjGroupDao.delete(pjGroup);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjGroup.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjGroup> findPjGroupByCondition(PjGroupCondition pjGroupCondition, Page page, Order order) {
		return pjGroupDao.findPjGroupByCondition(pjGroupCondition, page, order);
	}
	
	@Override
	public List<PjGroup> findPjGroupByCondition(PjGroupCondition pjGroupCondition) {
		return pjGroupDao.findPjGroupByCondition(pjGroupCondition, null, null);
	}
	
	@Override
	public List<PjGroup> findPjGroupByCondition(PjGroupCondition pjGroupCondition, Page page) {
		return pjGroupDao.findPjGroupByCondition(pjGroupCondition, page, null);
	}
	
	@Override
	public List<PjGroup> findPjGroupByCondition(PjGroupCondition pjGroupCondition, Order order) {
		return pjGroupDao.findPjGroupByCondition(pjGroupCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjGroupDao.count(null);
	}

	@Override
	public Long count(PjGroupCondition pjGroupCondition) {
		return this.pjGroupDao.count(pjGroupCondition);
	}

}
