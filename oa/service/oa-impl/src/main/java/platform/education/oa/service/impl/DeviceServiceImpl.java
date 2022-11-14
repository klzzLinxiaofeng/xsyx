package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Device;
import platform.education.oa.vo.DeviceCondition;
import platform.education.oa.vo.DeviceVo;
import platform.education.oa.service.DeviceService;
import platform.education.oa.dao.DeviceDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class DeviceServiceImpl implements DeviceService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private DeviceDao deviceDao;

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}
	
	@Override
	public Device findDeviceById(Integer id) {
		try {
			return deviceDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Device add(Device device) {
		if(device == null) {
    		return null;
    	}
    	Date createDate = device.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	device.setCreateDate(createDate);
    	device.setModifyDate(createDate);
		return deviceDao.create(device);
	}

	@Override
	public Device modify(Device device) {
		if(device == null) {
    		return null;
    	}
    	Date modify = device.getModifyDate();
    	device.setModifyDate(modify != null ? modify : new Date());
		return deviceDao.update(device);
	}
	
	@Override
	public void remove(Device device) {
		try {
			deviceDao.delete(device);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", device.getId(), e);
			}
		}
	}
	
	@Override
	public List<Device> findDeviceByCondition(DeviceCondition deviceCondition, Page page, Order order) {
		return deviceDao.findDeviceByCondition(deviceCondition, page, order);
	}
	
	@Override
	public List<Device> findDeviceByCondition(DeviceCondition deviceCondition) {
		return deviceDao.findDeviceByCondition(deviceCondition, null, null);
	}
	
	@Override
	public List<Device> findDeviceByCondition(DeviceCondition deviceCondition, Page page) {
		return deviceDao.findDeviceByCondition(deviceCondition, page, null);
	}
	
	@Override
	public List<Device> findDeviceByCondition(DeviceCondition deviceCondition, Order order) {
		return deviceDao.findDeviceByCondition(deviceCondition, null, order);
	}
	
	@Override
	public List<Device> findDeviceNameByCondition(DeviceCondition deviceCondition) {
		return deviceDao.findDeviceNameByCondition(deviceCondition);
	}
	
	@Override
	public Long count() {
		return this.deviceDao.count(null);
	}

	@Override
	public Long count(DeviceCondition deviceCondition) {
		return this.deviceDao.count(deviceCondition);
	}

	@Override
	public List<DeviceVo> findDeviceAllByCondition(DeviceCondition condition,
			Page page, Order order) {
		return deviceDao.findDeviceAllByCondition(condition, page, order);
	}

}
