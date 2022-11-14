package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.GroupTaskEvaluation;
import platform.education.generalTeachingAffair.vo.GroupTaskEvaluationCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.GroupTaskEvaluationVo;

import java.util.List;

public interface GroupTaskEvaluationService {
    GroupTaskEvaluation findGroupTaskEvaluationById(Integer id);
	   
	GroupTaskEvaluation add(GroupTaskEvaluation groupTaskEvaluation);
	   
	GroupTaskEvaluation modify(GroupTaskEvaluation groupTaskEvaluation);
	   
	void remove(GroupTaskEvaluation groupTaskEvaluation);
	   
	List<GroupTaskEvaluation> findGroupTaskEvaluationByCondition(GroupTaskEvaluationCondition groupTaskEvaluationCondition, Page page, Order order);
	
	List<GroupTaskEvaluation> findGroupTaskEvaluationByCondition(GroupTaskEvaluationCondition groupTaskEvaluationCondition);
	
	List<GroupTaskEvaluation> findGroupTaskEvaluationByCondition(GroupTaskEvaluationCondition groupTaskEvaluationCondition, Page page);
	
	List<GroupTaskEvaluation> findGroupTaskEvaluationByCondition(GroupTaskEvaluationCondition groupTaskEvaluationCondition, Order order);
	
	Long count();
	
	Long count(GroupTaskEvaluationCondition groupTaskEvaluationCondition);

	//批量创建
	void batchCreate(GroupTaskEvaluation[] list);

	//批量删除
	void batchDelete(GroupTaskEvaluationCondition groupTaskEvaluationCondition);

	List<GroupTaskEvaluationVo> findGroupTaskEvaluationByGroupIdAndGroupNum(Integer groupId, Integer groupNum);
}
