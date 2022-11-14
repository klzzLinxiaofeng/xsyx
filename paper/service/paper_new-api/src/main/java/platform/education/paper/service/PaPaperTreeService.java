package platform.education.paper.service;
import platform.education.paper.model.PaPaperTree;
import platform.education.paper.vo.BasketGroupJson;
import platform.education.paper.vo.PaPaperTreeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaPaperTreeService {
    PaPaperTree findPaPaperTreeById(Integer id);
	   
	PaPaperTree add(PaPaperTree paPaperTree);
	   
	PaPaperTree modify(PaPaperTree paPaperTree);
	   
	void remove(PaPaperTree paPaperTree);
	   
	List<PaPaperTree> findPaPaperTreeByCondition(PaPaperTreeCondition paPaperTreeCondition, Page page, Order order);
	
	List<PaPaperTree> findPaPaperTreeByCondition(PaPaperTreeCondition paPaperTreeCondition);
	
	List<PaPaperTree> findPaPaperTreeByCondition(PaPaperTreeCondition paPaperTreeCondition, Page page);
	
	List<PaPaperTree> findPaPaperTreeByCondition(PaPaperTreeCondition paPaperTreeCondition, Order order);
	
	Long count();
	
	Long count(PaPaperTreeCondition paPaperTreeCondition);

	void removeByPaperId(Integer paperId);

	List<PaPaperTree> findPaPaperTreeByPaperIdAndType(Integer paperId, Integer nodeType);
	
	String findBasketJsonByPaperId(Integer paperId);
}
