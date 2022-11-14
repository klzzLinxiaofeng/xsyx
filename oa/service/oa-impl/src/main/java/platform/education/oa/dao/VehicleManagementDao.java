package platform.education.oa.dao;

import platform.education.oa.model.VehicleManagement;
import platform.education.oa.vo.VehicleManagementCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface VehicleManagementDao extends GenericDao<VehicleManagement, java.lang.Integer> {

	List<VehicleManagement> findVehicleManagementByCondition(VehicleManagementCondition vehicleManagementCondition, Page page, Order order);
	
	VehicleManagement findById(Integer id);
	
	Long count(VehicleManagementCondition vehicleManagementCondition);
	
}
