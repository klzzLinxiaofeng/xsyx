package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.AppSumStatistics;
import platform.education.oa.vo.AppSumStatisticsCondition;
import platform.education.oa.service.AppSumStatisticsService;
import platform.education.oa.dao.AppSumStatisticsDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class AppSumStatisticsServiceImpl implements AppSumStatisticsService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private AppSumStatisticsDao appSumStatisticsDao;

	public void setAppSumStatisticsDao(AppSumStatisticsDao appSumStatisticsDao) {
		this.appSumStatisticsDao = appSumStatisticsDao;
	}
	
	@Override
	public AppSumStatistics findAppSumStatisticsById(Integer id) {
		try {
			return appSumStatisticsDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public AppSumStatistics add(AppSumStatistics appSumStatistics) {
		if(appSumStatistics == null) {
    		return null;
    	}
    	Date createDate = appSumStatistics.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	appSumStatistics.setCreateDate(createDate);
    	appSumStatistics.setModifyDate(createDate);
		return appSumStatisticsDao.create(appSumStatistics);
	}

	@Override
	public AppSumStatistics modify(AppSumStatistics appSumStatistics) {
		if(appSumStatistics == null) {
    		return null;
    	}
    	Date modify = appSumStatistics.getModifyDate();
    	appSumStatistics.setModifyDate(modify != null ? modify : new Date());
		return appSumStatisticsDao.update(appSumStatistics);
	}
	
	@Override
	public void remove(AppSumStatistics appSumStatistics) {
		try {
			appSumStatisticsDao.delete(appSumStatistics);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", appSumStatistics.getId(), e);
			}
		}
	}
	
	@Override
	public List<AppSumStatistics> findAppSumStatisticsByCondition(AppSumStatisticsCondition appSumStatisticsCondition, Page page, Order order) {
		return appSumStatisticsDao.findAppSumStatisticsByCondition(appSumStatisticsCondition, page, order);
	}
	
	@Override
	public List<AppSumStatistics> findAppSumStatisticsByCondition(AppSumStatisticsCondition appSumStatisticsCondition) {
		return appSumStatisticsDao.findAppSumStatisticsByCondition(appSumStatisticsCondition, null, null);
	}
	
	@Override
	public List<AppSumStatistics> findAppSumStatisticsByCondition(AppSumStatisticsCondition appSumStatisticsCondition, Page page) {
		return appSumStatisticsDao.findAppSumStatisticsByCondition(appSumStatisticsCondition, page, null);
	}
	
	@Override
	public List<AppSumStatistics> findAppSumStatisticsByCondition(AppSumStatisticsCondition appSumStatisticsCondition, Order order) {
		return appSumStatisticsDao.findAppSumStatisticsByCondition(appSumStatisticsCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.appSumStatisticsDao.count(null);
	}

	@Override
	public Long count(AppSumStatisticsCondition appSumStatisticsCondition) {
		return this.appSumStatisticsDao.count(appSumStatisticsCondition);
	}

	@Override
	public AppSumStatistics findAppSumStatisticsToType(String type) {
		 
		return this.appSumStatisticsDao.findAppSumStatisticsToType(type);
	}

	 
}
