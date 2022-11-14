package platform.education.oa.service;
import platform.education.oa.model.VehicleManagement;
import platform.education.oa.vo.VehicleManagementCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface VehicleManagementService {
    VehicleManagement findVehicleManagementById(Integer id);
	   
	VehicleManagement add(VehicleManagement vehicleManagement);
	   
	VehicleManagement modify(VehicleManagement vehicleManagement);
	   
	void remove(VehicleManagement vehicleManagement);
	   
	List<VehicleManagement> findVehicleManagementByCondition(VehicleManagementCondition vehicleManagementCondition, Page page, Order order);
	
	List<VehicleManagement> findVehicleManagementByCondition(VehicleManagementCondition vehicleManagementCondition);
	
	List<VehicleManagement> findVehicleManagementByCondition(VehicleManagementCondition vehicleManagementCondition, Page page);
	
	List<VehicleManagement> findVehicleManagementByCondition(VehicleManagementCondition vehicleManagementCondition, Order order);
	
	Long count();
	
	Long count(VehicleManagementCondition vehicleManagementCondition);
	
}
