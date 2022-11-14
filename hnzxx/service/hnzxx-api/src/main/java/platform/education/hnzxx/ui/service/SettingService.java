package platform.education.hnzxx.ui.service;
import platform.education.hnzxx.ui.model.Setting;
import platform.education.hnzxx.ui.vo.SettingCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SettingService {
    Setting findSettingById(Integer id);
	   
	Setting add(Setting setting);
	   
	Setting modify(Setting setting);
	   
	void remove(Setting setting);
	   
	List<Setting> findSettingByCondition(SettingCondition settingCondition, Page page, Order order);
	
	List<Setting> findSettingByCondition(SettingCondition settingCondition);
	
	List<Setting> findSettingByCondition(SettingCondition settingCondition, Page page);
	
	List<Setting> findSettingByCondition(SettingCondition settingCondition, Order order);
	
	Long count();
	
	Long count(SettingCondition settingCondition);
	
	/**
	 * 功能描述：通过用户ID查找记录
	 * @param userId
	 * @return
	 */
	Setting findByUserId(Integer userId);
	
}
