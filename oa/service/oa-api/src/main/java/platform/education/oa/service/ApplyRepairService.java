package platform.education.oa.service;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.oa.model.ApplyRepair;
import platform.education.oa.vo.ApplyRepairCondition;
import platform.education.oa.vo.ApplyRepairVo;

import java.util.List;

public interface ApplyRepairService {
    ApplyRepair findApplyRepairById(Integer id);
	   
	ApplyRepair add(ApplyRepair applyRepair);
	   
	ApplyRepair modify(ApplyRepair applyRepair);
	   
	void remove(ApplyRepair applyRepair);
	   
	List<ApplyRepair> findApplyRepairByCondition(ApplyRepairCondition applyRepairCondition, Page page, Order order);
	
	List<ApplyRepair> findApplyRepairByCondition(ApplyRepairCondition applyRepairCondition);
	
	List<ApplyRepair> findApplyRepairByCondition(ApplyRepairCondition applyRepairCondition, Page page);
	
	List<ApplyRepair> findApplyRepairByCondition(ApplyRepairCondition applyRepairCondition, Order order);
	
	Long count();
	
	Long count(ApplyRepairCondition applyRepairCondition);
	
	List<ApplyRepairVo> findApplyAndAcceptRepairByCondition(ApplyRepairCondition applyRepairCondition, Page page, Order order);

	ApplyRepair findApplyAndAcceptRepairById(Integer id);

	List<ApplyRepairVo> findRepairHasApprovalByTeacherId(Integer teacherId,
			Page page, Order order);

	List<ApplyRepair> findByshenhe(String shenqingren, Integer shenheId,Integer typeId,Integer isShenhe,Page page);
	ApplyRepair findByshenheId(Integer id);
	String updateShenhe(Integer id,Integer weixiugong,String liyou,Integer isshenhe);
}
