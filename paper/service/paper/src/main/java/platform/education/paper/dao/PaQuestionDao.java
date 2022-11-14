package platform.education.paper.dao;

import platform.education.paper.model.PaQuestion;
import platform.education.paper.vo.PaQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import java.util.List;

public interface PaQuestionDao extends GenericDao<PaQuestion,  java.lang.Integer> {

	List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page, Order order);
	
	PaQuestion findById(Integer id);
	
	Long count(PaQuestionCondition paQuestionCondition);
	
	void deleteByCondition(PaQuestionCondition paQuestionCondition);
        
        //以下为业务方法
        PaQuestion findByUuid(String uuid);
}
