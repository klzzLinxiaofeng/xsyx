package platform.education.oa.service;
import platform.education.oa.model.SummaryReadUser;
import platform.education.oa.vo.SummaryReadUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SummaryReadUserService {
    SummaryReadUser findSummaryReadUserById(Integer id);
	   
	SummaryReadUser add(SummaryReadUser summaryReadUser);
	   
	SummaryReadUser modify(SummaryReadUser summaryReadUser);
	   
	void remove(SummaryReadUser summaryReadUser);
	   
	List<SummaryReadUser> findSummaryReadUserByCondition(SummaryReadUserCondition summaryReadUserCondition, Page page, Order order);
	
	List<SummaryReadUser> findSummaryReadUserByCondition(SummaryReadUserCondition summaryReadUserCondition);
	
	List<SummaryReadUser> findSummaryReadUserByCondition(SummaryReadUserCondition summaryReadUserCondition, Page page);
	
	List<SummaryReadUser> findSummaryReadUserByCondition(SummaryReadUserCondition summaryReadUserCondition, Order order);
	
	Long count();
	
	Long count(SummaryReadUserCondition summaryReadUserCondition);
	
	SummaryReadUser findBySummaryAndUserId(Integer sid,Integer uid);
	
}
