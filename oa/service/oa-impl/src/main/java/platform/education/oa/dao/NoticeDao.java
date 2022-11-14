package platform.education.oa.dao;

import platform.education.oa.model.Notice;
import platform.education.oa.vo.NoticeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface NoticeDao extends GenericDao<Notice, java.lang.Integer> {

	List<Notice> findNoticeByCondition(NoticeCondition noticeCondition, Page page, Order order);
	
	Notice findById(Integer id);
	
	Long count(NoticeCondition noticeCondition);
	
	List<Notice> findNoticeByConditionToUser(Integer ownerId,String ownerType,
			Integer userId, Page page, Order order);
	
	List<Notice> findNotesOfDepartmentOrAllInSchool(Integer receiverType,Integer ownerId,String ownerType,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order);
	
	List<Notice> findPostedNotesInSchool(Integer ownerId,String ownerType,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order);

	List<Notice> findNoticeByRelatedWithMe(NoticeCondition condition,
			Page page, Order order);

	List<Notice> findNoticeByRelatedWithMeForApp(NoticeCondition nCondition,
			Page page, Order order);
}
