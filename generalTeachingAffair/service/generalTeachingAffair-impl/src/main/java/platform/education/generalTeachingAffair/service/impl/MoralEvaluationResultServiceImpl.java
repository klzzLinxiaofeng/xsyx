package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.MoralEvaluationResult;
import platform.education.generalTeachingAffair.vo.MoralEvaluationResultCondition;
import platform.education.generalTeachingAffair.service.MoralEvaluationResultService;
import platform.education.generalTeachingAffair.dao.MoralEvaluationResultDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class MoralEvaluationResultServiceImpl implements MoralEvaluationResultService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private MoralEvaluationResultDao moralEvaluationResultDao;

	public void setMoralEvaluationResultDao(MoralEvaluationResultDao moralEvaluationResultDao) {
		this.moralEvaluationResultDao = moralEvaluationResultDao;
	}
	
	@Override
	public MoralEvaluationResult findMoralEvaluationResultById(Integer id) {
		try {
			return moralEvaluationResultDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public MoralEvaluationResult add(MoralEvaluationResult moralEvaluationResult) {
		if(moralEvaluationResult == null) {
    		return null;
    	}
    	Date createDate = moralEvaluationResult.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	moralEvaluationResult.setCreateDate(createDate);
    	moralEvaluationResult.setModifyDate(createDate);
		return moralEvaluationResultDao.create(moralEvaluationResult);
	}

	@Override
	public MoralEvaluationResult modify(MoralEvaluationResult moralEvaluationResult) {
		if(moralEvaluationResult == null) {
    		return null;
    	}
    	Date modify = moralEvaluationResult.getModifyDate();
    	moralEvaluationResult.setModifyDate(modify != null ? modify : new Date());
		return moralEvaluationResultDao.update(moralEvaluationResult);
	}
	
	@Override
	public void remove(MoralEvaluationResult moralEvaluationResult) {
		 moralEvaluationResultDao.delete(moralEvaluationResult);
	}
	
	@Override
	public List<MoralEvaluationResult> findMoralEvaluationResultByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition, Page page, Order order) {
		return moralEvaluationResultDao.findMoralEvaluationResultByCondition(moralEvaluationResultCondition, page, order);
	}
	
	@Override
	public List<MoralEvaluationResult> findMoralEvaluationResultByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition) {
		return moralEvaluationResultDao.findMoralEvaluationResultByCondition(moralEvaluationResultCondition, null, null);
	}
	
	@Override
	public List<MoralEvaluationResult> findMoralEvaluationResultByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition, Page page) {
		return moralEvaluationResultDao.findMoralEvaluationResultByCondition(moralEvaluationResultCondition, page, null);
	}
	
	@Override
	public List<MoralEvaluationResult> findMoralEvaluationResultByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition, Order order) {
		return moralEvaluationResultDao.findMoralEvaluationResultByCondition(moralEvaluationResultCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.moralEvaluationResultDao.count(null);
	}

	@Override
	public Long count(MoralEvaluationResultCondition moralEvaluationResultCondition) {
		return this.moralEvaluationResultDao.count(moralEvaluationResultCondition);
	}

	@Override
	public void remove(MoralEvaluationResultCondition moralEvaluationResultCondition) {
		this.moralEvaluationResultDao.deleteByCondition(moralEvaluationResultCondition);
	}

}
