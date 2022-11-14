package platform.education.paper.service;
import platform.education.paper.model.PaQuestionCatalog;
import platform.education.paper.vo.PaQuestionCatalogCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaQuestionCatalogService {
    PaQuestionCatalog findPaQuestionCatalogById(Integer id);
	   
	PaQuestionCatalog add(PaQuestionCatalog paQuestionCatalog);
	   
	PaQuestionCatalog modify(PaQuestionCatalog paQuestionCatalog);
	   
	void remove(PaQuestionCatalog paQuestionCatalog);
	   
	List<PaQuestionCatalog> findPaQuestionCatalogByCondition(PaQuestionCatalogCondition paQuestionCatalogCondition, Page page, Order order);
	
	List<PaQuestionCatalog> findPaQuestionCatalogByCondition(PaQuestionCatalogCondition paQuestionCatalogCondition);
	
	List<PaQuestionCatalog> findPaQuestionCatalogByCondition(PaQuestionCatalogCondition paQuestionCatalogCondition, Page page);
	
	List<PaQuestionCatalog> findPaQuestionCatalogByCondition(PaQuestionCatalogCondition paQuestionCatalogCondition, Order order);
	
	Long count();
	
	Long count(PaQuestionCatalogCondition paQuestionCatalogCondition);

	void removeByQuestionIds(Integer[] questionIds);

	List<PaQuestionCatalog> findPaQuestionCatalogByQuestionIds(Integer[] questionIds);

	PaQuestionCatalog findPaQuestionCatalogByQuestionId(Integer questionId);

}
