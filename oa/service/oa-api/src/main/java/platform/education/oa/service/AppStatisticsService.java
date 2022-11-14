package platform.education.oa.service;
import platform.education.oa.model.AppStatistics;
import platform.education.oa.vo.AppStatisticsCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface AppStatisticsService {
    AppStatistics findAppStatisticsById(Integer id);
	   
	AppStatistics add(AppStatistics appStatistics);
	   
	AppStatistics modify(AppStatistics appStatistics);
	   
	void remove(AppStatistics appStatistics);
	   
	List<AppStatistics> findAppStatisticsByCondition(AppStatisticsCondition appStatisticsCondition, Page page, Order order);
	
	List<AppStatistics> findAppStatisticsByCondition(AppStatisticsCondition appStatisticsCondition);
	
	List<AppStatistics> findAppStatisticsByCondition(AppStatisticsCondition appStatisticsCondition, Page page);
	
	List<AppStatistics> findAppStatisticsByCondition(AppStatisticsCondition appStatisticsCondition, Order order);
	
	Long count();
	
	Long count(AppStatisticsCondition appStatisticsCondition);
	
}
