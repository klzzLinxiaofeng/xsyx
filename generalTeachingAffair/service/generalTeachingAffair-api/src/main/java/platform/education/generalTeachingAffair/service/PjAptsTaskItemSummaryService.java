package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjAptsTaskItemSummary;
import platform.education.generalTeachingAffair.vo.PjAptsTaskItemSummaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PjAptsTaskItemSummaryService {
    PjAptsTaskItemSummary findPjAptsTaskItemSummaryById(Integer id);
	   
	PjAptsTaskItemSummary add(PjAptsTaskItemSummary pjAptsTaskItemSummary);
	   
	PjAptsTaskItemSummary modify(PjAptsTaskItemSummary pjAptsTaskItemSummary);
	   
	void remove(PjAptsTaskItemSummary pjAptsTaskItemSummary);
	   
	List<PjAptsTaskItemSummary> findPjAptsTaskItemSummaryByCondition(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition, Page page, Order order);
	
	List<PjAptsTaskItemSummary> findPjAptsTaskItemSummaryByCondition(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition);
	
	List<PjAptsTaskItemSummary> findPjAptsTaskItemSummaryByCondition(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition, Page page);
	
	List<PjAptsTaskItemSummary> findPjAptsTaskItemSummaryByCondition(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition, Order order);
	
	Long count();
	
	Long count(PjAptsTaskItemSummaryCondition pjAptsTaskItemSummaryCondition);
	
	void createBatch(PjAptsTaskItemSummary[] pjAptsTaskItemSummaryList);
	
}
