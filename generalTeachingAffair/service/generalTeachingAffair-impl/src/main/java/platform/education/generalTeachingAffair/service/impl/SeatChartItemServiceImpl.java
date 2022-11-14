package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.SeatChartItem;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.vo.SeatChartItemCondition;
import platform.education.generalTeachingAffair.vo.SeatChartItemVo;
import platform.education.generalTeachingAffair.service.SeatChartItemService;
import platform.education.generalTeachingAffair.dao.SeatChartItemDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SeatChartItemServiceImpl implements SeatChartItemService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SeatChartItemDao seatChartItemDao;

	public void setSeatChartItemDao(SeatChartItemDao seatChartItemDao) {
		this.seatChartItemDao = seatChartItemDao;
	}
	
	@Override
	public SeatChartItem findSeatChartItemById(Integer id) {
		try {
			return seatChartItemDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SeatChartItem add(SeatChartItem seatChartItem) {
		if(seatChartItem == null) {
    		return null;
    	}
    	Date createDate = seatChartItem.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	seatChartItem.setCreateDate(createDate);
    	seatChartItem.setModifyDate(createDate);
		return seatChartItemDao.create(seatChartItem);
	}

	@Override
	public SeatChartItem modify(SeatChartItem seatChartItem) {
		if(seatChartItem == null) {
    		return null;
    	}
    	Date modify = seatChartItem.getModifyDate();
    	seatChartItem.setModifyDate(modify != null ? modify : new Date());
		return seatChartItemDao.update(seatChartItem);
	}
	
	@Override
	public void remove(SeatChartItem seatChartItem) {
		try {
			seatChartItemDao.delete(seatChartItem);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", seatChartItem.getId(), e);
			}
		}
	}
	
	@Override
	public List<SeatChartItem> findSeatChartItemByCondition(SeatChartItemCondition seatChartItemCondition, Page page, Order order) {
		return seatChartItemDao.findSeatChartItemByCondition(seatChartItemCondition, page, order);
	}
	
	@Override
	public List<SeatChartItem> findSeatChartItemByCondition(SeatChartItemCondition seatChartItemCondition) {
		return seatChartItemDao.findSeatChartItemByCondition(seatChartItemCondition, null, null);
	}
	
	@Override
	public List<SeatChartItem> findSeatChartItemByCondition(SeatChartItemCondition seatChartItemCondition, Page page) {
		return seatChartItemDao.findSeatChartItemByCondition(seatChartItemCondition, page, null);
	}
	
	@Override
	public List<SeatChartItem> findSeatChartItemByCondition(SeatChartItemCondition seatChartItemCondition, Order order) {
		return seatChartItemDao.findSeatChartItemByCondition(seatChartItemCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.seatChartItemDao.count(null);
	}

	@Override
	public Long count(SeatChartItemCondition seatChartItemCondition) {
		return this.seatChartItemDao.count(seatChartItemCondition);
	}

	@Override
	public List<SeatChartItemVo> findSeatChartItemVoBySeatId(Integer seatId) {
		return this.seatChartItemDao.findSeatChartItemVoBySeatId(seatId);
	}

	@Override
	public List<Student> findStudentDoNotHasSeatInTeam(Integer teamId, Integer seatId) {
		return this.seatChartItemDao.findStudentDoNotHasSeatInTeam(teamId, seatId);
	}

	@Override
	public List<SeatChartItem> findBySeatId(Integer seatId) {
		return this.seatChartItemDao.findBySeatId(seatId);
	}

	@Override
	public void deleteBySeatId(Integer seatId) {
		this.seatChartItemDao.deleteBySeatId(seatId);
	}

	@Override
	public void save(List<SeatChartItem> list) {
		for(SeatChartItem item:list){
			this.add(item);
		}
	}
}
