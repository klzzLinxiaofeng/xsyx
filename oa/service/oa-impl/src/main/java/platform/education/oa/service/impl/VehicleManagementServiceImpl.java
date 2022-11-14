package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.VehicleManagement;
import platform.education.oa.vo.VehicleManagementCondition;
import platform.education.oa.service.VehicleManagementService;
import platform.education.oa.dao.VehicleManagementDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class VehicleManagementServiceImpl implements VehicleManagementService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private VehicleManagementDao vehicleManagementDao;

	public void setVehicleManagementDao(VehicleManagementDao vehicleManagementDao) {
		this.vehicleManagementDao = vehicleManagementDao;
	}
	
	@Override
	public VehicleManagement findVehicleManagementById(Integer id) {
		try {
			return vehicleManagementDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public VehicleManagement add(VehicleManagement vehicleManagement) {
		if(vehicleManagement == null) {
    		return null;
    	}
    	Date createDate = vehicleManagement.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	vehicleManagement.setCreateDate(createDate);
    	vehicleManagement.setModifyDate(createDate);
		return vehicleManagementDao.create(vehicleManagement);
	}

	@Override
	public VehicleManagement modify(VehicleManagement vehicleManagement) {
		if(vehicleManagement == null) {
    		return null;
    	}
    	Date modify = vehicleManagement.getModifyDate();
    	vehicleManagement.setModifyDate(modify != null ? modify : new Date());
		return vehicleManagementDao.update(vehicleManagement);
	}
	
	@Override
	public void remove(VehicleManagement vehicleManagement) {
		try {
			vehicleManagementDao.delete(vehicleManagement);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", vehicleManagement.getId(), e);
			}
		}
	}
	
	@Override
	public List<VehicleManagement> findVehicleManagementByCondition(VehicleManagementCondition vehicleManagementCondition, Page page, Order order) {
		return vehicleManagementDao.findVehicleManagementByCondition(vehicleManagementCondition, page, order);
	}
	
	@Override
	public List<VehicleManagement> findVehicleManagementByCondition(VehicleManagementCondition vehicleManagementCondition) {
		return vehicleManagementDao.findVehicleManagementByCondition(vehicleManagementCondition, null, null);
	}
	
	@Override
	public List<VehicleManagement> findVehicleManagementByCondition(VehicleManagementCondition vehicleManagementCondition, Page page) {
		return vehicleManagementDao.findVehicleManagementByCondition(vehicleManagementCondition, page, null);
	}
	
	@Override
	public List<VehicleManagement> findVehicleManagementByCondition(VehicleManagementCondition vehicleManagementCondition, Order order) {
		return vehicleManagementDao.findVehicleManagementByCondition(vehicleManagementCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.vehicleManagementDao.count(null);
	}

	@Override
	public Long count(VehicleManagementCondition vehicleManagementCondition) {
		return this.vehicleManagementDao.count(vehicleManagementCondition);
	}

}
