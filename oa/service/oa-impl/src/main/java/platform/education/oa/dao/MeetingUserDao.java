package platform.education.oa.dao;

import platform.education.oa.model.MeetingUser;
import platform.education.oa.vo.MeetingUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface MeetingUserDao extends GenericDao<MeetingUser, java.lang.Integer> {

	List<MeetingUser> findMeetingUserByCondition(MeetingUserCondition meetingUserCondition, Page page, Order order);
	
	MeetingUser findById(Integer id);
	
	MeetingUser findByUserId(Integer meetingId,Integer userId);
	
	Long count(MeetingUserCondition meetingUserCondition);
	
	List<MeetingUser> findMeetingUserByMetingId(Integer meetingId);
	
}
