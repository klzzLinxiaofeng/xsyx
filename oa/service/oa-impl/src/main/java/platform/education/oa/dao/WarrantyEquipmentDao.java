package platform.education.oa.dao;

import platform.education.oa.model.WarrantyEquipment;
import platform.education.oa.vo.WarrantyEquipmentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface WarrantyEquipmentDao extends GenericDao<WarrantyEquipment, java.lang.Integer> {

	List<WarrantyEquipment> findWarrantyEquipmentByCondition(WarrantyEquipmentCondition warrantyEquipmentCondition, Page page, Order order);
	
	WarrantyEquipment findById(Integer id);
	
	Long count(WarrantyEquipmentCondition warrantyEquipmentCondition);
	
}
