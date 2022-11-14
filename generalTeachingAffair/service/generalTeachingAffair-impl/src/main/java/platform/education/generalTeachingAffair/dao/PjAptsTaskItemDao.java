package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjAptsTaskItem;
import platform.education.generalTeachingAffair.vo.PjAptsTaskItemCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjAptsTaskItemDao extends GenericDao<PjAptsTaskItem, java.lang.Integer> {

	List<PjAptsTaskItem> findPjAptsTaskItemByCondition(PjAptsTaskItemCondition pjAptsTaskItemCondition, Page page, Order order);
	
	PjAptsTaskItem findById(Integer id);
	
	Long count(PjAptsTaskItemCondition pjAptsTaskItemCondition);
	
	void createBatch(PjAptsTaskItem[] pati);
	
	void updateIsDeleteByTaskId(Integer taskId);
	
}
