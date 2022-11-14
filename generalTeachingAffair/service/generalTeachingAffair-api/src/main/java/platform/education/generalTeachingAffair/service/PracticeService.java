package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.Practice;
import platform.education.generalTeachingAffair.vo.PracticeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PracticeService {
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
	
    Practice findPracticeById(Integer id);
	   
	Practice add(Practice practice);
	   
	Practice modify(Practice practice);
	   
	void remove(Practice practice);
	   
	List<Practice> findPracticeByCondition(PracticeCondition practiceCondition, Page page, Order order);
	
	List<Practice> findPracticeByCondition(PracticeCondition practiceCondition);
	
	List<Practice> findPracticeByCondition(PracticeCondition practiceCondition, Page page);
	
	List<Practice> findPracticeByCondition(PracticeCondition practiceCondition, Order order);
	
	Long count();
	
	Long count(PracticeCondition practiceCondition);

	String moveTo(Practice practice);
	
}
