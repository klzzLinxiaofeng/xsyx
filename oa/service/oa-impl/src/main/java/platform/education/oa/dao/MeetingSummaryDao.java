package platform.education.oa.dao;

import platform.education.oa.model.MeetingSummary;
import platform.education.oa.vo.MeetingSummaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface MeetingSummaryDao extends GenericDao<MeetingSummary, java.lang.Integer> {

	List<MeetingSummary> findMeetingSummaryByCondition(MeetingSummaryCondition meetingSummaryCondition, Page page, Order order);
	
	MeetingSummary findById(Integer id);
	
	Long count(MeetingSummaryCondition meetingSummaryCondition);
	
}
