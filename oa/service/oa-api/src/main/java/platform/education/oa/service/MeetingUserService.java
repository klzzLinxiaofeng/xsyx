package platform.education.oa.service;
import platform.education.oa.model.MeetingUser;
import platform.education.oa.vo.MeetingUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface MeetingUserService {
    MeetingUser findMeetingUserById(Integer id);
    MeetingUser findByUserId(Integer meetingId,Integer userId);  
	MeetingUser add(MeetingUser meetingUser);
	   
	MeetingUser modify(MeetingUser meetingUser);
	   
	void remove(MeetingUser meetingUser);
	   
	List<MeetingUser> findMeetingUserByCondition(MeetingUserCondition meetingUserCondition, Page page, Order order);
	
	List<MeetingUser> findMeetingUserByCondition(MeetingUserCondition meetingUserCondition);
	
	List<MeetingUser> findMeetingUserByCondition(MeetingUserCondition meetingUserCondition, Page page);
	
	List<MeetingUser> findMeetingUserByCondition(MeetingUserCondition meetingUserCondition, Order order);
	
	Long count();
	
	Long count(MeetingUserCondition meetingUserCondition);
	
}
