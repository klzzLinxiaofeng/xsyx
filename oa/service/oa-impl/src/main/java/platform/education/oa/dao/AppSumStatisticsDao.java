package platform.education.oa.dao;

import platform.education.oa.model.AppSumStatistics;
import platform.education.oa.vo.AppSumStatisticsCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface AppSumStatisticsDao extends GenericDao<AppSumStatistics, java.lang.Integer> {

	List<AppSumStatistics> findAppSumStatisticsByCondition(AppSumStatisticsCondition appSumStatisticsCondition, Page page, Order order);
	
	AppSumStatistics findById(Integer id);
	
	Long count(AppSumStatisticsCondition appSumStatisticsCondition);
	AppSumStatistics findAppSumStatisticsToType(String type);
}
