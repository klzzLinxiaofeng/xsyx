package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.GroupTask;
import platform.education.generalTeachingAffair.vo.GroupTaskCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.GroupTaskVo;

import java.util.List;

public interface GroupTaskService {
    GroupTask findGroupTaskById(Integer id);
	   
	GroupTask add(GroupTask groupTask);
	   
	GroupTask modify(GroupTask groupTask);
	   
	void remove(GroupTask groupTask);
	   
	List<GroupTask> findGroupTaskByCondition(GroupTaskCondition groupTaskCondition, Page page, Order order);
	
	List<GroupTask> findGroupTaskByCondition(GroupTaskCondition groupTaskCondition);
	
	List<GroupTask> findGroupTaskByCondition(GroupTaskCondition groupTaskCondition, Page page);
	
	List<GroupTask> findGroupTaskByCondition(GroupTaskCondition groupTaskCondition, Order order);
	
	Long count();
	
	Long count(GroupTaskCondition groupTaskCondition);

	void batchCreate(GroupTask[] list);

	//获取分组去重ID
	List<Integer> findGroupIdByHourAndPeriod(GroupTaskCondition groupTaskCondition);

	//获取数据去重question_num
	List<Integer> findQuestionNum(GroupTaskCondition groupTaskCondition);

	List<GroupTaskVo> findGroupTaskByGroupIdAndGroupNum(Integer groupId, Integer groupNum, Integer studentId);
}
