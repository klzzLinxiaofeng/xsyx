package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaUserFileQuestion;
import platform.education.paper.vo.PaUserFileQuestionCondition;
import platform.education.paper.service.PaUserFileQuestionService;
import platform.education.paper.dao.PaUserFileQuestionDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaUserFileQuestionServiceImpl implements PaUserFileQuestionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaUserFileQuestionDao paUserFileQuestionDao;

	public void setPaUserFileQuestionDao(PaUserFileQuestionDao paUserFileQuestionDao) {
		this.paUserFileQuestionDao = paUserFileQuestionDao;
	}
	
	@Override
	public PaUserFileQuestion findPaUserFileQuestionById(Integer id) {
		try {
			return paUserFileQuestionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaUserFileQuestion add(PaUserFileQuestion paUserFileQuestion) {
		if(paUserFileQuestion == null) {
    		return null;
    	}
    	Date createDate = paUserFileQuestion.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paUserFileQuestion.setCreateDate(createDate);
    	paUserFileQuestion.setModifyDate(createDate);
		return paUserFileQuestionDao.create(paUserFileQuestion);
	}

	@Override
	public PaUserFileQuestion modify(PaUserFileQuestion paUserFileQuestion) {
		if(paUserFileQuestion == null) {
    		return null;
    	}
    	Date modify = paUserFileQuestion.getModifyDate();
    	paUserFileQuestion.setModifyDate(modify != null ? modify : new Date());
		return paUserFileQuestionDao.update(paUserFileQuestion);
	}
	
	@Override
	public void remove(PaUserFileQuestion paUserFileQuestion) {
		try {
			paUserFileQuestionDao.delete(paUserFileQuestion);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paUserFileQuestion.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaUserFileQuestion> findPaUserFileQuestionByCondition(PaUserFileQuestionCondition paUserFileQuestionCondition, Page page, Order order) {
		return paUserFileQuestionDao.findPaUserFileQuestionByCondition(paUserFileQuestionCondition, page, order);
	}
	
	@Override
	public List<PaUserFileQuestion> findPaUserFileQuestionByCondition(PaUserFileQuestionCondition paUserFileQuestionCondition) {
		return paUserFileQuestionDao.findPaUserFileQuestionByCondition(paUserFileQuestionCondition, null, null);
	}
	
	@Override
	public List<PaUserFileQuestion> findPaUserFileQuestionByCondition(PaUserFileQuestionCondition paUserFileQuestionCondition, Page page) {
		return paUserFileQuestionDao.findPaUserFileQuestionByCondition(paUserFileQuestionCondition, page, null);
	}
	
	@Override
	public List<PaUserFileQuestion> findPaUserFileQuestionByCondition(PaUserFileQuestionCondition paUserFileQuestionCondition, Order order) {
		return paUserFileQuestionDao.findPaUserFileQuestionByCondition(paUserFileQuestionCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paUserFileQuestionDao.count(null);
	}

	@Override
	public Long count(PaUserFileQuestionCondition paUserFileQuestionCondition) {
		return this.paUserFileQuestionDao.count(paUserFileQuestionCondition);
	}

}
