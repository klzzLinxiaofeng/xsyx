package platform.education.oa.dao;

import platform.education.oa.model.ApplayLeave;
import platform.education.oa.vo.ApplayLeaveCondition;
import platform.education.oa.vo.ApplayLeaveVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApplayLeaveDao extends GenericDao<ApplayLeave, java.lang.Integer> {

	List<ApplayLeave> findApplayLeaveByCondition(ApplayLeaveCondition applayLeaveCondition, Page page, Order order);
	
	List<ApplayLeaveVo> findApplayLeaveVoByCondition(ApplayLeaveCondition applayLeaveCondition, Page page, Order order);
	
	List<ApplayLeaveVo> findApplayLeaveTJByCondition(ApplayLeaveCondition applayLeaveCondition, Page page, Order order);
	
	
	ApplayLeave findById(Integer id);
	
	ApplayLeaveVo findByVoId(Integer id);
	
	Long count(ApplayLeaveCondition applayLeaveCondition);
	
}
