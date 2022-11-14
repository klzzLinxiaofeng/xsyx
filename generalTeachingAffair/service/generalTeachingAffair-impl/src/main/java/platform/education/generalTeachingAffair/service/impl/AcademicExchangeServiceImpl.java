package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.AcademicExchangeDao;
import platform.education.generalTeachingAffair.model.AcademicExchange;
import platform.education.generalTeachingAffair.service.AcademicExchangeService;
import platform.education.generalTeachingAffair.vo.AcademicExchangeCondition;

public class AcademicExchangeServiceImpl implements AcademicExchangeService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private AcademicExchangeDao academicExchangeDao;

	public void setAcademicExchangeDao(AcademicExchangeDao academicExchangeDao) {
		this.academicExchangeDao = academicExchangeDao;
	}
	
	@Override
	public AcademicExchange findAcademicExchangeById(Integer id) {
		try {
			return academicExchangeDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public AcademicExchange add(AcademicExchange academicExchange) {
		if(academicExchange == null) {
    		return null;
    	}
    	Date createDate = academicExchange.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	academicExchange.setCreateDate(createDate);
    	academicExchange.setModifyDate(createDate);
		return academicExchangeDao.create(academicExchange);
	}

	@Override
	public AcademicExchange modify(AcademicExchange academicExchange) {
		if(academicExchange == null) {
    		return null;
    	}
    	Date modify = academicExchange.getModifyDate();
    	academicExchange.setModifyDate(modify != null ? modify : new Date());
		return academicExchangeDao.update(academicExchange);
	}
	
	@Override
	public void remove(AcademicExchange academicExchange) {
		try {
			academicExchangeDao.delete(academicExchange);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", academicExchange.getId(), e);
			}
		}
	}
	
	@Override
	public List<AcademicExchange> findAcademicExchangeByCondition(AcademicExchangeCondition academicExchangeCondition, Page page, Order order) {
		return academicExchangeDao.findAcademicExchangeByCondition(academicExchangeCondition, page, order);
	}
	
	@Override
	public List<AcademicExchange> findAcademicExchangeByCondition(AcademicExchangeCondition academicExchangeCondition) {
		return academicExchangeDao.findAcademicExchangeByCondition(academicExchangeCondition, null, null);
	}
	
	@Override
	public List<AcademicExchange> findAcademicExchangeByCondition(AcademicExchangeCondition academicExchangeCondition, Page page) {
		return academicExchangeDao.findAcademicExchangeByCondition(academicExchangeCondition, page, null);
	}
	
	@Override
	public List<AcademicExchange> findAcademicExchangeByCondition(AcademicExchangeCondition academicExchangeCondition, Order order) {
		return academicExchangeDao.findAcademicExchangeByCondition(academicExchangeCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.academicExchangeDao.count(null);
	}

	@Override
	public Long count(AcademicExchangeCondition academicExchangeCondition) {
		return this.academicExchangeDao.count(academicExchangeCondition);
	}

	@Override
	public String moveTo(AcademicExchange academicExchange) {
		if(academicExchange != null) {
			academicExchange.setIsDelete(true);
			try {
				academicExchange = this.academicExchangeDao.update(academicExchange);
				if(academicExchange != null){
					return AcademicExchangeService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("废弃 -> {} 失败，异常信息为 {}", academicExchange.getId(), e);
				}
				return AcademicExchangeService.OPERATE_ERROR;
			}
		}
		return AcademicExchangeService.OPERATE_FAIL;
	}

	@Override
	public List<AcademicExchange> findAcademicExchangeByNameAndSchool(
			String name, Integer schoolId) {
		// TODO Auto-generated method stub
		return academicExchangeDao.findAcademicExchangeByNameAndSchool(name,schoolId);
	}

}
