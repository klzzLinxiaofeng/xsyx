package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.SeatChartItem;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.vo.SeatChartItemCondition;
import platform.education.generalTeachingAffair.vo.SeatChartItemVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SeatChartItemService {
    SeatChartItem findSeatChartItemById(Integer id);
	   
	SeatChartItem add(SeatChartItem seatChartItem);
	   
	void save(List<SeatChartItem> list);
	
	SeatChartItem modify(SeatChartItem seatChartItem);
	   
	void remove(SeatChartItem seatChartItem);
	   
	List<SeatChartItem> findSeatChartItemByCondition(SeatChartItemCondition seatChartItemCondition, Page page, Order order);
	
	List<SeatChartItem> findSeatChartItemByCondition(SeatChartItemCondition seatChartItemCondition);
	
	List<SeatChartItem> findSeatChartItemByCondition(SeatChartItemCondition seatChartItemCondition, Page page);
	
	List<SeatChartItem> findSeatChartItemByCondition(SeatChartItemCondition seatChartItemCondition, Order order);
	
	Long count();
	
	Long count(SeatChartItemCondition seatChartItemCondition);
	
	/**
	 * 根据班级分好教室的id查询班上的学生座位信息
	 * @param seatId 教室的id
	 * @return
	 */
	public List<SeatChartItemVo> findSeatChartItemVoBySeatId(Integer seatId);
	
	/**
	 * 根据班级查询未分教室座位的学生
	 * @param teamId 班级的id
	 * @param seatId 教室的id
	 * @return
	 */
	public List<Student> findStudentDoNotHasSeatInTeam(Integer teamId,Integer seatId);
	
	/**
	 * 根据教室的id查询该教室的学生
	 * @param seatId 教室的id
	 * @return
	 */
	public List<SeatChartItem> findBySeatId(Integer seatId);
	/**
	 * 根据教室的id删除该教室的所有学生
	 * @param seatId
	 */
	public void deleteBySeatId(Integer seatId);
	
}
