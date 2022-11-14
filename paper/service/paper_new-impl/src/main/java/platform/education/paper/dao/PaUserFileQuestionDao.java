package platform.education.paper.dao;

import platform.education.paper.model.PaUserFileQuestion;
import platform.education.paper.vo.PaUserFileQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaUserFileQuestionDao extends GenericDao<PaUserFileQuestion, java.lang.Integer> {

	List<PaUserFileQuestion> findPaUserFileQuestionByCondition(PaUserFileQuestionCondition paUserFileQuestionCondition, Page page, Order order);
	
	PaUserFileQuestion findById(Integer id);
	
	Long count(PaUserFileQuestionCondition paUserFileQuestionCondition);
	
}
