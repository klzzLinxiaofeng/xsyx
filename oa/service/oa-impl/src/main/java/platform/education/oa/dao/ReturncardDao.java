package platform.education.oa.dao;

import platform.education.oa.model.Returncard;
import platform.education.oa.vo.ReturncardCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ReturncardDao extends GenericDao<Returncard, java.lang.Integer> {

	List<Returncard> findReturncardByCondition(ReturncardCondition returncardCondition, Page page, Order order);
	
	Returncard findById(Integer id);
	
	Long count(ReturncardCondition returncardCondition);
	
}
