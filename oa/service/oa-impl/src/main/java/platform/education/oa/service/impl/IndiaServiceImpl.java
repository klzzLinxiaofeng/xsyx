package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.India;
import platform.education.oa.vo.IndiaCondition;
import platform.education.oa.service.IndiaService;
import platform.education.oa.dao.IndiaDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class IndiaServiceImpl implements IndiaService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private IndiaDao indiaDao;

	public void setIndiaDao(IndiaDao indiaDao) {
		this.indiaDao = indiaDao;
	}
	
	@Override
	public India findIndiaById(Integer id) {
		try {
			return indiaDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public India add(India india) {
		if(india == null) {
    		return null;
    	}
    	Date createDate = india.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	india.setCreateDate(createDate);
    	india.setModifyDate(createDate);
		return indiaDao.create(india);
	}

	@Override
	public India modify(India india) {
		if(india == null) {
    		return null;
    	}
    	Date modify = india.getModifyDate();
    	india.setModifyDate(modify != null ? modify : new Date());
		return indiaDao.update(india);
	}
	
	@Override
	public void remove(India india) {
		try {
			indiaDao.delete(india);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", india.getId(), e);
			}
		}
	}
	
	@Override
	public List<India> findIndiaByCondition(IndiaCondition indiaCondition, Page page, Order order) {
		return indiaDao.findIndiaByCondition(indiaCondition, page, order);
	}
	
	@Override
	public List<India> findIndiaByCondition(IndiaCondition indiaCondition) {
		return indiaDao.findIndiaByCondition(indiaCondition, null, null);
	}
	
	@Override
	public List<India> findIndiaByCondition(IndiaCondition indiaCondition, Page page) {
		return indiaDao.findIndiaByCondition(indiaCondition, page, null);
	}
	
	@Override
	public List<India> findIndiaByCondition(IndiaCondition indiaCondition, Order order) {
		return indiaDao.findIndiaByCondition(indiaCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.indiaDao.count(null);
	}

	@Override
	public Long count(IndiaCondition indiaCondition) {
		return this.indiaDao.count(indiaCondition);
	}

}
