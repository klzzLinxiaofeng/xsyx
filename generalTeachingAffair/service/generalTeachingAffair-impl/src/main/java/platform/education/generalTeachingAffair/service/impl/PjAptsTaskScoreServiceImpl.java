package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.PjAptsTaskScoreDao;
import platform.education.generalTeachingAffair.model.PjAptsTaskScore;
import platform.education.generalTeachingAffair.service.PjAptsTaskScoreService;
import platform.education.generalTeachingAffair.vo.AssessmentScoreVo;
import platform.education.generalTeachingAffair.vo.PjAptsTaskScoreCondition;

import java.util.Date;
import java.util.List;

public class PjAptsTaskScoreServiceImpl implements PjAptsTaskScoreService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjAptsTaskScoreDao pjAptsTaskScoreDao;

	public void setPjAptsTaskScoreDao(PjAptsTaskScoreDao pjAptsTaskScoreDao) {
		this.pjAptsTaskScoreDao = pjAptsTaskScoreDao;
	}
	
	@Override
	public PjAptsTaskScore findPjAptsTaskScoreById(Integer id) {
		try {
			return pjAptsTaskScoreDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjAptsTaskScore add(PjAptsTaskScore pjAptsTaskScore) {
		if(pjAptsTaskScore == null) {
    		return null;
    	}
    	Date createDate = pjAptsTaskScore.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjAptsTaskScore.setCreateDate(createDate);
    	pjAptsTaskScore.setModifyDate(createDate);
		return pjAptsTaskScoreDao.create(pjAptsTaskScore);
	}

	@Override
	public PjAptsTaskScore modify(PjAptsTaskScore pjAptsTaskScore) {
		if(pjAptsTaskScore == null) {
    		return null;
    	}
    	Date modify = pjAptsTaskScore.getModifyDate();
    	pjAptsTaskScore.setModifyDate(modify != null ? modify : new Date());
		return pjAptsTaskScoreDao.update(pjAptsTaskScore);
	}
	
	@Override
	public void remove(PjAptsTaskScore pjAptsTaskScore) {
		try {
			pjAptsTaskScoreDao.delete(pjAptsTaskScore);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjAptsTaskScore.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjAptsTaskScore> findPjAptsTaskScoreByCondition(PjAptsTaskScoreCondition pjAptsTaskScoreCondition, Page page, Order order) {
		return pjAptsTaskScoreDao.findPjAptsTaskScoreByCondition(pjAptsTaskScoreCondition, page, order);
	}
	
	@Override
	public List<PjAptsTaskScore> findPjAptsTaskScoreByCondition(PjAptsTaskScoreCondition pjAptsTaskScoreCondition) {
		return pjAptsTaskScoreDao.findPjAptsTaskScoreByCondition(pjAptsTaskScoreCondition, null, null);
	}
	
	@Override
	public List<PjAptsTaskScore> findPjAptsTaskScoreByCondition(PjAptsTaskScoreCondition pjAptsTaskScoreCondition, Page page) {
		return pjAptsTaskScoreDao.findPjAptsTaskScoreByCondition(pjAptsTaskScoreCondition, page, null);
	}
	
	@Override
	public List<PjAptsTaskScore> findPjAptsTaskScoreByCondition(PjAptsTaskScoreCondition pjAptsTaskScoreCondition, Order order) {
		return pjAptsTaskScoreDao.findPjAptsTaskScoreByCondition(pjAptsTaskScoreCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjAptsTaskScoreDao.count(null);
	}

	@Override
	public Long count(PjAptsTaskScoreCondition pjAptsTaskScoreCondition) {
		return this.pjAptsTaskScoreDao.count(pjAptsTaskScoreCondition);
	}

	@Override
	public void createBatch(PjAptsTaskScore[] pats) {
		pjAptsTaskScoreDao.createBatch(pats);
	}

	@Override
	public List<AssessmentScoreVo>findAssessmentCount(Integer taskUserId,Integer studentId) {
		
		return pjAptsTaskScoreDao.findAssessmentCount(taskUserId,studentId);
	}

	@Override
	public List<Float> findAvgScore(Integer id) {
		return pjAptsTaskScoreDao.findAvgScore(id);
	}
}
