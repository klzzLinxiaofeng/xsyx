package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.SeatChartDao;
import platform.education.generalTeachingAffair.model.SeatChart;
import platform.education.generalTeachingAffair.service.SeatChartService;
import platform.education.generalTeachingAffair.vo.SeatChartCondition;

public class SeatChartServiceImpl implements SeatChartService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SeatChartDao seatChartDao;

	public void setSeatChartDao(SeatChartDao seatChartDao) {
		this.seatChartDao = seatChartDao;
	}
	
	@Override
	public SeatChart findSeatChartById(Integer id) {
		try {
			return seatChartDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SeatChart add(SeatChart seatChart) {
		if(seatChart == null) {
    		return null;
    	}
    	Date createDate = seatChart.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	seatChart.setCreateDate(createDate);
    	seatChart.setModifyDate(createDate);
		return seatChartDao.create(seatChart);
	}

	@Override
	public SeatChart modify(SeatChart seatChart) {
		if(seatChart == null) {
    		return null;
    	}
    	Date modify = seatChart.getModifyDate();
    	seatChart.setModifyDate(modify != null ? modify : new Date());
		return seatChartDao.update(seatChart);
	}
	
	@Override
	public void remove(SeatChart seatChart) {
		try {
			seatChartDao.delete(seatChart);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", seatChart.getId(), e);
			}
		}
	}
	
	@Override
	public List<SeatChart> findSeatChartByCondition(SeatChartCondition seatChartCondition, Page page, Order order) {
		return seatChartDao.findSeatChartByCondition(seatChartCondition, page, order);
	}
	
	@Override
	public List<SeatChart> findSeatChartByCondition(SeatChartCondition seatChartCondition) {
		return seatChartDao.findSeatChartByCondition(seatChartCondition, null, null);
	}
	
	@Override
	public List<SeatChart> findSeatChartByCondition(SeatChartCondition seatChartCondition, Page page) {
		return seatChartDao.findSeatChartByCondition(seatChartCondition, page, null);
	}
	
	@Override
	public List<SeatChart> findSeatChartByCondition(SeatChartCondition seatChartCondition, Order order) {
		return seatChartDao.findSeatChartByCondition(seatChartCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.seatChartDao.count(null);
	}

	@Override
	public Long count(SeatChartCondition seatChartCondition) {
		return this.seatChartDao.count(seatChartCondition);
	}

	@Override
	public List<SeatChart> findSeatChartOfTeamAndSchool(Integer teamId, Integer schoolId) {
		return seatChartDao.findSeatChartOfTeam(teamId, schoolId);
	}

}
