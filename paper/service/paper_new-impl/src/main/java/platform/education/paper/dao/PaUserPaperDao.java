package platform.education.paper.dao;

import platform.education.paper.model.PaUserPaper;
import platform.education.paper.vo.PaUserPaperCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaUserPaperDao extends GenericDao<PaUserPaper, java.lang.Integer> {

	List<PaUserPaper> findPaUserPaperByCondition(PaUserPaperCondition paUserPaperCondition, Page page, Order order);
	
	PaUserPaper findById(Integer id);
	
	Long count(PaUserPaperCondition paUserPaperCondition);
	
}
