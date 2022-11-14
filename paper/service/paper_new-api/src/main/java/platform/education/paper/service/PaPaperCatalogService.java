package platform.education.paper.service;
import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.vo.AssemblySubject;
import platform.education.paper.vo.PaPaperCatalogCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaPaperCatalogService {
    PaPaperCatalog findPaPaperCatalogById(Integer id);
	   
	PaPaperCatalog add(PaPaperCatalog paPaperCatalog);
	   
	PaPaperCatalog modify(PaPaperCatalog paPaperCatalog);
	   
	void remove(PaPaperCatalog paPaperCatalog);
	   
	List<PaPaperCatalog> findPaPaperCatalogByCondition(PaPaperCatalogCondition paPaperCatalogCondition, Page page, Order order);
	
	List<PaPaperCatalog> findPaPaperCatalogByCondition(PaPaperCatalogCondition paPaperCatalogCondition);
	
	List<PaPaperCatalog> findPaPaperCatalogByCondition(PaPaperCatalogCondition paPaperCatalogCondition, Page page);
	
	List<PaPaperCatalog> findPaPaperCatalogByCondition(PaPaperCatalogCondition paPaperCatalogCondition, Order order);
	
	Long count();
	
	Long count(PaPaperCatalogCondition paPaperCatalogCondition);

	void removeByPaperId(Integer id);

	PaPaperCatalog findPaPaperCatalogByPaperId(Integer paPaperId);

	PaPaperCatalog findPaPaperCatalogByPaperIdAndSubjectCode(Integer paperId, String subjectCode);
	
}
