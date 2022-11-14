package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaUserFile;
import platform.education.paper.vo.PaUserFileCondition;
import platform.education.paper.vo.PaUserFileVo;
import platform.education.paper.service.PaUserFileService;
import platform.education.paper.dao.PaUserFileDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaUserFileServiceImpl implements PaUserFileService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaUserFileDao paUserFileDao;

	public void setPaUserFileDao(PaUserFileDao paUserFileDao) {
		this.paUserFileDao = paUserFileDao;
	}
	
	@Override
	public PaUserFile findPaUserFileById(Integer id) {
		try {
			return paUserFileDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaUserFile add(PaUserFile paUserFile) {
		if(paUserFile == null) {
    		return null;
    	}
    	Date createDate = paUserFile.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paUserFile.setCreateDate(createDate);
    	paUserFile.setModifyDate(createDate);
		return paUserFileDao.create(paUserFile);
	}

	@Override
	public PaUserFile modify(PaUserFile paUserFile) {
		if(paUserFile == null) {
    		return null;
    	}
    	Date modify = paUserFile.getModifyDate();
    	paUserFile.setModifyDate(modify != null ? modify : new Date());
		return paUserFileDao.update(paUserFile);
	}
	
	@Override
	public void remove(PaUserFile paUserFile) {
		try {
			paUserFileDao.delete(paUserFile);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paUserFile.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaUserFile> findPaUserFileByCondition(PaUserFileCondition paUserFileCondition, Page page, Order order) {
		return paUserFileDao.findPaUserFileByCondition(paUserFileCondition, page, order);
	}
	
	@Override
	public List<PaUserFile> findPaUserFileByCondition(PaUserFileCondition paUserFileCondition) {
		return paUserFileDao.findPaUserFileByCondition(paUserFileCondition, null, null);
	}
	
	@Override
	public List<PaUserFile> findPaUserFileByCondition(PaUserFileCondition paUserFileCondition, Page page) {
		return paUserFileDao.findPaUserFileByCondition(paUserFileCondition, page, null);
	}
	
	@Override
	public List<PaUserFile> findPaUserFileByCondition(PaUserFileCondition paUserFileCondition, Order order) {
		return paUserFileDao.findPaUserFileByCondition(paUserFileCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paUserFileDao.count(null);
	}

	@Override
	public Long count(PaUserFileCondition paUserFileCondition) {
		return this.paUserFileDao.count(paUserFileCondition);
	}

	@Override
	public List<PaUserFileVo> findPaUserFilesByUuids(String[] uuids) {
		
		return this.paUserFileDao.findPaUserFilesByUuids(uuids);
	}

}
