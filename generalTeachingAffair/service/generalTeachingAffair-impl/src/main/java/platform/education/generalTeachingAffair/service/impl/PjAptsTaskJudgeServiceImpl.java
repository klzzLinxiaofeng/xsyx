package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.PjAptsTaskJudge;
import platform.education.generalTeachingAffair.vo.PjAptsTaskJudgeCondition;
import platform.education.generalTeachingAffair.service.PjAptsTaskJudgeService;
import platform.education.generalTeachingAffair.dao.PjAptsTaskJudgeDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjAptsTaskJudgeServiceImpl implements PjAptsTaskJudgeService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjAptsTaskJudgeDao pjAptsTaskJudgeDao;

	public void setPjAptsTaskJudgeDao(PjAptsTaskJudgeDao pjAptsTaskJudgeDao) {
		this.pjAptsTaskJudgeDao = pjAptsTaskJudgeDao;
	}
	
	@Override
	public PjAptsTaskJudge findPjAptsTaskJudgeById(Integer id) {
		try {
			return pjAptsTaskJudgeDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjAptsTaskJudge add(PjAptsTaskJudge pjAptsTaskJudge) {
		if(pjAptsTaskJudge == null) {
    		return null;
    	}
    	Date createDate = pjAptsTaskJudge.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjAptsTaskJudge.setCreateDate(createDate);
    	pjAptsTaskJudge.setModifyDate(createDate);
		return pjAptsTaskJudgeDao.create(pjAptsTaskJudge);
	}

	@Override
	public PjAptsTaskJudge modify(PjAptsTaskJudge pjAptsTaskJudge) {
		if(pjAptsTaskJudge == null) {
    		return null;
    	}
    	Date modify = pjAptsTaskJudge.getModifyDate();
    	pjAptsTaskJudge.setModifyDate(modify != null ? modify : new Date());
		return pjAptsTaskJudgeDao.update(pjAptsTaskJudge);
	}
	
	@Override
	public void remove(PjAptsTaskJudge pjAptsTaskJudge) {
		try {
			pjAptsTaskJudgeDao.delete(pjAptsTaskJudge);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjAptsTaskJudge.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjAptsTaskJudge> findPjAptsTaskJudgeByCondition(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition, Page page, Order order) {
		return pjAptsTaskJudgeDao.findPjAptsTaskJudgeByCondition(pjAptsTaskJudgeCondition, page, order);
	}
	
	@Override
	public List<PjAptsTaskJudge> findPjAptsTaskJudgeByCondition(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition) {
		return pjAptsTaskJudgeDao.findPjAptsTaskJudgeByCondition(pjAptsTaskJudgeCondition, null, null);
	}
	
	@Override
	public List<PjAptsTaskJudge> findPjAptsTaskJudgeByCondition(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition, Page page) {
		return pjAptsTaskJudgeDao.findPjAptsTaskJudgeByCondition(pjAptsTaskJudgeCondition, page, null);
	}
	
	@Override
	public List<PjAptsTaskJudge> findPjAptsTaskJudgeByCondition(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition, Order order) {
		return pjAptsTaskJudgeDao.findPjAptsTaskJudgeByCondition(pjAptsTaskJudgeCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjAptsTaskJudgeDao.count(null);
	}

	@Override
	public Long count(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition) {
		return this.pjAptsTaskJudgeDao.count(pjAptsTaskJudgeCondition);
	}

}
