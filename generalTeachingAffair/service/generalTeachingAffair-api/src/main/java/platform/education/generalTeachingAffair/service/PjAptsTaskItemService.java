package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjAptsTaskItem;
import platform.education.generalTeachingAffair.vo.PjAptsTaskItemCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PjAptsTaskItemService {
    PjAptsTaskItem findPjAptsTaskItemById(Integer id);
	   
	PjAptsTaskItem add(PjAptsTaskItem pjAptsTaskItem);
	   
	PjAptsTaskItem modify(PjAptsTaskItem pjAptsTaskItem);
	   
	void remove(PjAptsTaskItem pjAptsTaskItem);
	   
	List<PjAptsTaskItem> findPjAptsTaskItemByCondition(PjAptsTaskItemCondition pjAptsTaskItemCondition, Page page, Order order);
	
	List<PjAptsTaskItem> findPjAptsTaskItemByCondition(PjAptsTaskItemCondition pjAptsTaskItemCondition);
	
	List<PjAptsTaskItem> findPjAptsTaskItemByCondition(PjAptsTaskItemCondition pjAptsTaskItemCondition, Page page);
	
	List<PjAptsTaskItem> findPjAptsTaskItemByCondition(PjAptsTaskItemCondition pjAptsTaskItemCondition, Order order);
	
	Long count();
	
	Long count(PjAptsTaskItemCondition pjAptsTaskItemCondition);
	
	void createBatch(PjAptsTaskItem[] pati);
	
	void modifyIsDeleteByTaskId(Integer taskId);
}
