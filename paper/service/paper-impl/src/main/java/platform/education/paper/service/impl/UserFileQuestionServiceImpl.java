package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.UserFileQuestion;
import platform.education.paper.vo.UserFileQuestionCondition;
import platform.education.paper.service.UserFileQuestionService;
import platform.education.paper.dao.UserFileQuestionDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class UserFileQuestionServiceImpl implements UserFileQuestionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private UserFileQuestionDao userFileQuestionDao;

	public void setUserFileQuestionDao(UserFileQuestionDao userFileQuestionDao) {
		this.userFileQuestionDao = userFileQuestionDao;
	}
	
	@Override
	public UserFileQuestion findUserFileQuestionById(Integer id) {
		try {
			return userFileQuestionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public UserFileQuestion add(UserFileQuestion userFileQuestion) {
		if(userFileQuestion == null) {
    		return null;
    	}
    	Date createDate = userFileQuestion.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	userFileQuestion.setCreateDate(createDate);
    	userFileQuestion.setModifyDate(createDate);
		return userFileQuestionDao.create(userFileQuestion);
	}

	@Override
	public UserFileQuestion modify(UserFileQuestion userFileQuestion) {
		if(userFileQuestion == null) {
    		return null;
    	}
    	Date modify = userFileQuestion.getModifyDate();
    	userFileQuestion.setModifyDate(modify != null ? modify : new Date());
		return userFileQuestionDao.update(userFileQuestion);
	}
	
	@Override
	public void remove(UserFileQuestion userFileQuestion) {
		try {
			userFileQuestionDao.delete(userFileQuestion);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", userFileQuestion.getId(), e);
			}
		}
	}
	
	@Override
	public List<UserFileQuestion> findUserFileQuestionByCondition(UserFileQuestionCondition userFileQuestionCondition, Page page, Order order) {
		return userFileQuestionDao.findUserFileQuestionByCondition(userFileQuestionCondition, page, order);
	}
	
	@Override
	public List<UserFileQuestion> findUserFileQuestionByCondition(UserFileQuestionCondition userFileQuestionCondition) {
		return userFileQuestionDao.findUserFileQuestionByCondition(userFileQuestionCondition, null, null);
	}
	
	@Override
	public List<UserFileQuestion> findUserFileQuestionByCondition(UserFileQuestionCondition userFileQuestionCondition, Page page) {
		return userFileQuestionDao.findUserFileQuestionByCondition(userFileQuestionCondition, page, null);
	}
	
	@Override
	public List<UserFileQuestion> findUserFileQuestionByCondition(UserFileQuestionCondition userFileQuestionCondition, Order order) {
		return userFileQuestionDao.findUserFileQuestionByCondition(userFileQuestionCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.userFileQuestionDao.count(null);
	}

	@Override
	public Long count(UserFileQuestionCondition userFileQuestionCondition) {
		return this.userFileQuestionDao.count(userFileQuestionCondition);
	}

	@Override
	public void batchUserFileQuestionAnswer(List<UserFileQuestion> userFileQuestionLis) {
		
		userFileQuestionDao.batchUserFileQuestionAnswer(userFileQuestionLis);
		
	}

}
