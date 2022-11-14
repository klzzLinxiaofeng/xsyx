package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.MoralEvaluationResult;
import platform.education.generalTeachingAffair.vo.MoralEvaluationResultCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface MoralEvaluationResultService {
    MoralEvaluationResult findMoralEvaluationResultById(Integer id);
	   
	MoralEvaluationResult add(MoralEvaluationResult moralEvaluationResult);
	   
	MoralEvaluationResult modify(MoralEvaluationResult moralEvaluationResult);
	   
	void remove(MoralEvaluationResult moralEvaluationResult);
	   
	List<MoralEvaluationResult> findMoralEvaluationResultByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition, Page page, Order order);
	
	List<MoralEvaluationResult> findMoralEvaluationResultByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition);
	
	List<MoralEvaluationResult> findMoralEvaluationResultByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition, Page page);
	
	List<MoralEvaluationResult> findMoralEvaluationResultByCondition(MoralEvaluationResultCondition moralEvaluationResultCondition, Order order);
	
	Long count();
	
	Long count(MoralEvaluationResultCondition moralEvaluationResultCondition);
	
	void remove(MoralEvaluationResultCondition moralEvaluationResultCondition);
	
}
