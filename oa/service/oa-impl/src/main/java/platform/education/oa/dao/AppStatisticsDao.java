package platform.education.oa.dao;

import platform.education.oa.model.AppStatistics;
import platform.education.oa.vo.AppStatisticsCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface AppStatisticsDao extends GenericDao<AppStatistics, java.lang.Integer> {

	List<AppStatistics> findAppStatisticsByCondition(AppStatisticsCondition appStatisticsCondition, Page page, Order order);
	
	AppStatistics findById(Integer id);
	
	Long count(AppStatisticsCondition appStatisticsCondition);
	
}
