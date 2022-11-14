package platform.education.oa.service;
import platform.education.oa.model.Vehicle;
import platform.education.oa.vo.VehicleCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface VehicleService {
    Vehicle findVehicleById(Integer id);
	   
	Vehicle add(Vehicle vehicle);
	   
	Vehicle modify(Vehicle vehicle);
	   
	void remove(Vehicle vehicle);
	   
	List<Vehicle> findVehicleByCondition(VehicleCondition vehicleCondition, Page page, Order order);
	
	List<Vehicle> findVehicleByCondition(VehicleCondition vehicleCondition);
	
	List<Vehicle> findVehicleByCondition(VehicleCondition vehicleCondition, Page page);
	
	List<Vehicle> findVehicleByCondition(VehicleCondition vehicleCondition, Order order);
	
	Long count();
	
	Long count(VehicleCondition vehicleCondition);

	Vehicle findVehicleByplateNumber(String plateNumber);
	
}
