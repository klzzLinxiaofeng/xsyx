package platform.education.oa.dao;

import platform.education.oa.model.ApplyRepair;
import platform.education.oa.vo.ApplyRepairCondition;
import platform.education.oa.vo.ApplyRepairVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApplyRepairDao extends GenericDao<ApplyRepair, java.lang.Integer> {

	List<ApplyRepair> findApplyRepairByCondition(ApplyRepairCondition applyRepairCondition, Page page, Order order);
	
	ApplyRepair findById(Integer id);
	ApplyRepair findByshenheId(Integer id);
	Long count(ApplyRepairCondition applyRepairCondition);
	
	List<ApplyRepairVo> findApplyAndAcceptRepairByCondition(ApplyRepairCondition applyRepairCondition, Page page, Order order);

	ApplyRepair findMoreById(Integer id);

	List<ApplyRepairVo> findRepairHasApprovalByTeacherId(Integer teacherId,
			Page page, Order order);
	 List<ApplyRepair> findByshenhe(String shenqingren,
										  Integer shenheId,
										  Integer typeId, Integer isShenhe, Page page);
	Integer updateShenhe(Integer id, Integer weixiugong, String liyou, Integer isshenhe);
	
}
