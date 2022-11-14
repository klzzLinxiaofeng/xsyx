package platform.education.oa.service;
import platform.education.oa.model.MeetingDepartmentCount;
import platform.education.oa.vo.MeetingDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface MeetingDepartmentCountService {
    MeetingDepartmentCount findMeetingDepartmentCountById(Integer id);
	   
	MeetingDepartmentCount add(MeetingDepartmentCount meetingDepartmentCount);
	   
	MeetingDepartmentCount modify(MeetingDepartmentCount meetingDepartmentCount);
	   
	void remove(MeetingDepartmentCount meetingDepartmentCount);
	   
	List<MeetingDepartmentCount> findMeetingDepartmentCountByCondition(MeetingDepartmentCountCondition meetingDepartmentCountCondition, Page page, Order order);
	
	List<MeetingDepartmentCount> findMeetingDepartmentCountByCondition(MeetingDepartmentCountCondition meetingDepartmentCountCondition);
	
	List<MeetingDepartmentCount> findMeetingDepartmentCountByCondition(MeetingDepartmentCountCondition meetingDepartmentCountCondition, Page page);
	
	List<MeetingDepartmentCount> findMeetingDepartmentCountByCondition(MeetingDepartmentCountCondition meetingDepartmentCountCondition, Order order);
	
	Long count();
	
	Long count(MeetingDepartmentCountCondition meetingDepartmentCountCondition);
	
}
