package platform.education.oa.service;
import platform.education.oa.model.Approval;
import platform.education.oa.vo.ApprovalCondition;
import platform.education.oa.vo.ApprovalVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ApprovalService {
    Approval findApprovalById(Integer id);
	   
	Approval add(Approval approval);
	   
	Approval modify(Approval approval);
	   
	void remove(Approval approval);
	   
	List<ApprovalVo> findApprovalByCondition(ApprovalCondition approvalCondition, Page page, Order order);
	
	List<ApprovalVo> findApprovalByCondition(ApprovalCondition approvalCondition);
	
	List<ApprovalVo> findApprovalByCondition(ApprovalCondition approvalCondition, Page page);
	
	List<ApprovalVo> findApprovalByCondition(ApprovalCondition approvalCondition, Order order);
	
	Long count();
	
	Long count(ApprovalCondition approvalCondition);
	
}
