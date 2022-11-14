package platform.education.oa.dao;

import platform.education.oa.model.SummaryReadUser;
import platform.education.oa.vo.SummaryReadUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SummaryReadUserDao extends GenericDao<SummaryReadUser, java.lang.Integer> {

	List<SummaryReadUser> findSummaryReadUserByCondition(SummaryReadUserCondition summaryReadUserCondition, Page page, Order order);
	
	SummaryReadUser findById(Integer id);
	
	Long count(SummaryReadUserCondition summaryReadUserCondition);
	SummaryReadUser findBySummaryAndUserId(Integer sid,Integer uid);
	
}
