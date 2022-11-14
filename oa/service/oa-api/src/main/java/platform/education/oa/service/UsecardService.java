package platform.education.oa.service;
import platform.education.oa.model.Usecard;
import platform.education.oa.vo.UsecardCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface UsecardService {
    Usecard findUsecardById(Integer id);
	   
	Usecard add(Usecard usecard);
	   
	Usecard modify(Usecard usecard);
	   
	void remove(Usecard usecard);
	   
	List<Usecard> findUsecardByCondition(UsecardCondition usecardCondition, Page page, Order order);
	
	List<Usecard> findUsecardByCondition(UsecardCondition usecardCondition);
	
	List<Usecard> findUsecardByCondition(UsecardCondition usecardCondition, Page page);
	
	List<Usecard> findUsecardByCondition(UsecardCondition usecardCondition, Order order);
	
	Long count();
	
	Long count(UsecardCondition usecardCondition);
	
}
