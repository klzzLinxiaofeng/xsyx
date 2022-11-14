package platform.education.paper.service;
import platform.education.paper.model.PaperQuestion;
import platform.education.paper.model.Question;
import platform.education.paper.vo.QuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    Question findQuestionById(Integer id);
	   
	Question add(Question question);
	   
	Question modify(Question question);
	   
	void remove(Question question);
	   
	List<Question> findQuestionByCondition(QuestionCondition questionCondition, Page page, Order order);
	
	List<Question> findQuestionByCondition(QuestionCondition questionCondition);
	
	List<Question> findQuestionByCondition(QuestionCondition questionCondition, Page page);
	
	List<Question> findQuestionByCondition(QuestionCondition questionCondition, Order order);
	
	Long count();
	
	Long count(QuestionCondition questionCondition);
	
	Question findQuestionByUuid(String questionUuid);
	
	List<Question> findQuestionByKnowledgeId(Integer knowledgeId);
	
	/**
	 * 根据questionuuid数组查询
	 * @param list
	 * @return
	 */
	Map<String,Question> findQuestionListByQuestionUuids(Object [] list);

	List<Question> findPaperQuestionByPaperIds(Integer[] paperIds);
	
}
