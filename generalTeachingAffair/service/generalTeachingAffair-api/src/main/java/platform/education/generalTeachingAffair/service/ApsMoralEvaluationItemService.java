package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.ApsMoralEvaluationItem;
import platform.education.generalTeachingAffair.vo.ApsMoralEvaluationItemCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ApsMoralEvaluationItemService {
    ApsMoralEvaluationItem findApsMoralEvaluationItemById(Integer id);
	   
	ApsMoralEvaluationItem add(ApsMoralEvaluationItem apsMoralEvaluationItem);
	   
	ApsMoralEvaluationItem modify(ApsMoralEvaluationItem apsMoralEvaluationItem);
	   
	void remove(ApsMoralEvaluationItem apsMoralEvaluationItem);
	   
	List<ApsMoralEvaluationItem> findApsMoralEvaluationItemByCondition(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition, Page page, Order order);
	
	List<ApsMoralEvaluationItem> findApsMoralEvaluationItemByCondition(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition);
	
	List<ApsMoralEvaluationItem> findApsMoralEvaluationItemByCondition(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition, Page page);
	
	List<ApsMoralEvaluationItem> findApsMoralEvaluationItemByCondition(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition, Order order);
	
	Long count();
	
	Long count(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition);
	
}
