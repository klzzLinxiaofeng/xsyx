package platform.education.oa.dao;

import platform.education.oa.model.Repair;
import platform.education.oa.vo.RepairCondition;
import platform.education.oa.vo.RepairVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface RepairDao extends GenericDao<Repair, java.lang.Integer> {

	List<Repair> findRepairByCondition(RepairCondition repairCondition, Page page, Order order);
	
	Repair findById(Integer id);
	
	Long count(RepairCondition repairCondition);

	List<RepairVo> findRepairAllByCondition(RepairCondition condition,
			Page page, Order order);
	
}
