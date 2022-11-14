package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.ApplayIndia;
import platform.education.oa.vo.ApplayIndiaCondition;
import platform.education.oa.vo.ApplayIndiaVo;
import platform.education.oa.service.ApplayIndiaService;
import platform.education.oa.dao.ApplayIndiaDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ApplayIndiaServiceImpl implements ApplayIndiaService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplayIndiaDao applayIndiaDao;

	public void setApplayIndiaDao(ApplayIndiaDao applayIndiaDao) {
		this.applayIndiaDao = applayIndiaDao;
	}
	
	@Override
	public ApplayIndia findApplayIndiaById(Integer id) {
		try {
			return applayIndiaDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplayIndiaVo findApplayIndiaById1(Integer id) {
		try {
			return applayIndiaDao.findById1(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplayIndia add(ApplayIndia applayIndia) {
		if(applayIndia == null) {
    		return null;
    	}
    	Date createDate = applayIndia.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	applayIndia.setCreateDate(createDate);
    	applayIndia.setModifyDate(createDate);
		return applayIndiaDao.create(applayIndia);
	}

	@Override
	public ApplayIndia modify(ApplayIndia applayIndia) {
		if(applayIndia == null) {
    		return null;
    	}
    	Date modify = applayIndia.getModifyDate();
    	applayIndia.setModifyDate(modify != null ? modify : new Date());
		return applayIndiaDao.update(applayIndia);
	}
	
	@Override
	public void remove(ApplayIndia applayIndia) {
		try {
			applayIndiaDao.delete(applayIndia);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", applayIndia.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApplayIndia> findApplayIndiaByCondition(ApplayIndiaCondition applayIndiaCondition, Page page, Order order) {
		return applayIndiaDao.findApplayIndiaByCondition(applayIndiaCondition, page, order);
	}
	
	@Override
	public List<ApplayIndiaVo> findApplayIndiaByCondition1(ApplayIndiaCondition applayIndiaCondition, Page page, Order order) {
		return applayIndiaDao.findApplayIndiaByCondition1(applayIndiaCondition, page, order);
	}
	
	@Override
	public List<ApplayIndia> findApplayIndiaByCondition(ApplayIndiaCondition applayIndiaCondition) {
		return applayIndiaDao.findApplayIndiaByCondition(applayIndiaCondition, null, null);
	}
	
	@Override
	public List<ApplayIndia> findApplayIndiaByCondition(ApplayIndiaCondition applayIndiaCondition, Page page) {
		return applayIndiaDao.findApplayIndiaByCondition(applayIndiaCondition, page, null);
	}
	
	@Override
	public List<ApplayIndia> findApplayIndiaByCondition(ApplayIndiaCondition applayIndiaCondition, Order order) {
		return applayIndiaDao.findApplayIndiaByCondition(applayIndiaCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.applayIndiaDao.count(null);
	}

	@Override
	public Long count(ApplayIndiaCondition applayIndiaCondition) {
		return this.applayIndiaDao.count(applayIndiaCondition);
	}

	@Override
	public void abandon(ApplayIndia applayIndia) {
		if(applayIndia!=null){
			applayIndia.setIsDeleted(true);
			try {
				applayIndia = this.applayIndiaDao.update(applayIndia);
			} catch (Exception e) {
				if(log.isInfoEnabled()){
					log.info("废弃 -> {} 失败，异常信息为 {}", applayIndia.getId(), e);	
				}
				
			}
		}
		
	}
	
	
}
