package platform.education.oa.service;
import platform.education.oa.model.AppSumStatistics;
import platform.education.oa.vo.AppSumStatisticsCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface AppSumStatisticsService {
    AppSumStatistics findAppSumStatisticsById(Integer id);
	   
	AppSumStatistics add(AppSumStatistics appSumStatistics);
	   
	AppSumStatistics modify(AppSumStatistics appSumStatistics);
	   
	void remove(AppSumStatistics appSumStatistics);
	   
	List<AppSumStatistics> findAppSumStatisticsByCondition(AppSumStatisticsCondition appSumStatisticsCondition, Page page, Order order);
	
	List<AppSumStatistics> findAppSumStatisticsByCondition(AppSumStatisticsCondition appSumStatisticsCondition);
	
	List<AppSumStatistics> findAppSumStatisticsByCondition(AppSumStatisticsCondition appSumStatisticsCondition, Page page);
	
	List<AppSumStatistics> findAppSumStatisticsByCondition(AppSumStatisticsCondition appSumStatisticsCondition, Order order);
	
	Long count();
	
	Long count(AppSumStatisticsCondition appSumStatisticsCondition);
	AppSumStatistics findAppSumStatisticsToType(String type);
	
 
}
