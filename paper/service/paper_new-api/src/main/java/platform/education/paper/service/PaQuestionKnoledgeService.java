package platform.education.paper.service;
import platform.education.paper.model.PaQuestionKnoledge;
import platform.education.paper.vo.PaQuestionKnoledgeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaQuestionKnoledgeService {
    PaQuestionKnoledge findPaQuestionKnoledgeById(Integer id);
	   
	PaQuestionKnoledge add(PaQuestionKnoledge paQuestionKnoledge);
	   
	PaQuestionKnoledge modify(PaQuestionKnoledge paQuestionKnoledge);
	   
	void remove(PaQuestionKnoledge paQuestionKnoledge);
	   
	List<PaQuestionKnoledge> findPaQuestionKnoledgeByCondition(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition, Page page, Order order);
	
	List<PaQuestionKnoledge> findPaQuestionKnoledgeByCondition(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition);
	
	List<PaQuestionKnoledge> findPaQuestionKnoledgeByCondition(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition, Page page);
	
	List<PaQuestionKnoledge> findPaQuestionKnoledgeByCondition(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition, Order order);
	
	Long count();
	
	Long count(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition);

	void removeByQuestionIds(Integer[] questionIds);

	List<PaQuestionKnoledge> findByQuestionId(Integer question);
	
	List<PaQuestionKnoledge> findByQuestionIds(Integer[] questions);
	
	
}
