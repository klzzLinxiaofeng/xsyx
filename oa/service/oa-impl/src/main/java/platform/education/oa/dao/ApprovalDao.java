package platform.education.oa.dao;

import platform.education.oa.model.Approval;
import platform.education.oa.vo.ApprovalCondition;
import platform.education.oa.vo.ApprovalVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApprovalDao extends GenericDao<Approval, java.lang.Integer> {

	List<ApprovalVo> findApprovalByCondition(ApprovalCondition approvalCondition, Page page, Order order);
	
	Approval findById(Integer id);
	
	Long count(ApprovalCondition approvalCondition);
	
}
