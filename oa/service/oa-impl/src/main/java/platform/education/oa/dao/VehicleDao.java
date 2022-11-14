package platform.education.oa.dao;

import platform.education.oa.model.Vehicle;
import platform.education.oa.vo.VehicleCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface VehicleDao extends GenericDao<Vehicle, java.lang.Integer> {

	List<Vehicle> findVehicleByCondition(VehicleCondition vehicleCondition, Page page, Order order);
	
	Vehicle findById(Integer id);
	
	Long count(VehicleCondition vehicleCondition);

	Vehicle findVehicleByplateNumber(String plateNumber);
	
}
