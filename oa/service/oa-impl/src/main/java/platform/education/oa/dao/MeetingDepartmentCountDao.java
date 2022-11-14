package platform.education.oa.dao;

import platform.education.oa.model.MeetingDepartmentCount;
import platform.education.oa.vo.MeetingDepartmentCountCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface MeetingDepartmentCountDao extends GenericDao<MeetingDepartmentCount, java.lang.Integer> {

	List<MeetingDepartmentCount> findMeetingDepartmentCountByCondition(MeetingDepartmentCountCondition meetingDepartmentCountCondition, Page page, Order order);
	
	MeetingDepartmentCount findById(Integer id);
	
	Long count(MeetingDepartmentCountCondition meetingDepartmentCountCondition);
	
}
