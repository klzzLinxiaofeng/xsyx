package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.Charge;
import platform.education.generalTeachingAffair.model.ChargeItem;
import platform.education.generalTeachingAffair.vo.ChargeItemCondition;
import platform.education.generalTeachingAffair.service.ChargeItemService;
import platform.education.generalTeachingAffair.dao.ChargeItemDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ChargeItemServiceImpl implements ChargeItemService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ChargeItemDao chargeItemDao;

	public void setChargeItemDao(ChargeItemDao chargeItemDao) {
		this.chargeItemDao = chargeItemDao;
	}
	
	@Override
	public ChargeItem findChargeItemById(Integer id) {
		try {
			return chargeItemDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ChargeItem add(ChargeItem chargeItem) {
		if(chargeItem == null) {
    		return null;
    	}
    	Date createDate = chargeItem.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	chargeItem.setCreateDate(createDate);
    	chargeItem.setModifyDate(createDate);
		return chargeItemDao.create(chargeItem);
	}

	@Override
	public ChargeItem modify(ChargeItem chargeItem) {
		if(chargeItem == null) {
    		return null;
    	}
    	Date modify = chargeItem.getModifyDate();
    	chargeItem.setModifyDate(modify != null ? modify : new Date());
		return chargeItemDao.update(chargeItem);
	}
	
	@Override
	public void remove(ChargeItem chargeItem) {
		try {
			chargeItemDao.delete(chargeItem);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", chargeItem.getId(), e);
			}
		}
	}
	
	@Override
	public List<ChargeItem> findChargeItemByCondition(ChargeItemCondition chargeItemCondition, Page page, Order order) {
		return chargeItemDao.findChargeItemByCondition(chargeItemCondition, page, order);
	}
	
	@Override
	public List<ChargeItem> findChargeItemByCondition(ChargeItemCondition chargeItemCondition) {
		return chargeItemDao.findChargeItemByCondition(chargeItemCondition, null, null);
	}
	
	@Override
	public List<ChargeItem> findChargeItemByCondition(ChargeItemCondition chargeItemCondition, Page page) {
		return chargeItemDao.findChargeItemByCondition(chargeItemCondition, page, null);
	}
	
	@Override
	public List<ChargeItem> findChargeItemByCondition(ChargeItemCondition chargeItemCondition, Order order) {
		return chargeItemDao.findChargeItemByCondition(chargeItemCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.chargeItemDao.count(null);
	}

	@Override
	public Long count(ChargeItemCondition chargeItemCondition) {
		return this.chargeItemDao.count(chargeItemCondition);
	}

	@Override
	public ChargeItem addItem(ChargeItem chargeItem) {
		if (chargeItem == null) {
			return null;
		}
		//objectType 和 objectId 的值接口不处理，有页面搞定
//		if (chargeItem.getObjectType() == null || "".equals(chargeItem.getObjectType())) {
//			chargeItem.setObjectType("school");
//			chargeItem.setObjectIds(String.valueOf(chargeItem.getSchoolId()));
//		}
		/*	原：新增时判断
			1.有重名、未删除 → 返回错误，不操作(将它放在Controller中操作)
		 	2.有重名、已删除 → 修改状态
		 	3.无重名 → 新增
		 */
		ChargeItem item = chargeItemDao.findUniqueByName(chargeItem.getSchoolId(), chargeItem.getName(), true);
		if (item != null) {
			item.setIsDeleted(false);
			item.setModifyDate(new Date());
			return chargeItemDao.update(item);
		} else {
			chargeItem.setCreateDate(new Date());
			chargeItem.setModifyDate(new Date());
			return chargeItemDao.create(chargeItem);
		}
		/*
		 * 	现：新增/修改时只判断是否重名且未删除，是则不操作，返回错误；否则新增或修改
		 *  因为按原规则，在修改时判断有重名且已删除时，
		 *  若判是，不能修改，用户感觉奇怪，因看不到有重名的；
		 *  若判否，可修改，已删和未删两个重名项，与原逻辑不符
		 */
	}

	@Override
	public ChargeItem modifyItem(ChargeItem chargeItem) {
		return null;
	}

	@Override
	public void removeItem(ChargeItem chargeItem) {
		if (chargeItem == null) {
			return;
		}
		chargeItem.setIsDeleted(true);
		chargeItem.setModifyDate(new Date());
		chargeItemDao.update(chargeItem);
	}

	@Override
	public ChargeItem findUniqueByName(Integer schoolId, String name, Boolean isDeleted) {
		if (isDeleted == null) {
			isDeleted = false;
		}
		return chargeItemDao.findUniqueByName(schoolId, name, isDeleted);
	}

	@Override
	public List<ChargeItem> findBySchoolId(Integer schoolId) {
		return chargeItemDao.findBySchoolIdAndName(schoolId, null, null, null);
	}

	@Override
	public List<ChargeItem> findWithName(Integer schoolId, String name, Page page, Order order) {
		return chargeItemDao.findBySchoolIdAndName(schoolId, name, page, order);
	}
}
