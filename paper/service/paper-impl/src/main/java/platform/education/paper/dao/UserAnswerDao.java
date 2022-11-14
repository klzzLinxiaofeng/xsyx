package platform.education.paper.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.UserAnswer;
import platform.education.paper.vo.UserAnswerCondition;

public interface UserAnswerDao extends GenericDao<UserAnswer, java.lang.Integer> {

	List<UserAnswer> findUserAnswerByCondition(UserAnswerCondition userAnswerCondition, Page page, Order order);
	
	UserAnswer findById(Integer id);
	
	Long count(UserAnswerCondition userAnswerCondition);
	
}
