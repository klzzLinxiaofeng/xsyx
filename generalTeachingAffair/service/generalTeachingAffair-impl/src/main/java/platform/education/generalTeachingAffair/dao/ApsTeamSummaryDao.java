package platform.education.generalTeachingAffair.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ApsTeamSummary;

public interface ApsTeamSummaryDao extends GenericDao<ApsTeamSummary, java.lang.Integer> {

	ApsTeamSummary findById(Integer id);
	
	List<ApsTeamSummary> findApsTeamSummaryByRedBanner(Integer taskID,String termCode,String periodCode,Integer medalID,Page page,Order order);
}
