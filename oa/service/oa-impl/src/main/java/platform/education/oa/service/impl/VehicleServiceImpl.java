package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Vehicle;
import platform.education.oa.vo.VehicleCondition;
import platform.education.oa.service.VehicleService;
import platform.education.oa.dao.VehicleDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class VehicleServiceImpl implements VehicleService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private VehicleDao vehicleDao;

	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	@Override
	public Vehicle findVehicleById(Integer id) {
		try {
			return vehicleDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Vehicle add(Vehicle vehicle) {
		if(vehicle == null) {
    		return null;
    	}
    	Date createDate = vehicle.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	vehicle.setCreateDate(createDate);
    	vehicle.setModifyDate(createDate);
		return vehicleDao.create(vehicle);
	}

	@Override
	public Vehicle modify(Vehicle vehicle) {
		if(vehicle == null) {
    		return null;
    	}
    	Date modify = vehicle.getModifyDate();
    	vehicle.setModifyDate(modify != null ? modify : new Date());
		return vehicleDao.update(vehicle);
	}
	
	@Override
	public void remove(Vehicle vehicle) {
		try {
			vehicleDao.delete(vehicle);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", vehicle.getId(), e);
			}
		}
	}
	
	@Override
	public List<Vehicle> findVehicleByCondition(VehicleCondition vehicleCondition, Page page, Order order) {
		return vehicleDao.findVehicleByCondition(vehicleCondition, page, order);
	}
	
	@Override
	public List<Vehicle> findVehicleByCondition(VehicleCondition vehicleCondition) {
		return vehicleDao.findVehicleByCondition(vehicleCondition, null, null);
	}
	
	@Override
	public List<Vehicle> findVehicleByCondition(VehicleCondition vehicleCondition, Page page) {
		return vehicleDao.findVehicleByCondition(vehicleCondition, page, null);
	}
	
	@Override
	public List<Vehicle> findVehicleByCondition(VehicleCondition vehicleCondition, Order order) {
		return vehicleDao.findVehicleByCondition(vehicleCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.vehicleDao.count(null);
	}

	@Override
	public Long count(VehicleCondition vehicleCondition) {
		return this.vehicleDao.count(vehicleCondition);
	}

	@Override
	public Vehicle findVehicleByplateNumber(String plateNumber) {
		return vehicleDao.findVehicleByplateNumber(plateNumber);
	}

}
