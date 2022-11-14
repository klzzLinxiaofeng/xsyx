package platform.education.oa.service;
import platform.education.oa.model.NoticeUserDeleted;
import platform.education.oa.vo.NoticeUserDeletedCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface NoticeUserDeletedService {
    NoticeUserDeleted findNoticeUserDeletedById(Integer id);
	   
	NoticeUserDeleted add(NoticeUserDeleted noticeUserDeleted);
	   
	NoticeUserDeleted modify(NoticeUserDeleted noticeUserDeleted);
	   
	void remove(NoticeUserDeleted noticeUserDeleted);
	   
	List<NoticeUserDeleted> findNoticeUserDeletedByCondition(NoticeUserDeletedCondition noticeUserDeletedCondition, Page page, Order order);
	
	List<NoticeUserDeleted> findNoticeUserDeletedByCondition(NoticeUserDeletedCondition noticeUserDeletedCondition);
	
	List<NoticeUserDeleted> findNoticeUserDeletedByCondition(NoticeUserDeletedCondition noticeUserDeletedCondition, Page page);
	
	List<NoticeUserDeleted> findNoticeUserDeletedByCondition(NoticeUserDeletedCondition noticeUserDeletedCondition, Order order);
	
	Long count();
	
	Long count(NoticeUserDeletedCondition noticeUserDeletedCondition);
	
}
