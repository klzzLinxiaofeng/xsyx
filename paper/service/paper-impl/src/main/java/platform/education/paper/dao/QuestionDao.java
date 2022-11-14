package platform.education.paper.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.PaperQuestion;
import platform.education.paper.model.Question;
import platform.education.paper.vo.QuestionCondition;

public interface QuestionDao extends GenericDao<Question, java.lang.Integer> {

	List<Question> findQuestionByCondition(QuestionCondition questionCondition, Page page, Order order);
	
	Question findById(Integer id);
	
	Long count(QuestionCondition questionCondition);
	
	Question findQuestionByUuid(String questionUuid);
	
	List<Question> findQuestionByKnowledgeId(Integer knowledgeId);
	
	/**
	 * 根据questionuuid数组查询
	 * @param list
	 * @return
	 */
	List<Question> findQuestionListByQuestionUuids(Object [] list);

	List<Question> findPaperQuestionByPaperIds(Integer[] paperIds);
	
}
