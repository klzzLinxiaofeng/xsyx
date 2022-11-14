package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.TextbookManager;
import platform.education.generalTeachingAffair.vo.TextbookManagerCondition;
import platform.education.generalTeachingAffair.service.TextbookManagerService;
import platform.education.generalTeachingAffair.dao.TextbookManagerDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class TextbookManagerServiceImpl implements TextbookManagerService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private TextbookManagerDao textbookManagerDao;

	public void setTextbookManagerDao(TextbookManagerDao textbookManagerDao) {
		this.textbookManagerDao = textbookManagerDao;
	}
	
	@Override
	public TextbookManager findTextbookManagerById(Integer id) {
		try {
			return textbookManagerDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public TextbookManager add(TextbookManager textbookManager) {
		if(textbookManager == null) {
    		return null;
    	}
    	Date createDate = textbookManager.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	textbookManager.setCreateDate(createDate);
    	textbookManager.setModifyTime(createDate);;
		return textbookManagerDao.create(textbookManager);
	}

	@Override
	public TextbookManager modify(TextbookManager textbookManager) {
		if(textbookManager == null) {
    		return null;
    	}
    	Date modify = textbookManager.getModifyTime();
    	textbookManager.setModifyTime(modify != null ? modify : new Date());
		return textbookManagerDao.update(textbookManager);
	}
	
	@Override
	public void remove(TextbookManager textbookManager) {
		try {
			textbookManagerDao.delete(textbookManager);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", textbookManager.getId(), e);
			}
		}
	}
	
	@Override
	public List<TextbookManager> findTextbookManagerByCondition(TextbookManagerCondition textbookManagerCondition, Page page, Order order) {
		return textbookManagerDao.findTextbookManagerByCondition(textbookManagerCondition, page, order);
	}
	
	@Override
	public List<TextbookManager> findTextbookManagerByCondition(TextbookManagerCondition textbookManagerCondition) {
		return textbookManagerDao.findTextbookManagerByCondition(textbookManagerCondition, null, null);
	}
	
	@Override
	public List<TextbookManager> findTextbookManagerByCondition(TextbookManagerCondition textbookManagerCondition, Page page) {
		return textbookManagerDao.findTextbookManagerByCondition(textbookManagerCondition, page, null);
	}
	
	@Override
	public List<TextbookManager> findTextbookManagerByCondition(TextbookManagerCondition textbookManagerCondition, Order order) {
		return textbookManagerDao.findTextbookManagerByCondition(textbookManagerCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.textbookManagerDao.count(null);
	}

	@Override
	public Long count(TextbookManagerCondition textbookManagerCondition) {
		return this.textbookManagerDao.count(textbookManagerCondition);
	}

}
