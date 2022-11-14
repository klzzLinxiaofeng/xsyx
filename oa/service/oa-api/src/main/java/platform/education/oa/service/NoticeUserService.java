package platform.education.oa.service;
import platform.education.oa.model.NoticeUser;
import platform.education.oa.vo.NoticeUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface NoticeUserService {
    NoticeUser findNoticeUserById(Integer id);
	   
	NoticeUser add(NoticeUser noticeUser);
	   
	NoticeUser modify(NoticeUser noticeUser);
	   
	void remove(NoticeUser noticeUser);
	   
	List<NoticeUser> findNoticeUserByCondition(NoticeUserCondition noticeUserCondition, Page page, Order order);
	
	List<NoticeUser> findNoticeUserByCondition(NoticeUserCondition noticeUserCondition);
	
	List<NoticeUser> findNoticeUserByCondition(NoticeUserCondition noticeUserCondition, Page page);
	
	List<NoticeUser> findNoticeUserByCondition(NoticeUserCondition noticeUserCondition, Order order);
	
	Long count();
	
	Long count(NoticeUserCondition noticeUserCondition);
	
	List<NoticeUser>  findByNotice(Integer notice_id,Integer user_id);
	NoticeUser findByNoticeToUser(Integer notice_id,Integer user_id);
	
}
