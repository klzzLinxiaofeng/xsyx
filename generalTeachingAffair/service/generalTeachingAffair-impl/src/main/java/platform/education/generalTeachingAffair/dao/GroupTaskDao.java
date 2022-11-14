package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.GroupTask;
import platform.education.generalTeachingAffair.vo.GroupTaskCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.GroupTaskVo;

import java.util.List;

public interface GroupTaskDao extends GenericDao<GroupTask, Integer> {

	List<GroupTask> findGroupTaskByCondition(GroupTaskCondition groupTaskCondition, Page page, Order order);
	
	GroupTask findById(Integer id);
	
	Long count(GroupTaskCondition groupTaskCondition);

	void batchCreate(GroupTask[] list);

	List<Integer> findGroupIdByHourAndPeriod(GroupTaskCondition groupTaskCondition);

	List<Integer> findQuestionNum(GroupTaskCondition groupTaskCondition);

	List<GroupTaskVo> findGroupTaskByGroupIdAndGroupNum(Integer groupId, Integer groupNum, Integer studentId);
}
