package platform.education.lads.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsPreviewJson;
import platform.education.lads.vo.LadsPreviewJsonCondition;
import platform.education.lads.service.LadsPreviewJsonService;
import platform.education.lads.dao.LadsPreviewJsonDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Date;

public class LadsPreviewJsonServiceImpl implements LadsPreviewJsonService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private LadsPreviewJsonDao ladsPreviewJsonDao;

	public void setLadsPreviewJsonDao(LadsPreviewJsonDao ladsPreviewJsonDao) {
		this.ladsPreviewJsonDao = ladsPreviewJsonDao;
	}
	
	@Override
	public LadsPreviewJson findLadsPreviewJsonById(Integer id) {
		try {
			return ladsPreviewJsonDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public LadsPreviewJson add(LadsPreviewJson ladsPreviewJson) {
		if(ladsPreviewJson == null) {
    		return null;
    	}
    	Date createDate = ladsPreviewJson.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	ladsPreviewJson.setCreateDate(createDate);
    	ladsPreviewJson.setModifyDate(createDate);
		return ladsPreviewJsonDao.create(ladsPreviewJson);
	}

	@Override
	public LadsPreviewJson modify(LadsPreviewJson ladsPreviewJson) {
		if(ladsPreviewJson == null) {
    		return null;
    	}
    	Date modify = ladsPreviewJson.getModifyDate();
    	ladsPreviewJson.setModifyDate(modify != null ? modify : new Date());
		return ladsPreviewJsonDao.update(ladsPreviewJson);
	}
	
	@Override
	public void remove(LadsPreviewJson ladsPreviewJson) {
		 ladsPreviewJsonDao.delete(ladsPreviewJson);
	}
	
	@Override
	public List<LadsPreviewJson> findLadsPreviewJsonByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition, Page page, Order order) {
		return ladsPreviewJsonDao.findLadsPreviewJsonByCondition(ladsPreviewJsonCondition, page, order);
	}
	
	@Override
	public List<LadsPreviewJson> findLadsPreviewJsonByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition) {
		return ladsPreviewJsonDao.findLadsPreviewJsonByCondition(ladsPreviewJsonCondition, null, null);
	}
	
	@Override
	public List<LadsPreviewJson> findLadsPreviewJsonByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition, Page page) {
		return ladsPreviewJsonDao.findLadsPreviewJsonByCondition(ladsPreviewJsonCondition, page, null);
	}
	
	@Override
	public List<LadsPreviewJson> findLadsPreviewJsonByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition, Order order) {
		return ladsPreviewJsonDao.findLadsPreviewJsonByCondition(ladsPreviewJsonCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.ladsPreviewJsonDao.count(null);
	}

	@Override
	public Long count(LadsPreviewJsonCondition ladsPreviewJsonCondition) {
		return this.ladsPreviewJsonDao.count(ladsPreviewJsonCondition);
	}

	@Override
	public void remove(LadsPreviewJsonCondition ladsPreviewJsonCondition) {
		this.ladsPreviewJsonDao.deleteByCondition(ladsPreviewJsonCondition);
	}
        
        @Override
	public LadsPreviewJson findLadsPreviewJsonByUuid(String uuid) {
		try {
			return ladsPreviewJsonDao.findByUuid(uuid);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在UUID为 {} ", uuid);
		}
		return null;
	}

}
