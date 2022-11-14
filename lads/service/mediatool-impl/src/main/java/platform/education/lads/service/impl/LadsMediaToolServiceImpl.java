package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.vo.mediaToolVo.LadsMediaToolCondition;
import platform.education.lads.service.LadsMediaToolService;
import platform.education.lads.dao.LadsMediaToolDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LadsMediaToolServiceImpl implements LadsMediaToolService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private LadsMediaToolDao ladsMediaToolDao;

	public void setLadsMediaToolDao(LadsMediaToolDao ladsMediaToolDao) {
		this.ladsMediaToolDao = ladsMediaToolDao;
	}
	
	@Override
	public LadsMediaTool findLadsMediaToolById(Integer id) {
		try {
			return ladsMediaToolDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public LadsMediaTool add(LadsMediaTool ladsMediaTool) {
		if(ladsMediaTool == null) {
    		return null;
    	}
    	Date createDate = ladsMediaTool.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	ladsMediaTool.setCreateDate(createDate);
    	ladsMediaTool.setModifyDate(createDate);
		return ladsMediaToolDao.create(ladsMediaTool);
	}

	@Override
	public LadsMediaTool modify(LadsMediaTool ladsMediaTool) {
		if(ladsMediaTool == null) {
    		return null;
    	}
    	Date modify = ladsMediaTool.getModifyDate();
    	ladsMediaTool.setModifyDate(modify != null ? modify : new Date());
		return ladsMediaToolDao.update(ladsMediaTool);
	}
	
	@Override
	public void remove(LadsMediaTool ladsMediaTool) {
		try {
			ladsMediaToolDao.delete(ladsMediaTool);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", ladsMediaTool.getId(), e);
			}
		}
	}
	
	@Override
	public List<LadsMediaTool> findLadsMediaToolByCondition(LadsMediaToolCondition ladsMediaToolCondition, Page page, Order order) {
		return ladsMediaToolDao.findLadsMediaToolByCondition(ladsMediaToolCondition, page, order);
	}
	
	@Override
	public List<LadsMediaTool> findLadsMediaToolByCondition(LadsMediaToolCondition ladsMediaToolCondition) {
		return ladsMediaToolDao.findLadsMediaToolByCondition(ladsMediaToolCondition, null, null);
	}
	
	@Override
	public List<LadsMediaTool> findLadsMediaToolByCondition(LadsMediaToolCondition ladsMediaToolCondition, Page page) {
		return ladsMediaToolDao.findLadsMediaToolByCondition(ladsMediaToolCondition, page, null);
	}
	
	@Override
	public List<LadsMediaTool> findLadsMediaToolByCondition(LadsMediaToolCondition ladsMediaToolCondition, Order order) {
		return ladsMediaToolDao.findLadsMediaToolByCondition(ladsMediaToolCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.ladsMediaToolDao.count(null);
	}

	@Override
	public Long count(LadsMediaToolCondition ladsMediaToolCondition) {
		return this.ladsMediaToolDao.count(ladsMediaToolCondition);
	}

}
