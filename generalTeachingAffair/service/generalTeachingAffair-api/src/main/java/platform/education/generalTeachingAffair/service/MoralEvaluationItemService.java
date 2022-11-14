package platform.education.generalTeachingAffair.service;
import java.util.List;

import platform.education.generalTeachingAffair.model.MoralEvaluationItem;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface MoralEvaluationItemService {
	
	/**
	 * 当前接口crud操作 成功时返回的状态值
	 */
	public final static String OPERATE_SUCCESS = "success";
	
	/**
	 * 当前接口crud操作 失败时返回的状态值
	 */
	public final static String OPERATE_FAIL = "fail";
	
	/**
	 * 系统异常造成的操作失败 系统返回的状态值
	 */
	public final static String OPERATE_ERROR = "error";
	
    MoralEvaluationItem findMoralEvaluationItemById(Integer id);
	   
	MoralEvaluationItem add(MoralEvaluationItem moralEvaluationItem);
	   
	MoralEvaluationItem modify(MoralEvaluationItem moralEvaluationItem);
	   
	void remove(MoralEvaluationItem moralEvaluationItem);
	   
	List<MoralEvaluationItem> findMoralEvaluationItemByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition, Page page, Order order);
	
	List<MoralEvaluationItem> findMoralEvaluationItemByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition);
	
	List<MoralEvaluationItem> findMoralEvaluationItemByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition, Page page);
	
	List<MoralEvaluationItem> findMoralEvaluationItemByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition, Order order);
	
	Long count();
	
	Long count(MoralEvaluationItemCondition moralEvaluationItemCondition);
	
	void remove(MoralEvaluationItemCondition moralEvaluationItemCondition);
	
	/**
	 * 功能描述：逻辑上删除用户账号即数据库仍然保存记录，只是修改删除标识
	 * @param moralEvaluationItem
	 * @return
	 */
	String abandon(MoralEvaluationItem moralEvaluationItem);
	
	/**
	 * 功能描述：从德育评价结果表里关联查询出某个学生的评价结果
	 * @param moralEvaluationItemCondition
	 * @return
	 */
	List<MoralEvaluationItemVo> findMoralEvaluationItemByConditionMore(MoralEvaluationItemCondition moralEvaluationItemCondition);
}
