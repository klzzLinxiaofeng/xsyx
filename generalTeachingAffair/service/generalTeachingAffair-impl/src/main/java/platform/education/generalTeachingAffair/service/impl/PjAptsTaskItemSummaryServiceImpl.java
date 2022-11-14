package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.PjAptsTaskItemSummary;
import platform.education.generalTeachingAffair.vo.PjAptsTaskItemSummaryCondition;
import platform.education.generalTeachingAffair.service.PjAptsTaskItemSummaryService;
import platform.education.generalTeachingAffair.dao.PjAptsTaskItemSummaryDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjAptsTaskItemSummaryServiceImpl implements PjAptsTaskItemSummaryService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjAptsTaskItemSummaryDao pjAptsTaskItemSummaryDao;

	public void setPjAptsTaskItemSummaryDao(PjAptsTaskItemSummaryDao pjAptsTaskItemSummaryDao) {
		this.pjAptsTaskItemSummaryDao = pjAptsTaskItemSummaryDao;
	}
	
	@Override
	public PjAptsTaskItemSummary findPjAptsTaskItemSummaryById(Integer id) {
		try {
			return pjAptsTaskItemSummaryDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjAptsTaskItemSummary add(PjAptsTaskItemSummary pjAptsTaskItemSummary) {
		if(pjAptsTaskItemSummary == null) {
    		return null;
    	}
    	Date createDate = pjAptsTaskItemSummary.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjAptsTaskItemSummary.setCreateDate(createDate);
    	pjAptsTaskItemSummary.setModifyDate(createDate);
		return pjAptsTaskItemSummaryDao.create(pjAptsTaskItemSummary);
	}

	@Override
	public PjAptsTaskItemSummary modify(PjAptsTaskItemSummary pjAptsTaskItemSummary) {
		if(pjAptsTaskItemSummary == null) {
    		return null;
    	}
    	Date modify = pjAptsTaskItemSummary.getModifyDate();
    	pjAptsTaskItemSummary.setModifyDate(modify != null ? modify : new Date());
		return pjAptsTaskItemSummaryDao.update(pjAptsTaskItemSummary);
	}
	
	@Override
	public void remove(PjAptsTaskItemSummary pjAptsTaskItemSummary) {
		try {
			pjAptsTaskItemSummaryDao.delete(pjAptsTaskItemSummary);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjAptsTaskItemSummary.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjAptsTaskItemSummary> findPjAptsTaskItemSummaryByCondition(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition, Page page, Order order) {
		return pjAptsTaskItemSummaryDao.findPjAptsTaskItemSummaryByCondition(pjAptsTaskItemSummaryCondition, page, order);
	}
	
	@Override
	public List<PjAptsTaskItemSummary> findPjAptsTaskItemSummaryByCondition(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition) {
		return pjAptsTaskItemSummaryDao.findPjAptsTaskItemSummaryByCondition(pjAptsTaskItemSummaryCondition, null, null);
	}
	
	@Override
	public List<PjAptsTaskItemSummary> findPjAptsTaskItemSummaryByCondition(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition, Page page) {
		return pjAptsTaskItemSummaryDao.findPjAptsTaskItemSummaryByCondition(pjAptsTaskItemSummaryCondition, page, null);
	}
	
	@Override
	public List<PjAptsTaskItemSummary> findPjAptsTaskItemSummaryByCondition(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition, Order order) {
		return pjAptsTaskItemSummaryDao.findPjAptsTaskItemSummaryByCondition(pjAptsTaskItemSummaryCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjAptsTaskItemSummaryDao.count(null);
	}

	@Override
	public Long count(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition) {
		return this.pjAptsTaskItemSummaryDao.count(pjAptsTaskItemSummaryCondition);
	}

	@Override
	public void createBatch(PjAptsTaskItemSummary[] pjAptsTaskItemSummaryList) {
		
		pjAptsTaskItemSummaryDao.createBatch(pjAptsTaskItemSummaryList);
		
	}

}
