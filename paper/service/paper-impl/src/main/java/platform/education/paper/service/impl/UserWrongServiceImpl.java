package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.dao.UserWrongDao;
import platform.education.paper.model.UserWrong;
import platform.education.paper.model.WrongPaper;
import platform.education.paper.service.UserWrongService;
import platform.education.paper.vo.UserWrongCondition;

public class UserWrongServiceImpl implements UserWrongService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private UserWrongDao userWrongDao;

	public void setUserWrongDao(UserWrongDao userWrongDao) {
		this.userWrongDao = userWrongDao;
	}
	
	@Override
	public UserWrong findUserWrongById(Integer id) {
		try {
			return userWrongDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public UserWrong add(UserWrong userWrong) {
		if(userWrong == null) {
    		return null;
    	}
    	Date createDate = userWrong.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	userWrong.setCreateDate(createDate);
    	userWrong.setModifyDate(createDate);
		return userWrongDao.create(userWrong);
	}

	@Override
	public UserWrong modify(UserWrong userWrong) {
		if(userWrong == null) {
    		return null;
    	}
    	Date modify = userWrong.getModifyDate();
    	userWrong.setModifyDate(modify != null ? modify : new Date());
		return userWrongDao.update(userWrong);
	}
	
	@Override
	public void remove(UserWrong userWrong) {
		try {
			userWrongDao.delete(userWrong);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", userWrong.getId(), e);
			}
		}
	}
	
	@Override
	public List<UserWrong> findUserWrongByCondition(UserWrongCondition userWrongCondition, Page page, Order order) {
		return userWrongDao.findUserWrongByCondition(userWrongCondition, page, order);
	}
	
	@Override
	public List<UserWrong> findUserWrongByCondition(UserWrongCondition userWrongCondition) {
		return userWrongDao.findUserWrongByCondition(userWrongCondition, null, null);
	}
	
	@Override
	public List<UserWrong> findUserWrongByCondition(UserWrongCondition userWrongCondition, Page page) {
		return userWrongDao.findUserWrongByCondition(userWrongCondition, page, null);
	}
	
	@Override
	public List<UserWrong> findUserWrongByCondition(UserWrongCondition userWrongCondition, Order order) {
		return userWrongDao.findUserWrongByCondition(userWrongCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.userWrongDao.count(null);
	}

	@Override
	public Long count(UserWrongCondition userWrongCondition) {
		return this.userWrongDao.count(userWrongCondition);
	}

	/* (非 Javadoc) 
	* <p>Title: findUserWrongByUserIdAndQuestionUuId</p> 
	* <p>Description: </p> 
	* @param userId
	* @param QuestionUuId
	* @return 
	* @see platform.education.paper.service.UserWrongService#findUserWrongByUserIdAndQuestionUuId(java.lang.Integer, java.lang.String) 
	*/
	@Override
	public UserWrong findUserWrongByUserIdAndQuestionUuId(Integer userId, String questionUuId) {
		return this.userWrongDao.findUserWrongByUserIdAndQuestionUuId(userId,questionUuId);
	}

	/* (非 Javadoc) 
	* <p>Title: findUserWrongList</p> 
	* <p>Description: </p> 
	* @param userId
	* @param subjectCode
	* @param page
	* @param order
	* @return 
	* @see platform.education.paper.service.UserWrongService#findUserWrongList(java.lang.Integer, java.lang.String, framework.generic.dao.Page, framework.generic.dao.Order) 
	*/
	@Override
	public List<WrongPaper> findUserWrongListByUserId(Integer userId, String subjectCode, Page page, Order order) {
		List<WrongPaper> list = null;
		String createDateStr = userWrongDao.findUserWrongCreateDateByUserId(userId);
		if(createDateStr != null){
			 list = userWrongDao.findUserWrongListByUserId(userId, subjectCode,createDateStr, page, order);
		}
		return list;
	}

	/* (非 Javadoc) 
	* <p>Title: findUserWrongCreateGroup</p> 
	* <p>Description: </p> 
	* @return 
	* @see platform.education.paper.service.UserWrongService#findUserWrongCreateGroup() 
	*/
	@Override
	public List<String> findUserWrongCreateGroup() {
		return userWrongDao.findUserWrongCreateGroup();
	}

	@Override
	public void deleteNotInPaperUuid(String[] uuids) {
		userWrongDao.deleteNotInPaperUuid(uuids);
	}

}
