package platform.education.oa.dao;

import platform.education.oa.model.RepairFile;
import platform.education.oa.vo.RepairFileCondition;
import platform.education.oa.vo.RepairFileVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface RepairFileDao extends GenericDao<RepairFile, java.lang.Integer> {

	List<RepairFile> findRepairFileByCondition(RepairFileCondition repairFileCondition, Page page, Order order);
	
	RepairFile findById(Integer id);
	
	Long count(RepairFileCondition repairFileCondition);

	List<RepairFileVo> findRepairFileAllByCondition(RepairFileCondition repairFileCondition, Page page, Order order);
	
}
