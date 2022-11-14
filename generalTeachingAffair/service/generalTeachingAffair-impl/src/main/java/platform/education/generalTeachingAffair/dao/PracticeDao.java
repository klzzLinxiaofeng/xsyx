package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Practice;
import platform.education.generalTeachingAffair.vo.PracticeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PracticeDao extends GenericDao<Practice, java.lang.Integer> {

	List<Practice> findPracticeByCondition(PracticeCondition practiceCondition, Page page, Order order);
	
	Practice findById(Integer id);
	
	Long count(PracticeCondition practiceCondition);
	
}
