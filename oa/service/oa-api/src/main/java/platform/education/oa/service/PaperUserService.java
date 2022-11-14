package platform.education.oa.service;
import platform.education.oa.model.PaperUser;
import platform.education.oa.vo.PaperUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaperUserService {
    PaperUser findPaperUserById(Integer id);
	   
	PaperUser add(PaperUser paperUser);
	   
	PaperUser modify(PaperUser paperUser);
	   
	void remove(PaperUser paperUser);
	   
	List<PaperUser> findPaperUserByCondition(PaperUserCondition paperUserCondition, Page page, Order order);
	
	List<PaperUser> findPaperUserByCondition(PaperUserCondition paperUserCondition);
	
	List<PaperUser> findPaperUserByCondition(PaperUserCondition paperUserCondition, Page page);
	
	List<PaperUser> findPaperUserByCondition(PaperUserCondition paperUserCondition, Order order);
	PaperUser findPaperUserByPaperidAndUserid(Integer paperid,Integer userid);
	Long count();
	
	Long count(PaperUserCondition paperUserCondition);
	
}
