package platform.education.paper.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaQuestionKnoledge;
import platform.education.paper.vo.PaQuestionKnoledgeCondition;
import platform.education.paper.service.PaQuestionKnoledgeService;
import platform.education.paper.dao.PaQuestionKnoledgeDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaQuestionKnoledgeServiceImpl implements PaQuestionKnoledgeService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaQuestionKnoledgeDao paQuestionKnoledgeDao;

	public void setPaQuestionKnoledgeDao(PaQuestionKnoledgeDao paQuestionKnoledgeDao) {
		this.paQuestionKnoledgeDao = paQuestionKnoledgeDao;
	}
	
	@Override
	public PaQuestionKnoledge findPaQuestionKnoledgeById(Integer id) {
		try {
			return paQuestionKnoledgeDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaQuestionKnoledge add(PaQuestionKnoledge paQuestionKnoledge) {
		if(paQuestionKnoledge == null) {
    		return null;
    	}
		return paQuestionKnoledgeDao.create(paQuestionKnoledge);
	}

	@Override
	public PaQuestionKnoledge modify(PaQuestionKnoledge paQuestionKnoledge) {
		if(paQuestionKnoledge == null) {
    		return null;
    	}
		return paQuestionKnoledgeDao.update(paQuestionKnoledge);
	}
	
	@Override
	public void remove(PaQuestionKnoledge paQuestionKnoledge) {
		try {
			paQuestionKnoledgeDao.delete(paQuestionKnoledge);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paQuestionKnoledge.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaQuestionKnoledge> findPaQuestionKnoledgeByCondition(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition, Page page, Order order) {
		return paQuestionKnoledgeDao.findPaQuestionKnoledgeByCondition(paQuestionKnoledgeCondition, page, order);
	}
	
	@Override
	public List<PaQuestionKnoledge> findPaQuestionKnoledgeByCondition(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition) {
		return paQuestionKnoledgeDao.findPaQuestionKnoledgeByCondition(paQuestionKnoledgeCondition, null, null);
	}
	
	@Override
	public List<PaQuestionKnoledge> findPaQuestionKnoledgeByCondition(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition, Page page) {
		return paQuestionKnoledgeDao.findPaQuestionKnoledgeByCondition(paQuestionKnoledgeCondition, page, null);
	}
	
	@Override
	public List<PaQuestionKnoledge> findPaQuestionKnoledgeByCondition(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition, Order order) {
		return paQuestionKnoledgeDao.findPaQuestionKnoledgeByCondition(paQuestionKnoledgeCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paQuestionKnoledgeDao.count(null);
	}

	@Override
	public Long count(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition) {
		return this.paQuestionKnoledgeDao.count(paQuestionKnoledgeCondition);
	}

	@Override
	public void removeByQuestionIds(Integer[] questionIds) {
		if(questionIds.length>0) {
			paQuestionKnoledgeDao.deleteByQuestionIds(questionIds);
		}
	}

	@Override
	public List<PaQuestionKnoledge> findByQuestionId(Integer questionId) {
		PaQuestionKnoledgeCondition paQuestionKnoledgeCondition = new PaQuestionKnoledgeCondition();
		paQuestionKnoledgeCondition.setQuestionId(questionId);
		List<PaQuestionKnoledge> paQuestionKnoledgeList =  this.findPaQuestionKnoledgeByCondition(paQuestionKnoledgeCondition);
		return paQuestionKnoledgeList;
	}

	@Override
	public List<PaQuestionKnoledge> findByQuestionIds(Integer[] questions) {
		return paQuestionKnoledgeDao.findByQuestionIds(questions);
	}

}
