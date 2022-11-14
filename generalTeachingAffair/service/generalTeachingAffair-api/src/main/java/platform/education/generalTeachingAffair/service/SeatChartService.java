package platform.education.generalTeachingAffair.service;
import java.util.List;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.SeatChart;
import platform.education.generalTeachingAffair.vo.SeatChartCondition;

public interface SeatChartService {
    SeatChart findSeatChartById(Integer id);
	   
	SeatChart add(SeatChart seatChart);
	   
	SeatChart modify(SeatChart seatChart);
	   
	void remove(SeatChart seatChart);
	  
	/**
	 * 通过学校ID和年级ID，得到座位表列表
	 */
	public List<SeatChart> findSeatChartOfTeamAndSchool(Integer teamId,Integer schoolId);
	
	List<SeatChart> findSeatChartByCondition(SeatChartCondition seatChartCondition, Page page, Order order);
	
	List<SeatChart> findSeatChartByCondition(SeatChartCondition seatChartCondition);
	
	List<SeatChart> findSeatChartByCondition(SeatChartCondition seatChartCondition, Page page);
	
	List<SeatChart> findSeatChartByCondition(SeatChartCondition seatChartCondition, Order order);
	
	Long count();
	
	Long count(SeatChartCondition seatChartCondition);
	
}
