package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.TestAdd;
import platform.education.generalTeachingAffair.vo.TestAddCondition;
import platform.education.generalTeachingAffair.service.TestAddService;
import platform.education.generalTeachingAffair.dao.TestAddDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class TestAddServiceImpl implements TestAddService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private TestAddDao testAddDao;

	public void setTestAddDao(TestAddDao testAddDao) {
		this.testAddDao = testAddDao;
	}
	
	@Override
	public TestAdd findTestAddById(Integer id) {
		try {
			return testAddDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public TestAdd add(TestAdd testAdd) {
		if(testAdd == null) {
    		return null;
    	}
		return testAddDao.create(testAdd);
	}

	@Override
	public TestAdd modify(TestAdd testAdd) {
		if(testAdd == null) {
    		return null;
    	}
		return testAddDao.update(testAdd);
	}
	
	@Override
	public void remove(TestAdd testAdd) {
		try {
			testAddDao.delete(testAdd);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", testAdd.getId(), e);
			}
		}
	}
	
	@Override
	public List<TestAdd> findTestAddByCondition(TestAddCondition testAddCondition, Page page, Order order) {
		return testAddDao.findTestAddByCondition(testAddCondition, page, order);
	}
	
	@Override
	public List<TestAdd> findTestAddByCondition(TestAddCondition testAddCondition) {
		return testAddDao.findTestAddByCondition(testAddCondition, null, null);
	}
	
	@Override
	public List<TestAdd> findTestAddByCondition(TestAddCondition testAddCondition, Page page) {
		return testAddDao.findTestAddByCondition(testAddCondition, page, null);
	}
	
	@Override
	public List<TestAdd> findTestAddByCondition(TestAddCondition testAddCondition, Order order) {
		return testAddDao.findTestAddByCondition(testAddCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.testAddDao.count(null);
	}

	@Override
	public Long count(TestAddCondition testAddCondition) {
		return this.testAddDao.count(testAddCondition);
	}

	@Override
	public void solveParents(Integer teamId, Integer schoolId) {
		
		
		
		
		
		
		
	}

}
