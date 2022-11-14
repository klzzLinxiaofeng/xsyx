package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.TestAdd;
import platform.education.generalTeachingAffair.vo.TestAddCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface TestAddService {
    TestAdd findTestAddById(Integer id);
	   
	TestAdd add(TestAdd testAdd);
	   
	TestAdd modify(TestAdd testAdd);
	   
	void remove(TestAdd testAdd);
	   
	List<TestAdd> findTestAddByCondition(TestAddCondition testAddCondition, Page page, Order order);
	
	List<TestAdd> findTestAddByCondition(TestAddCondition testAddCondition);
	
	List<TestAdd> findTestAddByCondition(TestAddCondition testAddCondition, Page page);
	
	List<TestAdd> findTestAddByCondition(TestAddCondition testAddCondition, Order order);
	
	Long count();
	
	Long count(TestAddCondition testAddCondition);
	
	/**
	 * @Title: solveParents 
	 * @Description: 处理思路
	 * 1.根据班级id，查询
	 * @param teamId
	 * @param schoolId
	 * @return: void
	 */
	void solveParents(Integer teamId, Integer schoolId);
	
}
