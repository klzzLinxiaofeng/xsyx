package platform.education.paper.service;
import platform.education.paper.model.UserFileQuestion;
import platform.education.paper.vo.UserFileQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface UserFileQuestionService {
    UserFileQuestion findUserFileQuestionById(Integer id);
	   
	UserFileQuestion add(UserFileQuestion userFileQuestion);
	   
	UserFileQuestion modify(UserFileQuestion userFileQuestion);
	   
	void remove(UserFileQuestion userFileQuestion);
	   
	List<UserFileQuestion> findUserFileQuestionByCondition(UserFileQuestionCondition userFileQuestionCondition, Page page, Order order);
	
	List<UserFileQuestion> findUserFileQuestionByCondition(UserFileQuestionCondition userFileQuestionCondition);
	
	List<UserFileQuestion> findUserFileQuestionByCondition(UserFileQuestionCondition userFileQuestionCondition, Page page);
	
	List<UserFileQuestion> findUserFileQuestionByCondition(UserFileQuestionCondition userFileQuestionCondition, Order order);
	
	Long count();
	
	Long count(UserFileQuestionCondition userFileQuestionCondition);
	
	void batchUserFileQuestionAnswer(List<UserFileQuestion> userFileQuestionLis);
	
}
