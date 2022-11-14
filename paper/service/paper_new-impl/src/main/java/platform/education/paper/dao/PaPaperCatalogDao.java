package platform.education.paper.dao;

import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.vo.PaPaperCatalogCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaPaperCatalogDao extends GenericDao<PaPaperCatalog, java.lang.Integer> {

	List<PaPaperCatalog> findPaPaperCatalogByCondition(PaPaperCatalogCondition paPaperCatalogCondition, Page page, Order order);
	
	PaPaperCatalog findById(Integer id);
	
	Long count(PaPaperCatalogCondition paPaperCatalogCondition);

	void deleteByPaperId(Integer paperId);
	
}
