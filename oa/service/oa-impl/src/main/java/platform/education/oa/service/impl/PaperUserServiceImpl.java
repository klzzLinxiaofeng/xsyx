package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.PaperUser;
import platform.education.oa.vo.PaperUserCondition;
import platform.education.oa.service.PaperUserService;
import platform.education.oa.dao.PaperUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaperUserServiceImpl implements PaperUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaperUserDao paperUserDao;

	public void setPaperUserDao(PaperUserDao paperUserDao) {
		this.paperUserDao = paperUserDao;
	}
	
	@Override
	public PaperUser findPaperUserById(Integer id) {
		try {
			return paperUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaperUser add(PaperUser paperUser) {
		if(paperUser == null) {
    		return null;
    	}
    	Date createDate = paperUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paperUser.setCreateDate(createDate);
    	paperUser.setModifyDate(createDate);
		return paperUserDao.create(paperUser);
	}

	@Override
	public PaperUser modify(PaperUser paperUser) {
		if(paperUser == null) {
    		return null;
    	}
    	Date modify = paperUser.getModifyDate();
    	paperUser.setModifyDate(modify != null ? modify : new Date());
		return paperUserDao.update(paperUser);
	}
	
	@Override
	public void remove(PaperUser paperUser) {
		try {
			paperUserDao.delete(paperUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paperUser.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaperUser> findPaperUserByCondition(PaperUserCondition paperUserCondition, Page page, Order order) {
		return paperUserDao.findPaperUserByCondition(paperUserCondition, page, order);
	}
	
	@Override
	public List<PaperUser> findPaperUserByCondition(PaperUserCondition paperUserCondition) {
		return paperUserDao.findPaperUserByCondition(paperUserCondition, null, null);
	}
	
	@Override
	public List<PaperUser> findPaperUserByCondition(PaperUserCondition paperUserCondition, Page page) {
		return paperUserDao.findPaperUserByCondition(paperUserCondition, page, null);
	}
	
	@Override
	public List<PaperUser> findPaperUserByCondition(PaperUserCondition paperUserCondition, Order order) {
		return paperUserDao.findPaperUserByCondition(paperUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paperUserDao.count(null);
	}

	@Override
	public Long count(PaperUserCondition paperUserCondition) {
		return this.paperUserDao.count(paperUserCondition);
	}

	@Override
	public PaperUser findPaperUserByPaperidAndUserid(Integer paperid,
			Integer userid) {
		 
		return this.paperUserDao.findPaperUserByPaperidAndUserid(paperid, userid);
	}

}
