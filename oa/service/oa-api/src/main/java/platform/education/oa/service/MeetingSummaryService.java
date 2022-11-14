package platform.education.oa.service;
import platform.education.oa.model.MeetingSummary;
import platform.education.oa.vo.MeetingSummaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface MeetingSummaryService {
    MeetingSummary findMeetingSummaryById(Integer id);
	   
	MeetingSummary add(MeetingSummary meetingSummary);
	   
	MeetingSummary modify(MeetingSummary meetingSummary);
	   
	void remove(MeetingSummary meetingSummary);
	   
	List<MeetingSummary> findMeetingSummaryByCondition(MeetingSummaryCondition meetingSummaryCondition, Page page, Order order);
	
	List<MeetingSummary> findMeetingSummaryByCondition(MeetingSummaryCondition meetingSummaryCondition);
	
	List<MeetingSummary> findMeetingSummaryByCondition(MeetingSummaryCondition meetingSummaryCondition, Page page);
	
	List<MeetingSummary> findMeetingSummaryByCondition(MeetingSummaryCondition meetingSummaryCondition, Order order);
	
	Long count();
	
	Long count(MeetingSummaryCondition meetingSummaryCondition);
	
}
