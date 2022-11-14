package platform.education.oa.dao;

import platform.education.oa.model.NoticeUserDeleted;
import platform.education.oa.vo.NoticeUserDeletedCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface NoticeUserDeletedDao extends GenericDao<NoticeUserDeleted, java.lang.Integer> {

	List<NoticeUserDeleted> findNoticeUserDeletedByCondition(NoticeUserDeletedCondition noticeUserDeletedCondition, Page page, Order order);
	
	NoticeUserDeleted findById(Integer id);
	
	Long count(NoticeUserDeletedCondition noticeUserDeletedCondition);
	
}
