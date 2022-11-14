package platform.education.oa.service;
import platform.education.oa.model.Device;
import platform.education.oa.vo.DeviceCondition;
import platform.education.oa.vo.DeviceVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface DeviceService {
    Device findDeviceById(Integer id);
	   
	Device add(Device device);
	   
	Device modify(Device device);
	   
	void remove(Device device);
	   
	List<Device> findDeviceByCondition(DeviceCondition deviceCondition, Page page, Order order);
	
	List<Device> findDeviceByCondition(DeviceCondition deviceCondition);
	
	List<Device> findDeviceByCondition(DeviceCondition deviceCondition, Page page);
	
	List<Device> findDeviceByCondition(DeviceCondition deviceCondition, Order order);
	
	List<Device> findDeviceNameByCondition(DeviceCondition deviceCondition);
	
	Long count();
	
	Long count(DeviceCondition deviceCondition);

	List<DeviceVo> findDeviceAllByCondition(DeviceCondition condition, Page page,
			Order order);
	
}
