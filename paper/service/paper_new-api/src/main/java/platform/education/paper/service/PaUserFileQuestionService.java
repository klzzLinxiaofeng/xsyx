package platform.education.paper.service;
import platform.education.paper.model.PaUserFileQuestion;
import platform.education.paper.vo.PaUserFileQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaUserFileQuestionService {
    PaUserFileQuestion findPaUserFileQuestionById(Integer id);
	   
	PaUserFileQuestion add(PaUserFileQuestion paUserFileQuestion);
	   
	PaUserFileQuestion modify(PaUserFileQuestion paUserFileQuestion);
	   
	void remove(PaUserFileQuestion paUserFileQuestion);
	   
	List<PaUserFileQuestion> findPaUserFileQuestionByCondition(PaUserFileQuestionCondition paUserFileQuestionCondition, Page page, Order order);
	
	List<PaUserFileQuestion> findPaUserFileQuestionByCondition(PaUserFileQuestionCondition paUserFileQuestionCondition);
	
	List<PaUserFileQuestion> findPaUserFileQuestionByCondition(PaUserFileQuestionCondition paUserFileQuestionCondition, Page page);
	
	List<PaUserFileQuestion> findPaUserFileQuestionByCondition(PaUserFileQuestionCondition paUserFileQuestionCondition, Order order);
	
	Long count();
	
	Long count(PaUserFileQuestionCondition paUserFileQuestionCondition);
	
}
