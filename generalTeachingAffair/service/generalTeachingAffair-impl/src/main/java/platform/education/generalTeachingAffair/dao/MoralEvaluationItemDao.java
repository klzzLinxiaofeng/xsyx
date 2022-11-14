package platform.education.generalTeachingAffair.dao;

import java.util.List;

import platform.education.generalTeachingAffair.model.MoralEvaluationItem;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemVo;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface MoralEvaluationItemDao extends GenericDao<MoralEvaluationItem, java.lang.Integer> {

	List<MoralEvaluationItem> findMoralEvaluationItemByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition, Page page, Order order);
	
	/**
	 * 功能描述：从德育评价结果表里关联查询出某个学生的评价结果
	 * @param moralEvaluationItemCondition
	 * @return
	 */
	List<MoralEvaluationItemVo> findMoralEvaluationItemByConditionMore(MoralEvaluationItemCondition moralEvaluationItemCondition);
	
	MoralEvaluationItem findById(Integer id);
	
	Long count(MoralEvaluationItemCondition moralEvaluationItemCondition);
	
	void deleteByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition);
}
