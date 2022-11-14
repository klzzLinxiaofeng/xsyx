package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Comments;
import platform.education.oa.vo.CommentsCondition;
import platform.education.oa.vo.CommentsVo;
import platform.education.oa.service.CommentsService;
import platform.education.oa.dao.CommentsDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class CommentsServiceImpl implements CommentsService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private CommentsDao commentsDao;

	public void setCommentsDao(CommentsDao commentsDao) {
		this.commentsDao = commentsDao;
	}
	
	@Override
	public Comments findCommentsById(Integer id) {
		try {
			return commentsDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Comments add(Comments comments) {
		if(comments == null) {
    		return null;
    	}
    	Date createDate = comments.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	comments.setCreateDate(createDate);
    	comments.setModifyDate(createDate);
		return commentsDao.create(comments);
	}

	@Override
	public Comments modify(Comments comments) {
		if(comments == null) {
    		return null;
    	}
    	Date modify = comments.getModifyDate();
    	comments.setModifyDate(modify != null ? modify : new Date());
		return commentsDao.update(comments);
	}
	
	@Override
	public void remove(Comments comments) {
		try {
			commentsDao.delete(comments);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", comments.getId(), e);
			}
		}
	}
	
	@Override
	public List<Comments> findCommentsByCondition(CommentsCondition commentsCondition, Page page, Order order) {
		return commentsDao.findCommentsByCondition(commentsCondition, page, order);
	}
	
	@Override
	public List<Comments> findCommentsByCondition(CommentsCondition commentsCondition) {
		return commentsDao.findCommentsByCondition(commentsCondition, null, null);
	}
	
	@Override
	public List<Comments> findCommentsByCondition(CommentsCondition commentsCondition, Page page) {
		return commentsDao.findCommentsByCondition(commentsCondition, page, null);
	}
	
	@Override
	public List<Comments> findCommentsByCondition(CommentsCondition commentsCondition, Order order) {
		return commentsDao.findCommentsByCondition(commentsCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.commentsDao.count(null);
	}

	@Override
	public Long count(CommentsCondition commentsCondition) {
		return this.commentsDao.count(commentsCondition);
	}

	@Override
	public List<Comments> findCommentsByMeeting(Integer meetingId,
			Integer new_or_old, String baseline_date, Page page, Order order) {
		 
		return this.commentsDao.findCommentsByMeeting(meetingId, new_or_old, baseline_date, page, order);
	}

	@Override
	public List<CommentsVo> findCommentsVoByCondition(
			CommentsCondition commentsCondition, Page page, Order order) {
		return commentsDao.findCommentsVoByCondition(commentsCondition, page, order);
	}

}
