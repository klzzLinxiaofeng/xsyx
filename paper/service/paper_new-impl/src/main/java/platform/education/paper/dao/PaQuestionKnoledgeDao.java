package platform.education.paper.dao;

import platform.education.paper.model.PaQuestionKnoledge;
import platform.education.paper.vo.PaQuestionKnoledgeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaQuestionKnoledgeDao extends GenericDao<PaQuestionKnoledge, java.lang.Integer> {

	List<PaQuestionKnoledge> findPaQuestionKnoledgeByCondition(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition, Page page, Order order);
	
	PaQuestionKnoledge findById(Integer id);
	
	Long count(PaQuestionKnoledgeCondition paQuestionKnoledgeCondition);

	void deleteByQuestionIds(Integer[] questionIds);
	
	public List<PaQuestionKnoledge> findByQuestionIds(Integer[] questions);
}
