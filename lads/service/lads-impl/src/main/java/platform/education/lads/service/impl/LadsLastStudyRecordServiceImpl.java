package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsLastStudyRecord;
import platform.education.lads.vo.LadsLastStudyRecordCondition;
import platform.education.lads.service.LadsLastStudyRecordService;
import platform.education.lads.dao.LadsLastStudyRecordDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LadsLastStudyRecordServiceImpl implements LadsLastStudyRecordService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private LadsLastStudyRecordDao ladsLastStudyRecordDao;

	public void setLadsLastStudyRecordDao(LadsLastStudyRecordDao ladsLastStudyRecordDao) {
		this.ladsLastStudyRecordDao = ladsLastStudyRecordDao;
	}
	
	@Override
	public LadsLastStudyRecord findLadsLastStudyRecordById(Integer id) {
		try {
			return ladsLastStudyRecordDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public LadsLastStudyRecord add(LadsLastStudyRecord ladsLastStudyRecord) {
		if(ladsLastStudyRecord == null) {
    		return null;
    	}
    	Date createDate = ladsLastStudyRecord.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	ladsLastStudyRecord.setCreateDate(createDate);
    	ladsLastStudyRecord.setModifyDate(createDate);
		return ladsLastStudyRecordDao.create(ladsLastStudyRecord);
	}

	@Override
	public LadsLastStudyRecord modify(LadsLastStudyRecord ladsLastStudyRecord) {
		if(ladsLastStudyRecord == null) {
    		return null;
    	}
    	Date modify = ladsLastStudyRecord.getModifyDate();
    	ladsLastStudyRecord.setModifyDate(modify != null ? modify : new Date());
		return ladsLastStudyRecordDao.update(ladsLastStudyRecord);
	}
	
	@Override
	public void remove(LadsLastStudyRecord ladsLastStudyRecord) {
		 ladsLastStudyRecordDao.delete(ladsLastStudyRecord);
	}
	
	@Override
	public List<LadsLastStudyRecord> findLadsLastStudyRecordByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition, Page page, Order order) {
		return ladsLastStudyRecordDao.findLadsLastStudyRecordByCondition(ladsLastStudyRecordCondition, page, order);
	}
	
	@Override
	public List<LadsLastStudyRecord> findLadsLastStudyRecordByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition) {
		return ladsLastStudyRecordDao.findLadsLastStudyRecordByCondition(ladsLastStudyRecordCondition, null, null);
	}
	
	@Override
	public List<LadsLastStudyRecord> findLadsLastStudyRecordByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition, Page page) {
		return ladsLastStudyRecordDao.findLadsLastStudyRecordByCondition(ladsLastStudyRecordCondition, page, null);
	}
	
	@Override
	public List<LadsLastStudyRecord> findLadsLastStudyRecordByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition, Order order) {
		return ladsLastStudyRecordDao.findLadsLastStudyRecordByCondition(ladsLastStudyRecordCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.ladsLastStudyRecordDao.count(null);
	}

	@Override
	public Long count(LadsLastStudyRecordCondition ladsLastStudyRecordCondition) {
		return this.ladsLastStudyRecordDao.count(ladsLastStudyRecordCondition);
	}

	@Override
	public void remove(LadsLastStudyRecordCondition ladsLastStudyRecordCondition) {
		this.ladsLastStudyRecordDao.deleteByCondition(ladsLastStudyRecordCondition);
	}
        
        //以下是业务方法
        @Override
	public LadsLastStudyRecord findLadsLastStudyRecordByUuid(String uuid) {
		try {
			return ladsLastStudyRecordDao.findByUuid(uuid);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在UUID为 {} ", uuid);
		}
		return null;
	}

}
