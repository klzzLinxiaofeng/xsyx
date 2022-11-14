package platform.education.generalTeachingAffair.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.SeatChart;
import platform.education.generalTeachingAffair.vo.SeatChartCondition;

public interface SeatChartDao extends GenericDao<SeatChart, java.lang.Integer> {

	List<SeatChart> findSeatChartByCondition(SeatChartCondition seatChartCondition, Page page, Order order);
	
	SeatChart findById(Integer id);
	
	Long count(SeatChartCondition seatChartCondition);
	/**
	 * 根据班级的id和学校的id查找对应的班级座位表
	 * @param teamId
	 * @param schoolId
	 * @return
	 */
	public List<SeatChart> findSeatChartOfTeam(Integer teamId,Integer schoolId);
	
	
	
}
