package platform.education.oa.dao;

import platform.education.oa.model.Meeting;
import platform.education.oa.vo.MeetingCondition;
import platform.education.oa.vo.MeetingVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface MeetingDao extends GenericDao<Meeting, java.lang.Integer> {

	List<Meeting> findMeetingByCondition(MeetingCondition meetingCondition, Page page, Order order);
	List<Meeting>  findMeetingToUser(Integer schoolId,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order);
	
	List<Meeting>  findMeeting(Integer schoolId,
			Integer userId,Integer fanwei,Integer department_id, Integer new_or_old,String baseline_date,Page page, Order order);
	
	
	
	Meeting findById(Integer id);
	
	Long count(MeetingCondition meetingCondition);
	
	List<Meeting>  findMeetingToSummary(Integer schoolId,
			String endtime, Page page, Order order);
	List<MeetingVo> findMeetingByRelatedWithMe(MeetingCondition condition,Page page, Order order);
	
}
