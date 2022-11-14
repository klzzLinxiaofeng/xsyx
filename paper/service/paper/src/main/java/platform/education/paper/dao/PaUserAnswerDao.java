package platform.education.paper.dao;

import platform.education.paper.model.PaUserAnswer;
import platform.education.paper.vo.PaUserAnswerCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import java.util.List;
import platform.education.paper.vo.FindDistinctAnswerCondition;
import platform.education.paper.vo.FindDistinctAnswerResult;

public interface PaUserAnswerDao extends GenericDao<PaUserAnswer, java.lang.Integer> {

	List<PaUserAnswer> findPaUserAnswerByCondition(PaUserAnswerCondition paUserAnswerCondition, Page page, Order order);
	
	PaUserAnswer findById(Integer id);
	
	Long count(PaUserAnswerCondition paUserAnswerCondition);
	
	void deleteByCondition(PaUserAnswerCondition paUserAnswerCondition);
        
        //以下是业务方法
        PaUserAnswer findByUuid(String uuid);
        
        List<FindDistinctAnswerResult> findDistinctAnswer(FindDistinctAnswerCondition ac, Page page, Order order);
        
        List<String> findDistinctQuestion(FindDistinctAnswerCondition ac, Page page, Order order);
}
