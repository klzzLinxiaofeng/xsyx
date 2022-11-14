package platform.education.hnzxx.ui.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.hnzxx.ui.model.Setting;
import platform.education.hnzxx.ui.vo.SettingCondition;
import platform.education.hnzxx.ui.service.SettingService;
import platform.education.hnzxx.ui.dao.SettingDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SettingServiceImpl implements SettingService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SettingDao settingDao;

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}
	
	@Override
	public Setting findSettingById(Integer id) {
		try {
			return settingDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Setting add(Setting setting) {
		if(setting == null) {
    		return null;
    	}
    	Date createDate = setting.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	setting.setCreateDate(createDate);
    	setting.setModifyDate(createDate);
		return settingDao.create(setting);
	}

	@Override
	public Setting modify(Setting setting) {
		if(setting == null) {
    		return null;
    	}
    	Date modify = setting.getModifyDate();
    	setting.setModifyDate(modify != null ? modify : new Date());
		return settingDao.update(setting);
	}
	
	@Override
	public void remove(Setting setting) {
		try {
			settingDao.delete(setting);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", setting.getId(), e);
			}
		}
	}
	
	@Override
	public List<Setting> findSettingByCondition(SettingCondition settingCondition, Page page, Order order) {
		return settingDao.findSettingByCondition(settingCondition, page, order);
	}
	
	@Override
	public List<Setting> findSettingByCondition(SettingCondition settingCondition) {
		return settingDao.findSettingByCondition(settingCondition, null, null);
	}
	
	@Override
	public List<Setting> findSettingByCondition(SettingCondition settingCondition, Page page) {
		return settingDao.findSettingByCondition(settingCondition, page, null);
	}
	
	@Override
	public List<Setting> findSettingByCondition(SettingCondition settingCondition, Order order) {
		return settingDao.findSettingByCondition(settingCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.settingDao.count(null);
	}

	@Override
	public Long count(SettingCondition settingCondition) {
		return this.settingDao.count(settingCondition);
	}

	@Override
	public Setting findByUserId(Integer userId) {
		try {
			return settingDao.findByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在该用户的记录为 {} ", userId);
		}
		return null;
	}

}
