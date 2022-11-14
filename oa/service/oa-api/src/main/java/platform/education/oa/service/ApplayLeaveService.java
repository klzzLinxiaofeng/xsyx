package platform.education.oa.service;
import platform.education.oa.model.ApplayLeave;
import platform.education.oa.vo.ApplayLeaveCondition;
import platform.education.oa.vo.ApplayLeaveVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ApplayLeaveService {
    ApplayLeave findApplayLeaveById(Integer id);
    
    ApplayLeaveVo findApplayLeaveVoById(Integer id);
	   
	ApplayLeave add(ApplayLeave applayLeave);
	   
	ApplayLeave modify(ApplayLeave applayLeave);
	   
	void remove(ApplayLeave applayLeave);
	   
	List<ApplayLeave> findApplayLeaveByCondition(ApplayLeaveCondition applayLeaveCondition, Page page, Order order);
	
	List<ApplayLeave> findApplayLeaveByCondition(ApplayLeaveCondition applayLeaveCondition);
	
	List<ApplayLeave> findApplayLeaveByCondition(ApplayLeaveCondition applayLeaveCondition, Page page);
	
	List<ApplayLeave> findApplayLeaveByCondition(ApplayLeaveCondition applayLeaveCondition, Order order);
	
	List<ApplayLeaveVo> findApplayLeaveVoByCondition(ApplayLeaveCondition applayLeaveCondition, Page page, Order order);
	
    List<ApplayLeaveVo> findApplayLeaveTJByCondition(ApplayLeaveCondition applayLeaveCondition, Page page, Order order);

	Long count();
	
	Long count(ApplayLeaveCondition applayLeaveCondition);
	
}
