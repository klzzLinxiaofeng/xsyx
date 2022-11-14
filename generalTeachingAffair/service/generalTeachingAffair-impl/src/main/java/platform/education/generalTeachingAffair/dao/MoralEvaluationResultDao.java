package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.MoralEvaluationResult;
import platform.education.generalTeachingAffair.vo.MoralEvaluationResultCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface MoralEvaluationResultDao extends GenericDao<MoralEvaluationResult, java.lang.Integer> {

	List<MoralEvaluationResult> findMoralEvaluationResultByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition, Page page, Order order);
	
	MoralEvaluationResult findById(Integer id);
	
	Long count(MoralEvaluationResultCondition moralEvaluationResultCondition);
	
	void deleteByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition);
}
