package platform.education.generalTeachingAffair.dao;

import java.util.Date;
import java.util.List;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsTaskItem;

public interface ApsTaskItemDao extends GenericDao<ApsTaskItem, java.lang.Integer> {

	ApsTaskItem findById(Integer id);
	
	List<ApsTaskItem> findApsTaskItemByCondition(ApsTaskItem apsTaskItem);

	//根据任务Id找到所有评价项目
	List<ApsTaskItem> findAllItems(Integer taskId);
	
	//获取评价项目--未删除的
	List<ApsTaskItem> findOneTypeItems(Integer taskId, String checkType);
	
	//获取评价项目--已启用&&未删除的
	List<ApsTaskItem> findEnableItems(Integer taskId, String checkType);
	
//	//获得所有项目(无论是否删除)
//	List<ApsTaskItem> findItems(Integer taskId);
//	
//	//获得某类型项目(无论是否删除)
//	List<ApsTaskItem> findItemsOfType(Integer taskId, String checkType);
	
	//获得某一类型的项目（激励评价）
	List<ApsTaskItem> findItemsOfCategory(Integer taskId, String checkType, String category);
	
	//根据名字找到项目
	List<ApsTaskItem> findByItemName(Integer taskId, String name);
	
	//获取已启用的项目 && score表对应的项目（去重）
	List<ApsTaskItem> findUnionItem(Integer taskId, String checkType, Date beginDate, Date endDate);
}
