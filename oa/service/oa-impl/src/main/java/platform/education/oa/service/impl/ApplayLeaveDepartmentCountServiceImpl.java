package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.ApplayLeaveDepartmentCount;
import platform.education.oa.vo.ApplayLeaveDepartmentCountCondition;
import platform.education.oa.service.ApplayLeaveDepartmentCountService;
import platform.education.oa.dao.ApplayLeaveDepartmentCountDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ApplayLeaveDepartmentCountServiceImpl implements ApplayLeaveDepartmentCountService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplayLeaveDepartmentCountDao applayLeaveDepartmentCountDao;

	public void setApplayLeaveDepartmentCountDao(ApplayLeaveDepartmentCountDao applayLeaveDepartmentCountDao) {
		this.applayLeaveDepartmentCountDao = applayLeaveDepartmentCountDao;
	}
	
	@Override
	public ApplayLeaveDepartmentCount findApplayLeaveDepartmentCountById(Integer id) {
		try {
			return applayLeaveDepartmentCountDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplayLeaveDepartmentCount add(ApplayLeaveDepartmentCount applayLeaveDepartmentCount) {
		if(applayLeaveDepartmentCount == null) {
    		return null;
    	}
    	Date createDate = applayLeaveDepartmentCount.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	applayLeaveDepartmentCount.setCreateDate(createDate);
    	applayLeaveDepartmentCount.setModifyDate(createDate);
		return applayLeaveDepartmentCountDao.create(applayLeaveDepartmentCount);
	}

	@Override
	public ApplayLeaveDepartmentCount modify(ApplayLeaveDepartmentCount applayLeaveDepartmentCount) {
		if(applayLeaveDepartmentCount == null) {
    		return null;
    	}
    	Date modify = applayLeaveDepartmentCount.getModifyDate();
    	applayLeaveDepartmentCount.setModifyDate(modify != null ? modify : new Date());
		return applayLeaveDepartmentCountDao.update(applayLeaveDepartmentCount);
	}
	
	@Override
	public void remove(ApplayLeaveDepartmentCount applayLeaveDepartmentCount) {
		try {
			applayLeaveDepartmentCountDao.delete(applayLeaveDepartmentCount);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", applayLeaveDepartmentCount.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApplayLeaveDepartmentCount> findApplayLeaveDepartmentCountByCondition(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition, Page page, Order order) {
		return applayLeaveDepartmentCountDao.findApplayLeaveDepartmentCountByCondition(applayLeaveDepartmentCountCondition, page, order);
	}
	
	@Override
	public List<ApplayLeaveDepartmentCount> findApplayLeaveDepartmentCountByCondition(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition) {
		return applayLeaveDepartmentCountDao.findApplayLeaveDepartmentCountByCondition(applayLeaveDepartmentCountCondition, null, null);
	}
	
	@Override
	public List<ApplayLeaveDepartmentCount> findApplayLeaveDepartmentCountByCondition(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition, Page page) {
		return applayLeaveDepartmentCountDao.findApplayLeaveDepartmentCountByCondition(applayLeaveDepartmentCountCondition, page, null);
	}
	
	@Override
	public List<ApplayLeaveDepartmentCount> findApplayLeaveDepartmentCountByCondition(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition, Order order) {
		return applayLeaveDepartmentCountDao.findApplayLeaveDepartmentCountByCondition(applayLeaveDepartmentCountCondition, null, order);
	}
	
	
	
	@Override
	public Long count() {
		return this.applayLeaveDepartmentCountDao.count(null);
	}

	@Override
	public Long count(ApplayLeaveDepartmentCountCondition applayLeaveDepartmentCountCondition) {
		return this.applayLeaveDepartmentCountDao.count(applayLeaveDepartmentCountCondition);
	}

	@Override
	public List<ApplayLeaveDepartmentCount> findApplayLeaveSqnumByCondition(
			ApplayLeaveDepartmentCountCondition sqCondition) {
		return this.applayLeaveDepartmentCountDao.findApplayLeaveSqnumByCondition(sqCondition);
	}

}
