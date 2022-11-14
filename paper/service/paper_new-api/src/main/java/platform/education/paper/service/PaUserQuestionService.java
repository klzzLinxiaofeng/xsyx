package platform.education.paper.service;
import platform.education.paper.model.PaUserQuestion;
import platform.education.paper.vo.PaUserQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaUserQuestionService {
    PaUserQuestion findPaUserQuestionById(Integer id);
	   
	PaUserQuestion add(PaUserQuestion paUserQuestion);
	   
	PaUserQuestion modify(PaUserQuestion paUserQuestion);
	   
	void remove(PaUserQuestion paUserQuestion);
	   
	List<PaUserQuestion> findPaUserQuestionByCondition(PaUserQuestionCondition paUserQuestionCondition, Page page, Order order);
	
	List<PaUserQuestion> findPaUserQuestionByCondition(PaUserQuestionCondition paUserQuestionCondition);
	
	List<PaUserQuestion> findPaUserQuestionByCondition(PaUserQuestionCondition paUserQuestionCondition, Page page);
	
	List<PaUserQuestion> findPaUserQuestionByCondition(PaUserQuestionCondition paUserQuestionCondition, Order order);
	
	Long count();
	
	Long count(PaUserQuestionCondition paUserQuestionCondition);
	
}
