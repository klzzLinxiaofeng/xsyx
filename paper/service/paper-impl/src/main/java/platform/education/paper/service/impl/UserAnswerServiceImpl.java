package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.dao.UserAnswerDao;
import platform.education.paper.model.UserAnswer;
import platform.education.paper.service.UserAnswerService;
import platform.education.paper.vo.UserAnswerCondition;

public class UserAnswerServiceImpl implements UserAnswerService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private UserAnswerDao userAnswerDao;

	public void setUserAnswerDao(UserAnswerDao userAnswerDao) {
		this.userAnswerDao = userAnswerDao;
	}
	
	@Override
	public UserAnswer findUserAnswerById(Integer id) {
		try {
			return userAnswerDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public UserAnswer add(UserAnswer userAnswer) {
		if(userAnswer == null) {
    		return null;
    	}
    	Date createDate = userAnswer.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	userAnswer.setCreateDate(createDate);
    	userAnswer.setModifyDate(createDate);
		return userAnswerDao.create(userAnswer);
	}

	@Override
	public UserAnswer modify(UserAnswer userAnswer) {
		if(userAnswer == null) {
    		return null;
    	}
    	Date modify = userAnswer.getModifyDate();
    	userAnswer.setModifyDate(modify != null ? modify : new Date());
		return userAnswerDao.update(userAnswer);
	}
	
	@Override
	public void remove(UserAnswer userAnswer) {
		try {
			userAnswerDao.delete(userAnswer);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", userAnswer.getId(), e);
			}
		}
	}
	
	@Override
	public List<UserAnswer> findUserAnswerByCondition(UserAnswerCondition userAnswerCondition, Page page, Order order) {
		return userAnswerDao.findUserAnswerByCondition(userAnswerCondition, page, order);
	}
	
	@Override
	public List<UserAnswer> findUserAnswerByCondition(UserAnswerCondition userAnswerCondition) {
		return userAnswerDao.findUserAnswerByCondition(userAnswerCondition, null, null);
	}
	
	@Override
	public List<UserAnswer> findUserAnswerByCondition(UserAnswerCondition userAnswerCondition, Page page) {
		return userAnswerDao.findUserAnswerByCondition(userAnswerCondition, page, null);
	}
	
	@Override
	public List<UserAnswer> findUserAnswerByCondition(UserAnswerCondition userAnswerCondition, Order order) {
		return userAnswerDao.findUserAnswerByCondition(userAnswerCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.userAnswerDao.count(null);
	}

	@Override
	public Long count(UserAnswerCondition userAnswerCondition) {
		return this.userAnswerDao.count(userAnswerCondition);
	}

}
