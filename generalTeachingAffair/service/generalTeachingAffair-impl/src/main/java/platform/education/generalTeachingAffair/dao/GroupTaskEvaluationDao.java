package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.GroupTaskEvaluation;
import platform.education.generalTeachingAffair.vo.GroupTaskEvaluationCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.GroupTaskEvaluationVo;

import java.util.List;

public interface GroupTaskEvaluationDao extends GenericDao<GroupTaskEvaluation, Integer> {

	List<GroupTaskEvaluation> findGroupTaskEvaluationByCondition(GroupTaskEvaluationCondition groupTaskEvaluationCondition, Page page, Order order);
	
	GroupTaskEvaluation findById(Integer id);
	
	Long count(GroupTaskEvaluationCondition groupTaskEvaluationCondition);

	void batchCreate(GroupTaskEvaluation[] list);

	void batchDelete(GroupTaskEvaluationCondition groupTaskEvaluationCondition);

	List<GroupTaskEvaluationVo> findGroupTaskEvaluationByGroupIdAndGroupNum(Integer groupId, Integer groupNum);
}
