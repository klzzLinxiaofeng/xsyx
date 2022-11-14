package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ChargeItem;
import platform.education.generalTeachingAffair.vo.ChargeItemCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ChargeItemDao extends GenericDao<ChargeItem, Integer> {

	List<ChargeItem> findChargeItemByCondition(ChargeItemCondition chargeItemCondition, Page page, Order order);
	
	ChargeItem findById(Integer id);
	
	Long count(ChargeItemCondition chargeItemCondition);

	//获取收费项，项目名唯一，无论是否删除，isDeleted不填默认0（未删除）
	ChargeItem findUniqueByName(Integer schoolId, String name, boolean isDeleted);

	List<ChargeItem> findBySchoolIdAndName(Integer schoolId, String name, Page page, Order order);
	
}
