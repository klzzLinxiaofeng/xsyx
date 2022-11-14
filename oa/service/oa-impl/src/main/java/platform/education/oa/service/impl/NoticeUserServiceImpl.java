package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.NoticeUser;
import platform.education.oa.vo.NoticeUserCondition;
import platform.education.oa.service.NoticeUserService;
import platform.education.oa.dao.NoticeUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class NoticeUserServiceImpl implements NoticeUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private NoticeUserDao noticeUserDao;

	public void setNoticeUserDao(NoticeUserDao noticeUserDao) {
		this.noticeUserDao = noticeUserDao;
	}
	
	@Override
	public NoticeUser findNoticeUserById(Integer id) {
		try {
			return noticeUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public NoticeUser add(NoticeUser noticeUser) {
		if(noticeUser == null) {
    		return null;
    	}
    	Date createDate = noticeUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	noticeUser.setCreateDate(createDate);
    	noticeUser.setModifyDate(createDate);
		return noticeUserDao.create(noticeUser);
	}

	@Override
	public NoticeUser modify(NoticeUser noticeUser) {
		if(noticeUser == null) {
    		return null;
    	}
    	Date modify = noticeUser.getModifyDate();
    	noticeUser.setModifyDate(modify != null ? modify : new Date());
		return noticeUserDao.update(noticeUser);
	}
	
	@Override
	public void remove(NoticeUser noticeUser) {
		try {
			noticeUserDao.delete(noticeUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", noticeUser.getId(), e);
			}
		}
	}
	
	@Override
	public List<NoticeUser> findNoticeUserByCondition(NoticeUserCondition noticeUserCondition, Page page, Order order) {
		return noticeUserDao.findNoticeUserByCondition(noticeUserCondition, page, order);
	}
	
	@Override
	public List<NoticeUser> findNoticeUserByCondition(NoticeUserCondition noticeUserCondition) {
		return noticeUserDao.findNoticeUserByCondition(noticeUserCondition, null, null);
	}
	
	@Override
	public List<NoticeUser> findNoticeUserByCondition(NoticeUserCondition noticeUserCondition, Page page) {
		return noticeUserDao.findNoticeUserByCondition(noticeUserCondition, page, null);
	}
	
	@Override
	public List<NoticeUser> findNoticeUserByCondition(NoticeUserCondition noticeUserCondition, Order order) {
		return noticeUserDao.findNoticeUserByCondition(noticeUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.noticeUserDao.count(null);
	}

	@Override
	public Long count(NoticeUserCondition noticeUserCondition) {
		return this.noticeUserDao.count(noticeUserCondition);
	}

	
	public List<NoticeUser>  findByNotice(Integer notice_id,Integer user_id){
		return this.noticeUserDao.findByNotice(notice_id,user_id);
	}

	@Override
	public NoticeUser findByNoticeToUser(Integer notice_id, Integer user_id) {
		 
		return this.noticeUserDao.findByNoticeToUser(notice_id, user_id);
	}
}
