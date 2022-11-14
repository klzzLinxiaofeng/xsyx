package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjAptsTaskItemSummary;
import platform.education.generalTeachingAffair.vo.PjAptsTaskItemSummaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjAptsTaskItemSummaryDao extends GenericDao<PjAptsTaskItemSummary, java.lang.Integer> {

	List<PjAptsTaskItemSummary> findPjAptsTaskItemSummaryByCondition(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition, Page page, Order order);
	
	PjAptsTaskItemSummary findById(Integer id);
	
	Long count(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition);
	
	void createBatch(PjAptsTaskItemSummary[] pjAptsTaskItemSummaryList);
}
