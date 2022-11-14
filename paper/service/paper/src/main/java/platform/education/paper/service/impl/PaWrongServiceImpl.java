package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaWrong;
import platform.education.paper.vo.PaWrongCondition;
import platform.education.paper.service.PaWrongService;
import platform.education.paper.dao.PaWrongDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaWrongServiceImpl implements PaWrongService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaWrongDao paWrongDao;

	public void setPaWrongDao(PaWrongDao paWrongDao) {
		this.paWrongDao = paWrongDao;
	}
	
	@Override
	public PaWrong findPaWrongById(Integer id) {
		try {
			return paWrongDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaWrong add(PaWrong paWrong) {
		if(paWrong == null) {
    		return null;
    	}
    	Date createDate = paWrong.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paWrong.setCreateDate(createDate);
    	paWrong.setModifyDate(createDate);
		return paWrongDao.create(paWrong);
	}

	@Override
	public PaWrong modify(PaWrong paWrong) {
		if(paWrong == null) {
    		return null;
    	}
    	Date modify = paWrong.getModifyDate();
    	paWrong.setModifyDate(modify != null ? modify : new Date());
		return paWrongDao.update(paWrong);
	}
	
	@Override
	public void remove(PaWrong paWrong) {
		 paWrongDao.delete(paWrong);
	}
	
	@Override
	public List<PaWrong> findPaWrongByCondition(PaWrongCondition paWrongCondition, Page page, Order order) {
		return paWrongDao.findPaWrongByCondition(paWrongCondition, page, order);
	}
	
	@Override
	public List<PaWrong> findPaWrongByCondition(PaWrongCondition paWrongCondition) {
		return paWrongDao.findPaWrongByCondition(paWrongCondition, null, null);
	}
	
	@Override
	public List<PaWrong> findPaWrongByCondition(PaWrongCondition paWrongCondition, Page page) {
		return paWrongDao.findPaWrongByCondition(paWrongCondition, page, null);
	}
	
	@Override
	public List<PaWrong> findPaWrongByCondition(PaWrongCondition paWrongCondition, Order order) {
		return paWrongDao.findPaWrongByCondition(paWrongCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paWrongDao.count(null);
	}

	@Override
	public Long count(PaWrongCondition paWrongCondition) {
		return this.paWrongDao.count(paWrongCondition);
	}

	@Override
	public void remove(PaWrongCondition paWrongCondition) {
		this.paWrongDao.deleteByCondition(paWrongCondition);
	}
        
        //以下是业务方法
        @Override
	public PaWrong findPaWrongByUuid(String uuid) {
		try {
			return paWrongDao.findByUuid(uuid);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在UUID为 {} ", uuid);
		}
		return null;
	}
}
