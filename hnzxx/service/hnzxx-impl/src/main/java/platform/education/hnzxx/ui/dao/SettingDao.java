package platform.education.hnzxx.ui.dao;

import platform.education.hnzxx.ui.model.Setting;
import platform.education.hnzxx.ui.vo.SettingCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SettingDao extends GenericDao<Setting, java.lang.Integer> {

	List<Setting> findSettingByCondition(SettingCondition settingCondition, Page page, Order order);
	
	Setting findById(Integer id);
	
	Long count(SettingCondition settingCondition);
	
	/**
	 * 功能描述：通过用户ID查找记录
	 * @param userId
	 * @return
	 */
	Setting findByUserId(Integer userId);
	
}
