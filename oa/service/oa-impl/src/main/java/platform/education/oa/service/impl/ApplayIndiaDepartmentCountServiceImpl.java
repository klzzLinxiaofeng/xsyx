package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.ApplayIndiaDepartmentCount;
import platform.education.oa.vo.ApplayIndiaDepartmentCountCondition;
import platform.education.oa.service.ApplayIndiaDepartmentCountService;
import platform.education.oa.dao.ApplayIndiaDepartmentCountDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ApplayIndiaDepartmentCountServiceImpl implements ApplayIndiaDepartmentCountService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplayIndiaDepartmentCountDao applayIndiaDepartmentCountDao;

	public void setApplayIndiaDepartmentCountDao(ApplayIndiaDepartmentCountDao applayIndiaDepartmentCountDao) {
		this.applayIndiaDepartmentCountDao = applayIndiaDepartmentCountDao;
	}
	
	@Override
	public ApplayIndiaDepartmentCount findApplayIndiaDepartmentCountById(Integer id) {
		try {
			return applayIndiaDepartmentCountDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplayIndiaDepartmentCount add(ApplayIndiaDepartmentCount applayIndiaDepartmentCount) {
		if(applayIndiaDepartmentCount == null) {
    		return null;
    	}
    	Date createDate = applayIndiaDepartmentCount.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	applayIndiaDepartmentCount.setCreateDate(createDate);
    	applayIndiaDepartmentCount.setModifyDate(createDate);
		return applayIndiaDepartmentCountDao.create(applayIndiaDepartmentCount);
	}

	@Override
	public ApplayIndiaDepartmentCount modify(ApplayIndiaDepartmentCount applayIndiaDepartmentCount) {
		if(applayIndiaDepartmentCount == null) {
    		return null;
    	}
    	Date modify = applayIndiaDepartmentCount.getModifyDate();
    	applayIndiaDepartmentCount.setModifyDate(modify != null ? modify : new Date());
		return applayIndiaDepartmentCountDao.update(applayIndiaDepartmentCount);
	}
	
	@Override
	public void remove(ApplayIndiaDepartmentCount applayIndiaDepartmentCount) {
		try {
			applayIndiaDepartmentCountDao.delete(applayIndiaDepartmentCount);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", applayIndiaDepartmentCount.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApplayIndiaDepartmentCount> findApplayIndiaDepartmentCountByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition, Page page, Order order) {
		return applayIndiaDepartmentCountDao.findApplayIndiaDepartmentCountByCondition(applayIndiaDepartmentCountCondition, page, order);
	}
	
	@Override
	public List<ApplayIndiaDepartmentCount> findApplayIndiaDepartmentCountByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition) {
		return applayIndiaDepartmentCountDao.findApplayIndiaDepartmentCountByCondition(applayIndiaDepartmentCountCondition, null, null);
	}
	
	@Override
	public List<ApplayIndiaDepartmentCount> findApplayIndiaDepartmentCountByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition, Page page) {
		return applayIndiaDepartmentCountDao.findApplayIndiaDepartmentCountByCondition(applayIndiaDepartmentCountCondition, page, null);
	}
	
	@Override
	public List<ApplayIndiaDepartmentCount> findApplayIndiaDepartmentCountByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition, Order order) {
		return applayIndiaDepartmentCountDao.findApplayIndiaDepartmentCountByCondition(applayIndiaDepartmentCountCondition, null, order);
	}
	
	@Override
	public List<ApplayIndiaDepartmentCount> findApplayIndiaSqnumByCondition(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition) {
		return applayIndiaDepartmentCountDao.findApplayIndiaSqnumByCondition(applayIndiaDepartmentCountCondition);
	}
	
	
	@Override
	public Long count() {
		return this.applayIndiaDepartmentCountDao.count(null);
	}

	@Override
	public Long count(ApplayIndiaDepartmentCountCondition applayIndiaDepartmentCountCondition) {
		return this.applayIndiaDepartmentCountDao.count(applayIndiaDepartmentCountCondition);
	}

}
