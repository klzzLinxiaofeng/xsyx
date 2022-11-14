package platform.education.paper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.dao.QuestionDao;
import platform.education.paper.model.PaperQuestion;
import platform.education.paper.model.Question;
import platform.education.paper.service.QuestionService;
import platform.education.paper.vo.QuestionCondition;

public class QuestionServiceImpl implements QuestionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private QuestionDao questionDao;

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	
	@Override
	public Question findQuestionById(Integer id) {
		try {
			return questionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Question add(Question question) {
		if(question == null) {
    		return null;
    	}
    	Date createDate = question.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	question.setCreateDate(createDate);
    	question.setModifyDate(createDate);
		return questionDao.create(question);
	}

	@Override
	public Question modify(Question question) {
		if(question == null) {
    		return null;
    	}
    	Date modify = question.getModifyDate();
    	question.setModifyDate(modify != null ? modify : new Date());
		return questionDao.update(question);
	}
	
	@Override
	public void remove(Question question) {
		try {
			questionDao.delete(question);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", question.getId(), e);
			}
		}
	}

	@Override
	public List<Question> findPaperQuestionByPaperIds(Integer[] paperIds) {
		if(paperIds==null || paperIds.length==0) {
			return null;
		}
		return questionDao.findPaperQuestionByPaperIds(paperIds);
	}
	
	@Override
	public List<Question> findQuestionByCondition(QuestionCondition questionCondition, Page page, Order order) {
		return questionDao.findQuestionByCondition(questionCondition, page, order);
	}
	
	@Override
	public List<Question> findQuestionByCondition(QuestionCondition questionCondition) {
		return questionDao.findQuestionByCondition(questionCondition, null, null);
	}
	
	@Override
	public List<Question> findQuestionByCondition(QuestionCondition questionCondition, Page page) {
		return questionDao.findQuestionByCondition(questionCondition, page, null);
	}
	
	@Override
	public List<Question> findQuestionByCondition(QuestionCondition questionCondition, Order order) {
		return questionDao.findQuestionByCondition(questionCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.questionDao.count(null);
	}

	@Override
	public Long count(QuestionCondition questionCondition) {
		return this.questionDao.count(questionCondition);
	}

	/* (非 Javadoc) 
	* <p>Title: findQuestionByUuid</p> 
	* <p>Description: </p> 
	* @param questionUuid
	* @return 
	* @see platform.education.paper.service.QuestionService#findQuestionByUuid(java.lang.String) 
	*/
	@Override
	public Question findQuestionByUuid(String questionUuid) {
		
		return questionDao.findQuestionByUuid(questionUuid);
	}

	@Override
	public List<Question> findQuestionByKnowledgeId(Integer knowledgeId) {
		return questionDao.findQuestionByKnowledgeId(knowledgeId);
	}

	@Override
	public Map<String,Question> findQuestionListByQuestionUuids(Object[] list) {
		Map<String,Question> map = new HashMap<String,Question>();;
		List<Question> questionList = questionDao.findQuestionListByQuestionUuids(list);
		if(questionList != null && questionList.size() > 0){
			for(Question question :questionList){
				map.put(question.getQuestionUuid(), question);
			}
		}
		return map;
	}

}
