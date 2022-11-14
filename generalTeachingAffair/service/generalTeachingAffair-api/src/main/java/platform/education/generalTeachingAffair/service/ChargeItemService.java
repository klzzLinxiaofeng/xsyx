package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.Charge;
import platform.education.generalTeachingAffair.model.ChargeItem;
import platform.education.generalTeachingAffair.vo.ChargeItemCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ChargeItemService {
    ChargeItem findChargeItemById(Integer id);
	   
	ChargeItem add(ChargeItem chargeItem);
	   
	ChargeItem modify(ChargeItem chargeItem);
	   
	void remove(ChargeItem chargeItem);
	   
	List<ChargeItem> findChargeItemByCondition(ChargeItemCondition chargeItemCondition, Page page, Order order);
	
	List<ChargeItem> findChargeItemByCondition(ChargeItemCondition chargeItemCondition);
	
	List<ChargeItem> findChargeItemByCondition(ChargeItemCondition chargeItemCondition, Page page);
	
	List<ChargeItem> findChargeItemByCondition(ChargeItemCondition chargeItemCondition, Order order);
	
	Long count();
	
	Long count(ChargeItemCondition chargeItemCondition);

	ChargeItem addItem(ChargeItem chargeItem);

	ChargeItem modifyItem(ChargeItem chargeItem);

	void removeItem(ChargeItem chargeItem);

	/**
	 * 获取收费项（项目唯一，无重名）
	 */
	ChargeItem findUniqueByName(Integer schoolId, String name, Boolean isDeleted);

	List<ChargeItem> findBySchoolId(Integer schoolId);

	List<ChargeItem> findWithName(Integer schoolId, String name, Page page, Order order);
	
}
