package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.oa.dao.NoticeDao;
import platform.education.oa.model.Notice;
import platform.education.oa.service.NoticeService;
import platform.education.oa.vo.NoticeCondition;

public class NoticeServiceImpl implements NoticeService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private NoticeDao noticeDao;

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	

	

	@Override
	public Notice findNoticeById(Integer id) {
		try {
			return noticeDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Notice add(Notice notice) {
		if(notice == null) {
    		return null;
    	}
    	Date createDate = notice.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	notice.setCreateDate(createDate);
    	notice.setModifyDate(createDate);
		return noticeDao.create(notice);
	}

	@Override
	public Notice modify(Notice notice) {
		if(notice == null) {
    		return null;
    	}
    	Date modify = notice.getModifyDate();
    	notice.setModifyDate(modify != null ? modify : new Date());
		return noticeDao.update(notice);
	}
	
	@Override
	public void remove(Notice notice) {
		try {
			noticeDao.delete(notice);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", notice.getId(), e);
			}
		}
	}
	
	@Override
	public List<Notice> findNoticeByCondition(NoticeCondition noticeCondition, Page page, Order order) {
		return noticeDao.findNoticeByCondition(noticeCondition, page, order);
	}
	
	@Override
	public List<Notice> findNoticeByCondition(NoticeCondition noticeCondition) {
		return noticeDao.findNoticeByCondition(noticeCondition, null, null);
	}
	
	@Override
	public List<Notice> findNoticeByCondition(NoticeCondition noticeCondition, Page page) {
		return noticeDao.findNoticeByCondition(noticeCondition, page, null);
	}
	
	@Override
	public List<Notice> findNoticeByCondition(NoticeCondition noticeCondition, Order order) {
		return noticeDao.findNoticeByCondition(noticeCondition, null, order);
	}
	public List<Notice> findNoticeByConditionToUser(Integer ownerId,String ownerType,
			Integer userId, Page page, Order order){
		return noticeDao.findNoticeByConditionToUser(ownerId,ownerType, userId, page, order);
	}
	@Override
	public Long count() {
		return this.noticeDao.count(null);
	}

	@Override
	public Long count(NoticeCondition noticeCondition) {
		return this.noticeDao.count(noticeCondition);
	}
	
	public List<Notice> findNotesOfDepartmentOrAllInSchool(Integer receiverType,Integer ownerId,String ownerType,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order){
		return this.noticeDao.findNotesOfDepartmentOrAllInSchool(receiverType, ownerId, ownerType, userId, new_or_old, baseline_date, page, order);
	}
			
	
	public List<Notice> findPostedNotesInSchool(Integer ownerId,String ownerType,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order){
		return this.noticeDao.findPostedNotesInSchool(ownerId, ownerType, userId, new_or_old, baseline_date, page, order);
	}

	@Override
	public List<Notice> findNoticeByRelatedWithMe(NoticeCondition condition,
			Page page, Order order) {
		return noticeDao.findNoticeByRelatedWithMe(condition, page, order);
	}

	@Override
	public List<Notice> findNoticeByRelatedWithMeForApp(
			NoticeCondition nCondition, Page page, Order order) {
		// TODO Auto-generated method stub
		return noticeDao.findNoticeByRelatedWithMeForApp(nCondition, page, order);
	}


}
