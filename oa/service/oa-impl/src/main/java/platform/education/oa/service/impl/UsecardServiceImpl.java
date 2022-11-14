package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Usecard;
import platform.education.oa.vo.UsecardCondition;
import platform.education.oa.service.UsecardService;
import platform.education.oa.dao.UsecardDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class UsecardServiceImpl implements UsecardService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private UsecardDao usecardDao;

	public void setUsecardDao(UsecardDao usecardDao) {
		this.usecardDao = usecardDao;
	}
	
	@Override
	public Usecard findUsecardById(Integer id) {
		try {
			return usecardDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Usecard add(Usecard usecard) {
		if(usecard == null) {
    		return null;
    	}
    	Date createDate = usecard.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	usecard.setCreateDate(createDate);
    	usecard.setModifyDate(createDate);
		return usecardDao.create(usecard);
	}

	@Override
	public Usecard modify(Usecard usecard) {
		if(usecard == null) {
    		return null;
    	}
    	Date modify = usecard.getModifyDate();
    	usecard.setModifyDate(modify != null ? modify : new Date());
		return usecardDao.update(usecard);
	}
	
	@Override
	public void remove(Usecard usecard) {
		try {
			usecardDao.delete(usecard);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", usecard.getId(), e);
			}
		}
	}
	
	@Override
	public List<Usecard> findUsecardByCondition(UsecardCondition usecardCondition, Page page, Order order) {
		return usecardDao.findUsecardByCondition(usecardCondition, page, order);
	}
	
	@Override
	public List<Usecard> findUsecardByCondition(UsecardCondition usecardCondition) {
		return usecardDao.findUsecardByCondition(usecardCondition, null, null);
	}
	
	@Override
	public List<Usecard> findUsecardByCondition(UsecardCondition usecardCondition, Page page) {
		return usecardDao.findUsecardByCondition(usecardCondition, page, null);
	}
	
	@Override
	public List<Usecard> findUsecardByCondition(UsecardCondition usecardCondition, Order order) {
		return usecardDao.findUsecardByCondition(usecardCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.usecardDao.count(null);
	}

	@Override
	public Long count(UsecardCondition usecardCondition) {
		return this.usecardDao.count(usecardCondition);
	}

}
