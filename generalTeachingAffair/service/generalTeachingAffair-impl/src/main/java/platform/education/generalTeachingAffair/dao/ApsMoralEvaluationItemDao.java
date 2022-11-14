package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ApsMoralEvaluationItem;
import platform.education.generalTeachingAffair.vo.ApsMoralEvaluationItemCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApsMoralEvaluationItemDao extends GenericDao<ApsMoralEvaluationItem, java.lang.Integer> {

	List<ApsMoralEvaluationItem> findApsMoralEvaluationItemByCondition(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition, Page page, Order order);
	
	ApsMoralEvaluationItem findById(Integer id);
	
	Long count(ApsMoralEvaluationItemCondition apsMoralEvaluationItemCondition);
	
}
