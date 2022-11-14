package platform.education.oa.service;
import platform.education.oa.model.Meeting;
import platform.education.oa.vo.MeetingCondition;
import platform.education.oa.vo.MeetingVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface MeetingService {
    Meeting findMeetingById(Integer id);
	   
	Meeting add(Meeting meeting);
	   
	Meeting modify(Meeting meeting);
	   
	void remove(Meeting meeting);
	   
	List<Meeting> findMeetingByCondition(MeetingCondition meetingCondition, Page page, Order order);
	
	List<Meeting> findMeetingByCondition(MeetingCondition meetingCondition);
	
	List<Meeting> findMeetingByCondition(MeetingCondition meetingCondition, Page page);
	
	List<Meeting> findMeetingByCondition(MeetingCondition meetingCondition, Order order);
	List<Meeting>  findMeetingToUser(Integer schoolId,
			Integer userId, Page page, Order order);
	
	List<Meeting>  findMeetingToUser(Integer schoolId,
			Integer userId,Integer new_or_old,String baseline_date, Page page, Order order);
	Long count();
	
	Long count(MeetingCondition meetingCondition);
	List<Meeting>  findMeetingToSummary(Integer schoolId,
			String endtime, Page page, Order order);
	
	List<Meeting>  findMeeting(Integer schoolId,
			Integer userId,Integer fanwei,Integer department_id, Integer new_or_old,String baseline_date,Page page, Order order);

	List<MeetingVo> findMeetingByRelatedWithMe(MeetingCondition condition,Page page, Order order);
}
