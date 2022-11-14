package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.NoticeUserDeleted;
import platform.education.oa.vo.NoticeUserDeletedCondition;
import platform.education.oa.service.NoticeUserDeletedService;
import platform.education.oa.dao.NoticeUserDeletedDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class NoticeUserDeletedServiceImpl implements NoticeUserDeletedService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private NoticeUserDeletedDao noticeUserDeletedDao;

	public void setNoticeUserDeletedDao(NoticeUserDeletedDao noticeUserDeletedDao) {
		this.noticeUserDeletedDao = noticeUserDeletedDao;
	}
	
	@Override
	public NoticeUserDeleted findNoticeUserDeletedById(Integer id) {
		try {
			return noticeUserDeletedDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public NoticeUserDeleted add(NoticeUserDeleted noticeUserDeleted) {
		if(noticeUserDeleted == null) {
    		return null;
    	}
    	Date createDate = noticeUserDeleted.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	noticeUserDeleted.setCreateDate(createDate);
    	noticeUserDeleted.setModifyDate(createDate);
		return noticeUserDeletedDao.create(noticeUserDeleted);
	}

	@Override
	public NoticeUserDeleted modify(NoticeUserDeleted noticeUserDeleted) {
		if(noticeUserDeleted == null) {
    		return null;
    	}
    	Date modify = noticeUserDeleted.getModifyDate();
    	noticeUserDeleted.setModifyDate(modify != null ? modify : new Date());
		return noticeUserDeletedDao.update(noticeUserDeleted);
	}
	
	@Override
	public void remove(NoticeUserDeleted noticeUserDeleted) {
		try {
			noticeUserDeletedDao.delete(noticeUserDeleted);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", noticeUserDeleted.getId(), e);
			}
		}
	}
	
	@Override
	public List<NoticeUserDeleted> findNoticeUserDeletedByCondition(NoticeUserDeletedCondition noticeUserDeletedCondition, Page page, Order order) {
		return noticeUserDeletedDao.findNoticeUserDeletedByCondition(noticeUserDeletedCondition, page, order);
	}
	
	@Override
	public List<NoticeUserDeleted> findNoticeUserDeletedByCondition(NoticeUserDeletedCondition noticeUserDeletedCondition) {
		return noticeUserDeletedDao.findNoticeUserDeletedByCondition(noticeUserDeletedCondition, null, null);
	}
	
	@Override
	public List<NoticeUserDeleted> findNoticeUserDeletedByCondition(NoticeUserDeletedCondition noticeUserDeletedCondition, Page page) {
		return noticeUserDeletedDao.findNoticeUserDeletedByCondition(noticeUserDeletedCondition, page, null);
	}
	
	@Override
	public List<NoticeUserDeleted> findNoticeUserDeletedByCondition(NoticeUserDeletedCondition noticeUserDeletedCondition, Order order) {
		return noticeUserDeletedDao.findNoticeUserDeletedByCondition(noticeUserDeletedCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.noticeUserDeletedDao.count(null);
	}

	@Override
	public Long count(NoticeUserDeletedCondition noticeUserDeletedCondition) {
		return this.noticeUserDeletedDao.count(noticeUserDeletedCondition);
	}

}
