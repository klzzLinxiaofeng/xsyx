package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.TestAdd;
import platform.education.generalTeachingAffair.vo.TestAddCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface TestAddDao extends GenericDao<TestAdd, java.lang.Integer> {

	List<TestAdd> findTestAddByCondition(TestAddCondition testAddCondition, Page page, Order order);
	
	TestAdd findById(Integer id);
	
	Long count(TestAddCondition testAddCondition);
	
}
