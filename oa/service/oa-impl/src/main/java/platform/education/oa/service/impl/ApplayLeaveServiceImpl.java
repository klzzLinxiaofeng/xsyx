package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.ApplayLeave;
import platform.education.oa.vo.ApplayLeaveCondition;
import platform.education.oa.vo.ApplayLeaveVo;
import platform.education.oa.service.ApplayLeaveService;
import platform.education.oa.dao.ApplayLeaveDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ApplayLeaveServiceImpl implements ApplayLeaveService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplayLeaveDao applayLeaveDao;

	public void setApplayLeaveDao(ApplayLeaveDao applayLeaveDao) {
		this.applayLeaveDao = applayLeaveDao;
	}
	
	@Override
	public ApplayLeave findApplayLeaveById(Integer id) {
		try {
			return applayLeaveDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplayLeaveVo findApplayLeaveVoById(Integer id) {
		try {
			return applayLeaveDao.findByVoId(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplayLeave add(ApplayLeave applayLeave) {
		if(applayLeave == null) {
    		return null;
    	}
    	Date createDate = applayLeave.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	
    	applayLeave.setPublishDate(createDate);
    	applayLeave.setCreateDate(createDate);
    	applayLeave.setModifyDate(createDate);
    	
		return applayLeaveDao.create(applayLeave);
	}

	@Override
	public ApplayLeave modify(ApplayLeave applayLeave) {
		if(applayLeave == null) {
    		return null;
    	}
    	Date modify = applayLeave.getModifyDate();
    	applayLeave.setModifyDate(modify != null ? modify : new Date());
		return applayLeaveDao.update(applayLeave);
	}
	
	@Override
	public void remove(ApplayLeave applayLeave) {
		try {
			applayLeaveDao.delete(applayLeave);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", applayLeave.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApplayLeave> findApplayLeaveByCondition(ApplayLeaveCondition applayLeaveCondition, Page page, Order order) {
		return applayLeaveDao.findApplayLeaveByCondition(applayLeaveCondition, page, order);
	}
	
	@Override
	public List<ApplayLeaveVo> findApplayLeaveVoByCondition(ApplayLeaveCondition applayLeaveCondition, Page page, Order order) {
		return applayLeaveDao.findApplayLeaveVoByCondition(applayLeaveCondition, page, order);
	}
	
	@Override
	public List<ApplayLeaveVo> findApplayLeaveTJByCondition(ApplayLeaveCondition applayLeaveCondition, Page page, Order order) {
		return applayLeaveDao.findApplayLeaveTJByCondition(applayLeaveCondition, page, order);
	}
	
	@Override
	public List<ApplayLeave> findApplayLeaveByCondition(ApplayLeaveCondition applayLeaveCondition) {
		return applayLeaveDao.findApplayLeaveByCondition(applayLeaveCondition, null, null);
	}
	
	@Override
	public List<ApplayLeave> findApplayLeaveByCondition(ApplayLeaveCondition applayLeaveCondition, Page page) {
		return applayLeaveDao.findApplayLeaveByCondition(applayLeaveCondition, page, null);
	}
	
	@Override
	public List<ApplayLeave> findApplayLeaveByCondition(ApplayLeaveCondition applayLeaveCondition, Order order) {
		return applayLeaveDao.findApplayLeaveByCondition(applayLeaveCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.applayLeaveDao.count(null);
	}

	@Override
	public Long count(ApplayLeaveCondition applayLeaveCondition) {
		return this.applayLeaveDao.count(applayLeaveCondition);
	}

}
