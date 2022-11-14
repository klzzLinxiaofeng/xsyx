package platform.education.paper.dao;

import platform.education.paper.model.PaPaperTree;
import platform.education.paper.vo.PaPaperTreeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaPaperTreeDao extends GenericDao<PaPaperTree, java.lang.Integer> {

	List<PaPaperTree> findPaPaperTreeByCondition(PaPaperTreeCondition paPaperTreeCondition, Page page, Order order);
	
	PaPaperTree findById(Integer id);
	
	Long count(PaPaperTreeCondition paPaperTreeCondition);

	void deleteByPaperId(Integer paperId);
	
	List<PaPaperTree> findGroupByPaperId(Integer paperId);

	List<Integer> findQuestionByGroupId(Integer id);

	List<Integer> findIdByGroupId(Integer id);
}
