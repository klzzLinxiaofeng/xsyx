package platform.education.paper.dao;

import platform.education.paper.model.PaQuestionCatalog;
import platform.education.paper.vo.PaQuestionCatalogCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaQuestionCatalogDao extends GenericDao<PaQuestionCatalog, java.lang.Integer> {

	List<PaQuestionCatalog> findPaQuestionCatalogByCondition(PaQuestionCatalogCondition paQuestionCatalogCondition, Page page, Order order);
	
	PaQuestionCatalog findById(Integer id);
	
	Long count(PaQuestionCatalogCondition paQuestionCatalogCondition);

	void deleteByQuestionIds(Integer[] questionIds);

	List<PaQuestionCatalog> findPaQuestionCatalogByQuestionIds(Integer[] questionIds);
	
}
