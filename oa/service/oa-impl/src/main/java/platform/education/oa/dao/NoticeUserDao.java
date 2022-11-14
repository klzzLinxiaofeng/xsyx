package platform.education.oa.dao;

import platform.education.oa.model.NoticeUser;
import platform.education.oa.vo.NoticeUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface NoticeUserDao extends GenericDao<NoticeUser, java.lang.Integer> {

	List<NoticeUser> findNoticeUserByCondition(NoticeUserCondition noticeUserCondition, Page page, Order order);
	
	NoticeUser findById(Integer id);
	
	Long count(NoticeUserCondition noticeUserCondition);
	
	List<NoticeUser>  findByNotice(Integer notice_id,Integer user_id);
	
	NoticeUser findByNoticeToUser(Integer notice_id,Integer user_id);
}
