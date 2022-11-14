package platform.education.paper.dao;

import platform.education.paper.model.PaUserQuestion;
import platform.education.paper.vo.PaUserQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaUserQuestionDao extends GenericDao<PaUserQuestion, java.lang.Integer> {

	List<PaUserQuestion> findPaUserQuestionByCondition(PaUserQuestionCondition paUserQuestionCondition, Page page, Order order);
	
	PaUserQuestion findById(Integer id);
	
	Long count(PaUserQuestionCondition paUserQuestionCondition);
	
}
