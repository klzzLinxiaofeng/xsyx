package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.AppStatistics;
import platform.education.oa.vo.AppStatisticsCondition;
import platform.education.oa.service.AppStatisticsService;
import platform.education.oa.dao.AppStatisticsDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class AppStatisticsServiceImpl implements AppStatisticsService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private AppStatisticsDao appStatisticsDao;

	public void setAppStatisticsDao(AppStatisticsDao appStatisticsDao) {
		this.appStatisticsDao = appStatisticsDao;
	}
	
	@Override
	public AppStatistics findAppStatisticsById(Integer id) {
		try {
			return appStatisticsDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public AppStatistics add(AppStatistics appStatistics) {
		if(appStatistics == null) {
    		return null;
    	}
    	Date createDate = appStatistics.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	appStatistics.setCreateDate(createDate);
    	appStatistics.setModifyDate(createDate);
		return appStatisticsDao.create(appStatistics);
	}

	@Override
	public AppStatistics modify(AppStatistics appStatistics) {
		if(appStatistics == null) {
    		return null;
    	}
    	Date modify = appStatistics.getModifyDate();
    	appStatistics.setModifyDate(modify != null ? modify : new Date());
		return appStatisticsDao.update(appStatistics);
	}
	
	@Override
	public void remove(AppStatistics appStatistics) {
		try {
			appStatisticsDao.delete(appStatistics);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", appStatistics.getId(), e);
			}
		}
	}
	
	@Override
	public List<AppStatistics> findAppStatisticsByCondition(AppStatisticsCondition appStatisticsCondition, Page page, Order order) {
		return appStatisticsDao.findAppStatisticsByCondition(appStatisticsCondition, page, order);
	}
	
	@Override
	public List<AppStatistics> findAppStatisticsByCondition(AppStatisticsCondition appStatisticsCondition) {
		return appStatisticsDao.findAppStatisticsByCondition(appStatisticsCondition, null, null);
	}
	
	@Override
	public List<AppStatistics> findAppStatisticsByCondition(AppStatisticsCondition appStatisticsCondition, Page page) {
		return appStatisticsDao.findAppStatisticsByCondition(appStatisticsCondition, page, null);
	}
	
	@Override
	public List<AppStatistics> findAppStatisticsByCondition(AppStatisticsCondition appStatisticsCondition, Order order) {
		return appStatisticsDao.findAppStatisticsByCondition(appStatisticsCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.appStatisticsDao.count(null);
	}

	@Override
	public Long count(AppStatisticsCondition appStatisticsCondition) {
		return this.appStatisticsDao.count(appStatisticsCondition);
	}

}
