package platform.education.oa.dao;

import platform.education.oa.model.India;
import platform.education.oa.vo.IndiaCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface IndiaDao extends GenericDao<India, java.lang.Integer> {

	List<India> findIndiaByCondition(IndiaCondition indiaCondition, Page page, Order order);
	
	India findById(Integer id);
	
	Long count(IndiaCondition indiaCondition);
	
}
