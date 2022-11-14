package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.Result;
import platform.education.generalTeachingAffair.vo.ResultCondition;
import platform.education.generalTeachingAffair.vo.ResultVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ResultService {
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
	
    Result findResultById(Integer id);
	   
	Result add(Result result);
	   
	Result modify(Result result);
	   
	void remove(Result result);
	   
	List<Result> findResultByCondition(ResultCondition resultCondition, Page page, Order order);
	
	List<Result> findResultByCondition(ResultCondition resultCondition);
	
	List<Result> findResultByCondition(ResultCondition resultCondition, Page page);
	
	List<Result> findResultByCondition(ResultCondition resultCondition, Order order);
	
	Long count();
	
	Long count(ResultCondition resultCondition);
	
	List<ResultVo> findResultVoByCondition(ResultCondition resultCondition, Page page, Order order);

	String moveTo(Result result);
}
