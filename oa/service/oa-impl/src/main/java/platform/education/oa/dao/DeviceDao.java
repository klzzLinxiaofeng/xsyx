package platform.education.oa.dao;

import platform.education.oa.model.Device;
import platform.education.oa.vo.DeviceCondition;
import platform.education.oa.vo.DeviceVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface DeviceDao extends GenericDao<Device, java.lang.Integer> {

	List<Device> findDeviceByCondition(DeviceCondition deviceCondition, Page page, Order order);
	
	List<Device> findDeviceNameByCondition(DeviceCondition deviceCondition);
	
	Device findById(Integer id);
	
	Long count(DeviceCondition deviceCondition);

	List<DeviceVo> findDeviceAllByCondition(DeviceCondition condition, Page page,
			Order order);
	
}
