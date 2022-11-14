package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Returncard;
import platform.education.oa.vo.ReturncardCondition;
import platform.education.oa.service.ReturncardService;
import platform.education.oa.dao.ReturncardDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ReturncardServiceImpl implements ReturncardService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ReturncardDao returncardDao;

	public void setReturncardDao(ReturncardDao returncardDao) {
		this.returncardDao = returncardDao;
	}
	
	@Override
	public Returncard findReturncardById(Integer id) {
		try {
			return returncardDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Returncard add(Returncard returncard) {
		if(returncard == null) {
    		return null;
    	}
    	Date createDate = returncard.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	returncard.setCreateDate(createDate);
    	returncard.setModifyDate(createDate);
		return returncardDao.create(returncard);
	}

	@Override
	public Returncard modify(Returncard returncard) {
		if(returncard == null) {
    		return null;
    	}
    	Date modify = returncard.getModifyDate();
    	returncard.setModifyDate(modify != null ? modify : new Date());
		return returncardDao.update(returncard);
	}
	
	@Override
	public void remove(Returncard returncard) {
		try {
			returncardDao.delete(returncard);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", returncard.getId(), e);
			}
		}
	}
	
	@Override
	public List<Returncard> findReturncardByCondition(ReturncardCondition returncardCondition, Page page, Order order) {
		return returncardDao.findReturncardByCondition(returncardCondition, page, order);
	}
	
	@Override
	public List<Returncard> findReturncardByCondition(ReturncardCondition returncardCondition) {
		return returncardDao.findReturncardByCondition(returncardCondition, null, null);
	}
	
	@Override
	public List<Returncard> findReturncardByCondition(ReturncardCondition returncardCondition, Page page) {
		return returncardDao.findReturncardByCondition(returncardCondition, page, null);
	}
	
	@Override
	public List<Returncard> findReturncardByCondition(ReturncardCondition returncardCondition, Order order) {
		return returncardDao.findReturncardByCondition(returncardCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.returncardDao.count(null);
	}

	@Override
	public Long count(ReturncardCondition returncardCondition) {
		return this.returncardDao.count(returncardCondition);
	}

}
