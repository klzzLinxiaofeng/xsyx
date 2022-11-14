package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaUserQuestion;
import platform.education.paper.vo.PaUserQuestionCondition;
import platform.education.paper.service.PaUserQuestionService;
import platform.education.paper.dao.PaUserQuestionDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaUserQuestionServiceImpl implements PaUserQuestionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaUserQuestionDao paUserQuestionDao;

	public void setPaUserQuestionDao(PaUserQuestionDao paUserQuestionDao) {
		this.paUserQuestionDao = paUserQuestionDao;
	}
	
	@Override
	public PaUserQuestion findPaUserQuestionById(Integer id) {
		try {
			return paUserQuestionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaUserQuestion add(PaUserQuestion paUserQuestion) {
		if(paUserQuestion == null) {
    		return null;
    	}
    	Date createDate = paUserQuestion.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paUserQuestion.setCreateDate(createDate);
    	paUserQuestion.setModifyDate(createDate);
		return paUserQuestionDao.create(paUserQuestion);
	}

	@Override
	public PaUserQuestion modify(PaUserQuestion paUserQuestion) {
		if(paUserQuestion == null) {
    		return null;
    	}
    	Date modify = paUserQuestion.getModifyDate();
    	paUserQuestion.setModifyDate(modify != null ? modify : new Date());
		return paUserQuestionDao.update(paUserQuestion);
	}
	
	@Override
	public void remove(PaUserQuestion paUserQuestion) {
		try {
			paUserQuestionDao.delete(paUserQuestion);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paUserQuestion.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaUserQuestion> findPaUserQuestionByCondition(PaUserQuestionCondition paUserQuestionCondition, Page page, Order order) {
		return paUserQuestionDao.findPaUserQuestionByCondition(paUserQuestionCondition, page, order);
	}
	
	@Override
	public List<PaUserQuestion> findPaUserQuestionByCondition(PaUserQuestionCondition paUserQuestionCondition) {
		return paUserQuestionDao.findPaUserQuestionByCondition(paUserQuestionCondition, null, null);
	}
	
	@Override
	public List<PaUserQuestion> findPaUserQuestionByCondition(PaUserQuestionCondition paUserQuestionCondition, Page page) {
		return paUserQuestionDao.findPaUserQuestionByCondition(paUserQuestionCondition, page, null);
	}
	
	@Override
	public List<PaUserQuestion> findPaUserQuestionByCondition(PaUserQuestionCondition paUserQuestionCondition, Order order) {
		return paUserQuestionDao.findPaUserQuestionByCondition(paUserQuestionCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paUserQuestionDao.count(null);
	}

	@Override
	public Long count(PaUserQuestionCondition paUserQuestionCondition) {
		return this.paUserQuestionDao.count(paUserQuestionCondition);
	}

}
