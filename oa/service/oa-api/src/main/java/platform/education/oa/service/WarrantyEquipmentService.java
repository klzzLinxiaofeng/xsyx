package platform.education.oa.service;
import platform.education.oa.model.WarrantyEquipment;
import platform.education.oa.vo.WarrantyEquipmentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface WarrantyEquipmentService {
    WarrantyEquipment findWarrantyEquipmentById(Integer id);
	   
	WarrantyEquipment add(WarrantyEquipment warrantyEquipment);
	   
	WarrantyEquipment modify(WarrantyEquipment warrantyEquipment);
	   
	void remove(WarrantyEquipment warrantyEquipment);
	   
	List<WarrantyEquipment> findWarrantyEquipmentByCondition(WarrantyEquipmentCondition warrantyEquipmentCondition, Page page, Order order);
	
	List<WarrantyEquipment> findWarrantyEquipmentByCondition(WarrantyEquipmentCondition warrantyEquipmentCondition);
	
	List<WarrantyEquipment> findWarrantyEquipmentByCondition(WarrantyEquipmentCondition warrantyEquipmentCondition, Page page);
	
	List<WarrantyEquipment> findWarrantyEquipmentByCondition(WarrantyEquipmentCondition warrantyEquipmentCondition, Order order);
	
	Long count();
	
	Long count(WarrantyEquipmentCondition warrantyEquipmentCondition);
	
}
