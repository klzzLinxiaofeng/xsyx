package platform.education.oa.service;
import platform.education.oa.model.Returncard;
import platform.education.oa.vo.ReturncardCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ReturncardService {
    Returncard findReturncardById(Integer id);
	   
	Returncard add(Returncard returncard);
	   
	Returncard modify(Returncard returncard);
	   
	void remove(Returncard returncard);
	   
	List<Returncard> findReturncardByCondition(ReturncardCondition returncardCondition, Page page, Order order);
	
	List<Returncard> findReturncardByCondition(ReturncardCondition returncardCondition);
	
	List<Returncard> findReturncardByCondition(ReturncardCondition returncardCondition, Page page);
	
	List<Returncard> findReturncardByCondition(ReturncardCondition returncardCondition, Order order);
	
	Long count();
	
	Long count(ReturncardCondition returncardCondition);
	
}
