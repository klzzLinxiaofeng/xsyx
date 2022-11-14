package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.AcceptRepari;
import platform.education.oa.vo.AcceptRepariCondition;
import platform.education.oa.vo.AcceptRepariVo;
import platform.education.oa.service.AcceptRepariService;
import platform.education.oa.dao.AcceptRepariDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class AcceptRepariServiceImpl implements AcceptRepariService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private AcceptRepariDao acceptRepariDao;

	public void setAcceptRepariDao(AcceptRepariDao acceptRepariDao) {
		this.acceptRepariDao = acceptRepariDao;
	}
	
	@Override
	public AcceptRepari findAcceptRepariById(Integer id) {
		try {
			return acceptRepariDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public AcceptRepari add(AcceptRepari acceptRepari) {
		if(acceptRepari == null) {
    		return null;
    	}
    	Date createDate = acceptRepari.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	acceptRepari.setCreateDate(createDate);
    	acceptRepari.setModifyDate(createDate);
		return acceptRepariDao.create(acceptRepari);
	}

	@Override
	public AcceptRepari modify(AcceptRepari acceptRepari) {
		if(acceptRepari == null) {
    		return null;
    	}
    	Date modify = acceptRepari.getModifyDate();
    	acceptRepari.setModifyDate(modify != null ? modify : new Date());
		return acceptRepariDao.update(acceptRepari);
	}
	
	@Override
	public void remove(AcceptRepari acceptRepari) {
		try {
			acceptRepariDao.delete(acceptRepari);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", acceptRepari.getId(), e);
			}
		}
	}
	
	@Override
	public List<AcceptRepari> findAcceptRepariByCondition(AcceptRepariCondition acceptRepariCondition, Page page, Order order) {
		return acceptRepariDao.findAcceptRepariByCondition(acceptRepariCondition, page, order);
	}
	
	@Override
	public List<AcceptRepari> findAcceptRepariByCondition(AcceptRepariCondition acceptRepariCondition) {
		return acceptRepariDao.findAcceptRepariByCondition(acceptRepariCondition, null, null);
	}
	
	@Override
	public List<AcceptRepari> findAcceptRepariByCondition(AcceptRepariCondition acceptRepariCondition, Page page) {
		return acceptRepariDao.findAcceptRepariByCondition(acceptRepariCondition, page, null);
	}
	
	@Override
	public List<AcceptRepari> findAcceptRepariByCondition(AcceptRepariCondition acceptRepariCondition, Order order) {
		return acceptRepariDao.findAcceptRepariByCondition(acceptRepariCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.acceptRepariDao.count(null);
	}

	@Override
	public Long count(AcceptRepariCondition acceptRepariCondition) {
		return this.acceptRepariDao.count(acceptRepariCondition);
	}

	@Override
	public List<AcceptRepariVo> findAcceptRepariByConditionAndGroup(
			AcceptRepariCondition acceptRepariCondition) {
		return acceptRepariDao.findAcceptRepariByConditionAndGroup(acceptRepariCondition);
	}

}
