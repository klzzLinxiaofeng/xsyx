package platform.education.oa.service;
import platform.education.oa.model.RepairFile;
import platform.education.oa.vo.RepairFileCondition;
import platform.education.oa.vo.RepairFileVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface RepairFileService {
    RepairFile findRepairFileById(Integer id);
	   
	RepairFile add(RepairFile repairFile);
	   
	RepairFile modify(RepairFile repairFile);
	   
	void remove(RepairFile repairFile);
	   
	List<RepairFile> findRepairFileByCondition(RepairFileCondition repairFileCondition, Page page, Order order);
	
	List<RepairFile> findRepairFileByCondition(RepairFileCondition repairFileCondition);
	
	List<RepairFile> findRepairFileByCondition(RepairFileCondition repairFileCondition, Page page);
	
	List<RepairFile> findRepairFileByCondition(RepairFileCondition repairFileCondition, Order order);
	
	Long count();
	
	Long count(RepairFileCondition repairFileCondition);

	List<RepairFileVo> findRepairFileAllByCondition(RepairFileCondition repairFileCondition);
	
}
