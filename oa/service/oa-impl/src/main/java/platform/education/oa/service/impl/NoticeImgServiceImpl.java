package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.NoticeImg;
import platform.education.oa.vo.NoticeImgCondition;
import platform.education.oa.service.NoticeImgService;
import platform.education.oa.dao.NoticeImgDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class NoticeImgServiceImpl implements NoticeImgService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private NoticeImgDao noticeImgDao;

	public void setNoticeImgDao(NoticeImgDao noticeImgDao) {
		this.noticeImgDao = noticeImgDao;
	}
	
	@Override
	public NoticeImg findNoticeImgById(Integer id) {
		try {
			return noticeImgDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public NoticeImg add(NoticeImg noticeImg) {
		if(noticeImg == null) {
    		return null;
    	}
    	Date createDate = noticeImg.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	noticeImg.setCreateDate(createDate);
    	noticeImg.setModifyDate(createDate);
		return noticeImgDao.create(noticeImg);
	}

	@Override
	public NoticeImg modify(NoticeImg noticeImg) {
		if(noticeImg == null) {
    		return null;
    	}
    	Date modify = noticeImg.getModifyDate();
    	noticeImg.setModifyDate(modify != null ? modify : new Date());
		return noticeImgDao.update(noticeImg);
	}
	
	@Override
	public void remove(NoticeImg noticeImg) {
		try {
			noticeImgDao.delete(noticeImg);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", noticeImg.getId(), e);
			}
		}
	}
	
	@Override
	public List<NoticeImg> findNoticeImgByCondition(NoticeImgCondition noticeImgCondition, Page page, Order order) {
		return noticeImgDao.findNoticeImgByCondition(noticeImgCondition, page, order);
	}
	
	@Override
	public List<NoticeImg> findNoticeImgByCondition(NoticeImgCondition noticeImgCondition) {
		return noticeImgDao.findNoticeImgByCondition(noticeImgCondition, null, null);
	}
	
	@Override
	public List<NoticeImg> findNoticeImgByCondition(NoticeImgCondition noticeImgCondition, Page page) {
		return noticeImgDao.findNoticeImgByCondition(noticeImgCondition, page, null);
	}
	
	@Override
	public List<NoticeImg> findNoticeImgByCondition(NoticeImgCondition noticeImgCondition, Order order) {
		return noticeImgDao.findNoticeImgByCondition(noticeImgCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.noticeImgDao.count(null);
	}

	@Override
	public Long count(NoticeImgCondition noticeImgCondition) {
		return this.noticeImgDao.count(noticeImgCondition);
	}

}
