package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Result;
import platform.education.generalTeachingAffair.vo.ResultCondition;
import platform.education.generalTeachingAffair.vo.ResultVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ResultDao extends GenericDao<Result, java.lang.Integer> {

	List<Result> findResultByCondition(ResultCondition resultCondition, Page page, Order order);
	
	Result findById(Integer id);
	
	Long count(ResultCondition resultCondition);
	
	List<ResultVo> findResultVoByCondition(ResultCondition resultCondition, Page page, Order order);
	
}
