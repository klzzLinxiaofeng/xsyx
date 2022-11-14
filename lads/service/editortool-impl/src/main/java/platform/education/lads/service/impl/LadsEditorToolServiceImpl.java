package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.vo.editortoolVo.LadsEditorToolCondition;
import platform.education.lads.service.LadsEditorToolService;
import platform.education.lads.dao.LadsEditorToolDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LadsEditorToolServiceImpl implements LadsEditorToolService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private LadsEditorToolDao ladsEditorToolDao;

	public void setLadsEditorToolDao(LadsEditorToolDao ladsEditorToolDao) {
		this.ladsEditorToolDao = ladsEditorToolDao;
	}
	
	@Override
	public LadsEditorTool findLadsEditorToolById(Integer id) {
		try {
			return ladsEditorToolDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public LadsEditorTool add(LadsEditorTool ladsEditorTool) {
		if(ladsEditorTool == null) {
    		return null;
    	}
    	Date createDate = ladsEditorTool.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	ladsEditorTool.setCreateDate(createDate);
    	ladsEditorTool.setModifyDate(createDate);
		return ladsEditorToolDao.create(ladsEditorTool);
	}

	@Override
	public LadsEditorTool modify(LadsEditorTool ladsEditorTool) {
		if(ladsEditorTool == null) {
    		return null;
    	}
    	Date modify = ladsEditorTool.getModifyDate();
    	ladsEditorTool.setModifyDate(modify != null ? modify : new Date());
		return ladsEditorToolDao.update(ladsEditorTool);
	}
	
	@Override
	public void remove(LadsEditorTool ladsEditorTool) {
		try {
			ladsEditorToolDao.delete(ladsEditorTool);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", ladsEditorTool.getId(), e);
			}
		}
	}
	
	@Override
	public List<LadsEditorTool> findLadsEditorToolByCondition(LadsEditorToolCondition ladsEditorToolCondition, Page page, Order order) {
		return ladsEditorToolDao.findLadsEditorToolByCondition(ladsEditorToolCondition, page, order);
	}
	
	@Override
	public List<LadsEditorTool> findLadsEditorToolByCondition(LadsEditorToolCondition ladsEditorToolCondition) {
		return ladsEditorToolDao.findLadsEditorToolByCondition(ladsEditorToolCondition, null, null);
	}
	
	@Override
	public List<LadsEditorTool> findLadsEditorToolByCondition(LadsEditorToolCondition ladsEditorToolCondition, Page page) {
		return ladsEditorToolDao.findLadsEditorToolByCondition(ladsEditorToolCondition, page, null);
	}
	
	@Override
	public List<LadsEditorTool> findLadsEditorToolByCondition(LadsEditorToolCondition ladsEditorToolCondition, Order order) {
		return ladsEditorToolDao.findLadsEditorToolByCondition(ladsEditorToolCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.ladsEditorToolDao.count(null);
	}

	@Override
	public Long count(LadsEditorToolCondition ladsEditorToolCondition) {
		return this.ladsEditorToolDao.count(ladsEditorToolCondition);
	}

}
