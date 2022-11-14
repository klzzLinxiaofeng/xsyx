package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ApsStuSummary;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApsStuSummaryDao extends GenericDao<ApsStuSummary, java.lang.Integer> {

	ApsStuSummary findById(Integer id);
	
//	List<ApsStuSummary> findStuSummaryOfStar(Integer taskId, Integer medalId, String termCode, String periodCode);
	List<ApsStuSummary> findStuSummaryOfStar(ApsStuSummary apsStuSummary, Page page, Order order);
}
