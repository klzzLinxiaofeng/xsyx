package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.WarrantyEquipment;
import platform.education.oa.vo.WarrantyEquipmentCondition;
import platform.education.oa.service.WarrantyEquipmentService;
import platform.education.oa.dao.WarrantyEquipmentDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class WarrantyEquipmentServiceImpl implements WarrantyEquipmentService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private WarrantyEquipmentDao warrantyEquipmentDao;

	public void setWarrantyEquipmentDao(WarrantyEquipmentDao warrantyEquipmentDao) {
		this.warrantyEquipmentDao = warrantyEquipmentDao;
	}
	
	@Override
	public WarrantyEquipment findWarrantyEquipmentById(Integer id) {
		try {
			return warrantyEquipmentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public WarrantyEquipment add(WarrantyEquipment warrantyEquipment) {
		if(warrantyEquipment == null) {
    		return null;
    	}
    	Date createDate = warrantyEquipment.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	warrantyEquipment.setCreateDate(createDate);
    	warrantyEquipment.setModifyDate(createDate);
		return warrantyEquipmentDao.create(warrantyEquipment);
	}

	@Override
	public WarrantyEquipment modify(WarrantyEquipment warrantyEquipment) {
		if(warrantyEquipment == null) {
    		return null;
    	}
    	Date modify = warrantyEquipment.getModifyDate();
    	warrantyEquipment.setModifyDate(modify != null ? modify : new Date());
		return warrantyEquipmentDao.update(warrantyEquipment);
	}
	
	@Override
	public void remove(WarrantyEquipment warrantyEquipment) {
		try {
			warrantyEquipmentDao.delete(warrantyEquipment);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", warrantyEquipment.getId(), e);
			}
		}
	}
	
	@Override
	public List<WarrantyEquipment> findWarrantyEquipmentByCondition(WarrantyEquipmentCondition warrantyEquipmentCondition, Page page, Order order) {
		return warrantyEquipmentDao.findWarrantyEquipmentByCondition(warrantyEquipmentCondition, page, order);
	}
	
	@Override
	public List<WarrantyEquipment> findWarrantyEquipmentByCondition(WarrantyEquipmentCondition warrantyEquipmentCondition) {
		return warrantyEquipmentDao.findWarrantyEquipmentByCondition(warrantyEquipmentCondition, null, null);
	}
	
	@Override
	public List<WarrantyEquipment> findWarrantyEquipmentByCondition(WarrantyEquipmentCondition warrantyEquipmentCondition, Page page) {
		return warrantyEquipmentDao.findWarrantyEquipmentByCondition(warrantyEquipmentCondition, page, null);
	}
	
	@Override
	public List<WarrantyEquipment> findWarrantyEquipmentByCondition(WarrantyEquipmentCondition warrantyEquipmentCondition, Order order) {
		return warrantyEquipmentDao.findWarrantyEquipmentByCondition(warrantyEquipmentCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.warrantyEquipmentDao.count(null);
	}

	@Override
	public Long count(WarrantyEquipmentCondition warrantyEquipmentCondition) {
		return this.warrantyEquipmentDao.count(warrantyEquipmentCondition);
	}

}
