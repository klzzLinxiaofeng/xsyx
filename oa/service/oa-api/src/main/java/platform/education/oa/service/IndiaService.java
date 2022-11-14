package platform.education.oa.service;
import platform.education.oa.model.India;
import platform.education.oa.vo.IndiaCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface IndiaService {
    India findIndiaById(Integer id);
	   
	India add(India india);
	   
	India modify(India india);
	   
	void remove(India india);
	   
	List<India> findIndiaByCondition(IndiaCondition indiaCondition, Page page, Order order);
	
	List<India> findIndiaByCondition(IndiaCondition indiaCondition);
	
	List<India> findIndiaByCondition(IndiaCondition indiaCondition, Page page);
	
	List<India> findIndiaByCondition(IndiaCondition indiaCondition, Order order);
	
	Long count();
	
	Long count(IndiaCondition indiaCondition);
	
}
