package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.AcademicExchange;
import platform.education.generalTeachingAffair.vo.AcademicExchangeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface AcademicExchangeService {
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
	
    AcademicExchange findAcademicExchangeById(Integer id);
	   
	AcademicExchange add(AcademicExchange academicExchange);
	   
	AcademicExchange modify(AcademicExchange academicExchange);
	   
	void remove(AcademicExchange academicExchange);
	   
	List<AcademicExchange> findAcademicExchangeByCondition(AcademicExchangeCondition academicExchangeCondition, Page page, Order order);
	
	List<AcademicExchange> findAcademicExchangeByCondition(AcademicExchangeCondition academicExchangeCondition);
	
	List<AcademicExchange> findAcademicExchangeByCondition(AcademicExchangeCondition academicExchangeCondition, Page page);
	
	List<AcademicExchange> findAcademicExchangeByCondition(AcademicExchangeCondition academicExchangeCondition, Order order);
	
	Long count();
	
	Long count(AcademicExchangeCondition academicExchangeCondition);

	String moveTo(AcademicExchange academicExchange);

	List<AcademicExchange> findAcademicExchangeByNameAndSchool(String name,
			Integer schoolId);
	
}
