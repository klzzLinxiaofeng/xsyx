package platform.education.oa.service;
import platform.education.oa.model.Repair;
import platform.education.oa.vo.RepairCondition;
import platform.education.oa.vo.RepairVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface RepairService {
    Repair findRepairById(Integer id);
	   
	Repair add(Repair repair);
	   
	Repair modify(Repair repair);
	   
	void remove(Repair repair);
	   
	List<Repair> findRepairByCondition(RepairCondition repairCondition, Page page, Order order);
	
	List<Repair> findRepairByCondition(RepairCondition repairCondition);
	
	List<Repair> findRepairByCondition(RepairCondition repairCondition, Page page);
	
	List<Repair> findRepairByCondition(RepairCondition repairCondition, Order order);
	
	Long count();
	
	Long count(RepairCondition repairCondition);

	List<RepairVo> findRepairAllByCondition(RepairCondition condition,Page page, Order order);
	
}
