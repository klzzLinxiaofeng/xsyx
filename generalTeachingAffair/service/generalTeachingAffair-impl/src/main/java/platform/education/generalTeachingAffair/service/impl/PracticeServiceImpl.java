package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.Practice;
import platform.education.generalTeachingAffair.vo.PracticeCondition;
import platform.education.generalTeachingAffair.service.PracticeService;
import platform.education.generalTeachingAffair.dao.PracticeDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PracticeServiceImpl implements PracticeService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PracticeDao practiceDao;

	public void setPracticeDao(PracticeDao practiceDao) {
		this.practiceDao = practiceDao;
	}
	
	@Override
	public Practice findPracticeById(Integer id) {
		try {
			return practiceDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Practice add(Practice practice) {
		if(practice == null) {
    		return null;
    	}
    	Date createDate = practice.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	practice.setCreateDate(createDate);
    	practice.setModifyDate(createDate);
		return practiceDao.create(practice);
	}

	@Override
	public Practice modify(Practice practice) {
		if(practice == null) {
    		return null;
    	}
    	Date modify = practice.getModifyDate();
    	practice.setModifyDate(modify != null ? modify : new Date());
		return practiceDao.update(practice);
	}
	
	@Override
	public void remove(Practice practice) {
		try {
			practiceDao.delete(practice);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", practice.getId(), e);
			}
		}
	}
	
	@Override
	public List<Practice> findPracticeByCondition(PracticeCondition practiceCondition, Page page, Order order) {
		return practiceDao.findPracticeByCondition(practiceCondition, page, order);
	}
	
	@Override
	public List<Practice> findPracticeByCondition(PracticeCondition practiceCondition) {
		return practiceDao.findPracticeByCondition(practiceCondition, null, null);
	}
	
	@Override
	public List<Practice> findPracticeByCondition(PracticeCondition practiceCondition, Page page) {
		return practiceDao.findPracticeByCondition(practiceCondition, page, null);
	}
	
	@Override
	public List<Practice> findPracticeByCondition(PracticeCondition practiceCondition, Order order) {
		return practiceDao.findPracticeByCondition(practiceCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.practiceDao.count(null);
	}

	@Override
	public Long count(PracticeCondition practiceCondition) {
		return this.practiceDao.count(practiceCondition);
	}

	@Override
	public String moveTo(Practice practice) {
		if(practice != null) {
			practice.setIsDelete(true);
			try {
				practice = this.practiceDao.update(practice);
				if(practice != null){
					return PracticeService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("废弃 -> {} 失败，异常信息为 {}", practice.getId(), e);
				}
				return PracticeService.OPERATE_ERROR;
			}
		}
		return PracticeService.OPERATE_FAIL;
	}

}
