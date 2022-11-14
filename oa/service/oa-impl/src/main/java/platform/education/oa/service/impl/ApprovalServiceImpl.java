package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Approval;
import platform.education.oa.vo.ApprovalCondition;
import platform.education.oa.vo.ApprovalVo;
import platform.education.oa.service.ApprovalService;
import platform.education.oa.dao.ApprovalDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ApprovalServiceImpl implements ApprovalService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApprovalDao approvalDao;

	public void setApprovalDao(ApprovalDao approvalDao) {
		this.approvalDao = approvalDao;
	}
	
	@Override
	public Approval findApprovalById(Integer id) {
		try {
			return approvalDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Approval add(Approval approval) {
		if(approval == null) {
    		return null;
    	}
    	Date createDate = approval.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	approval.setCreateDate(createDate);
    	approval.setModifyDate(createDate);
		return approvalDao.create(approval);
	}

	@Override
	public Approval modify(Approval approval) {
		if(approval == null) {
    		return null;
    	}
    	Date modify = approval.getModifyDate();
    	approval.setModifyDate(modify != null ? modify : new Date());
		return approvalDao.update(approval);
	}
	
	@Override
	public void remove(Approval approval) {
		try {
			approvalDao.delete(approval);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", approval.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApprovalVo> findApprovalByCondition(ApprovalCondition approvalCondition, Page page, Order order) {
		return approvalDao.findApprovalByCondition(approvalCondition, page, order);
	}
	
	@Override
	public List<ApprovalVo> findApprovalByCondition(ApprovalCondition approvalCondition) {
		return approvalDao.findApprovalByCondition(approvalCondition, null, null);
	}
	
	@Override
	public List<ApprovalVo> findApprovalByCondition(ApprovalCondition approvalCondition, Page page) {
		return approvalDao.findApprovalByCondition(approvalCondition, page, null);
	}
	
	@Override
	public List<ApprovalVo> findApprovalByCondition(ApprovalCondition approvalCondition, Order order) {
		return approvalDao.findApprovalByCondition(approvalCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.approvalDao.count(null);
	}

	@Override
	public Long count(ApprovalCondition approvalCondition) {
		return this.approvalDao.count(approvalCondition);
	}

}
