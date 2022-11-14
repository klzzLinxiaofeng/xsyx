package platform.education.oa.service;
import platform.education.oa.model.Notice;
import platform.education.oa.vo.NoticeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface NoticeService {
    Notice findNoticeById(Integer id);
	   
	Notice add(Notice notice);
	   
	Notice modify(Notice notice);
	   
	void remove(Notice notice);
	   
	List<Notice> findNoticeByCondition(NoticeCondition noticeCondition, Page page, Order order);
	
	List<Notice> findNoticeByCondition(NoticeCondition noticeCondition);
	
	List<Notice> findNoticeByCondition(NoticeCondition noticeCondition, Page page);
	
	List<Notice> findNoticeByCondition(NoticeCondition noticeCondition, Order order);
	List<Notice> findNoticeByConditionToUser(Integer ownerId,String ownerType,
			Integer userId, Page page, Order order);
	Long count();
	
	Long count(NoticeCondition noticeCondition);
	/**
	 * 查询发送给学校全体教工和部门的的通知
	 * @param receiverType
	 * @param ownerId
	 * @param ownerType
	 * @param userId
	 * @param new_or_old
	 * @param baseline_date
	 * @param page
	 * @param order
	 * @return
	 */
	List<Notice> findNotesOfDepartmentOrAllInSchool(Integer receiverType,Integer ownerId,String ownerType,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order);
	/**
	 * 查询我发布的通知
	 * @param ownerId
	 * @param ownerType
	 * @param userId
	 * @param new_or_old
	 * @param baseline_date
	 * @param page
	 * @param order
	 * @return
	 */
	List<Notice> findPostedNotesInSchool(Integer ownerId,String ownerType,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order);
	
	/**
	 * 该方法接收的参数有：
	 * 1、ownerId、ownerType、ssWord（关键字搜索）、
	 * 2、receiverType、receiverId、teacherId、posterId、isRelatedWithMe、isDepartmentRecord、
	 *   isMePublish 这十个个，多设置没有用
	 * 3、其中 ownerId、ownerType、ssWord 可不设置
	 * 4、设置了 isRelatedWithMe（是否与我相关）则必须设置 receiverType、receiverId、teacherId、posterId 这四个参数
	 * 5、设置了 isDepartmentRecord（是否是部门记录）则必须设置 teacherId
	 * 6、设置了 isMePublish（是否是我发表的）则必须设置 posterId
	 * 7、只设置  2 不设置 4、5、6 ，方法没有作用
	 * @param condition
	 * @param page
	 * @param order
	 * @return
	 */
	List<Notice> findNoticeByRelatedWithMe(NoticeCondition condition,
			Page page, Order order);

	List<Notice> findNoticeByRelatedWithMeForApp(NoticeCondition nCondition,
			Page page, Order order);
	
}
