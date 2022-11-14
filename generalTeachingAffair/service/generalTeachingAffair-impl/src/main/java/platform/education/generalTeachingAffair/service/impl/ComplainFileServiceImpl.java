package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.ComplainFile;
import platform.education.generalTeachingAffair.vo.ComplainFileCondition;
import platform.education.generalTeachingAffair.service.ComplainFileService;
import platform.education.generalTeachingAffair.dao.ComplainFileDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ComplainFileServiceImpl implements ComplainFileService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ComplainFileDao complainFileDao;

	public void setComplainFileDao(ComplainFileDao complainFileDao) {
		this.complainFileDao = complainFileDao;
	}
	
	@Override
	public ComplainFile findComplainFileById(Integer id) {
		try {
			return complainFileDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ComplainFile add(ComplainFile complainFile) {
		if(complainFile == null) {
    		return null;
    	}
    	Date createDate = complainFile.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	complainFile.setCreateDate(createDate);
		return complainFileDao.create(complainFile);
	}

	@Override
	public ComplainFile modify(ComplainFile complainFile) {
		if(complainFile == null) {
    		return null;
    	}
		return complainFileDao.update(complainFile);
	}
	
	@Override
	public void remove(ComplainFile complainFile) {
		try {
			complainFileDao.delete(complainFile);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", complainFile.getId(), e);
			}
		}
	}
	
	@Override
	public List<ComplainFile> findComplainFileByCondition(ComplainFileCondition complainFileCondition, Page page, Order order) {
		return complainFileDao.findComplainFileByCondition(complainFileCondition, page, order);
	}
	
	@Override
	public List<ComplainFile> findComplainFileByCondition(ComplainFileCondition complainFileCondition) {
		return complainFileDao.findComplainFileByCondition(complainFileCondition, null, null);
	}
	
	@Override
	public List<ComplainFile> findComplainFileByCondition(ComplainFileCondition complainFileCondition, Page page) {
		return complainFileDao.findComplainFileByCondition(complainFileCondition, page, null);
	}
	
	@Override
	public List<ComplainFile> findComplainFileByCondition(ComplainFileCondition complainFileCondition, Order order) {
		return complainFileDao.findComplainFileByCondition(complainFileCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.complainFileDao.count(null);
	}

	@Override
	public Long count(ComplainFileCondition complainFileCondition) {
		return this.complainFileDao.count(complainFileCondition);
	}

	@Override
	public List<ComplainFile> findByComplainId(Integer complainId) {
		return this.complainFileDao.findByComplainId(complainId);
	}
}
