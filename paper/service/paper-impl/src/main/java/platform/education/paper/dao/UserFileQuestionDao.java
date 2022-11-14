package platform.education.paper.dao;

import platform.education.paper.model.UserFileQuestion;
import platform.education.paper.vo.UserFileQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface UserFileQuestionDao extends GenericDao<UserFileQuestion, java.lang.Integer> {

	List<UserFileQuestion> findUserFileQuestionByCondition(UserFileQuestionCondition userFileQuestionCondition, Page page, Order order);
	
	UserFileQuestion findById(Integer id);
	
	Long count(UserFileQuestionCondition userFileQuestionCondition);
	
	void batchUserFileQuestionAnswer(List<UserFileQuestion> userFileQuestionLis);
	
}
