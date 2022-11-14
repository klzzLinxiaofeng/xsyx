package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.PjAptsTaskItem;
import platform.education.generalTeachingAffair.vo.PjAptsTaskItemCondition;
import platform.education.generalTeachingAffair.service.PjAptsTaskItemService;
import platform.education.generalTeachingAffair.dao.PjAptsTaskItemDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjAptsTaskItemServiceImpl implements PjAptsTaskItemService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjAptsTaskItemDao pjAptsTaskItemDao;

	public void setPjAptsTaskItemDao(PjAptsTaskItemDao pjAptsTaskItemDao) {
		this.pjAptsTaskItemDao = pjAptsTaskItemDao;
	}
	
	@Override
	public PjAptsTaskItem findPjAptsTaskItemById(Integer id) {
		try {
			return pjAptsTaskItemDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjAptsTaskItem add(PjAptsTaskItem pjAptsTaskItem) {
		if(pjAptsTaskItem == null) {
    		return null;
    	}
    	Date createDate = pjAptsTaskItem.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjAptsTaskItem.setCreateDate(createDate);
    	pjAptsTaskItem.setModifyDate(createDate);
		return pjAptsTaskItemDao.create(pjAptsTaskItem);
	}

	@Override
	public PjAptsTaskItem modify(PjAptsTaskItem pjAptsTaskItem) {
		if(pjAptsTaskItem == null) {
    		return null;
    	}
    	Date modify = pjAptsTaskItem.getModifyDate();
    	pjAptsTaskItem.setModifyDate(modify != null ? modify : new Date());
		return pjAptsTaskItemDao.update(pjAptsTaskItem);
	}
	
	@Override
	public void remove(PjAptsTaskItem pjAptsTaskItem) {
		try {
			pjAptsTaskItemDao.delete(pjAptsTaskItem);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjAptsTaskItem.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjAptsTaskItem> findPjAptsTaskItemByCondition(PjAptsTaskItemCondition pjAptsTaskItemCondition, Page page, Order order) {
		return pjAptsTaskItemDao.findPjAptsTaskItemByCondition(pjAptsTaskItemCondition, page, order);
	}
	
	@Override
	public List<PjAptsTaskItem> findPjAptsTaskItemByCondition(PjAptsTaskItemCondition pjAptsTaskItemCondition) {
		return pjAptsTaskItemDao.findPjAptsTaskItemByCondition(pjAptsTaskItemCondition, null, null);
	}
	
	@Override
	public List<PjAptsTaskItem> findPjAptsTaskItemByCondition(PjAptsTaskItemCondition pjAptsTaskItemCondition, Page page) {
		return pjAptsTaskItemDao.findPjAptsTaskItemByCondition(pjAptsTaskItemCondition, page, null);
	}
	
	@Override
	public List<PjAptsTaskItem> findPjAptsTaskItemByCondition(PjAptsTaskItemCondition pjAptsTaskItemCondition, Order order) {
		return pjAptsTaskItemDao.findPjAptsTaskItemByCondition(pjAptsTaskItemCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjAptsTaskItemDao.count(null);
	}

	@Override
	public Long count(PjAptsTaskItemCondition pjAptsTaskItemCondition) {
		return this.pjAptsTaskItemDao.count(pjAptsTaskItemCondition);
	}

	@Override
	public void createBatch(PjAptsTaskItem[] pati) {
		
		pjAptsTaskItemDao.createBatch(pati);
		
	}

	@Override
	public void modifyIsDeleteByTaskId(Integer taskId) {
	
		pjAptsTaskItemDao.updateIsDeleteByTaskId(taskId);
		
	}

}
