package platform.education.paper.service;
import platform.education.paper.model.UserAnswer;
import platform.education.paper.vo.UserAnswerCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface UserAnswerService {
    UserAnswer findUserAnswerById(Integer id);
	   
	UserAnswer add(UserAnswer userAnswer);
	   
	UserAnswer modify(UserAnswer userAnswer);
	   
	void remove(UserAnswer userAnswer);
	   
	List<UserAnswer> findUserAnswerByCondition(UserAnswerCondition userAnswerCondition, Page page, Order order);
	
	List<UserAnswer> findUserAnswerByCondition(UserAnswerCondition userAnswerCondition);
	
	List<UserAnswer> findUserAnswerByCondition(UserAnswerCondition userAnswerCondition, Page page);
	
	List<UserAnswer> findUserAnswerByCondition(UserAnswerCondition userAnswerCondition, Order order);
	
	Long count();
	
	Long count(UserAnswerCondition userAnswerCondition);
	
}
