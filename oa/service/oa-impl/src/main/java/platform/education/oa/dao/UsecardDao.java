package platform.education.oa.dao;

import platform.education.oa.model.Usecard;
import platform.education.oa.vo.UsecardCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface UsecardDao extends GenericDao<Usecard, java.lang.Integer> {

	List<Usecard> findUsecardByCondition(UsecardCondition usecardCondition, Page page, Order order);
	
	Usecard findById(Integer id);
	
	Long count(UsecardCondition usecardCondition);
	
}
