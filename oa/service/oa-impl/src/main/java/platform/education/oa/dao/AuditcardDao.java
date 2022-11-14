package platform.education.oa.dao;

import platform.education.oa.model.Auditcard;
import platform.education.oa.vo.AuditcardCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface AuditcardDao extends GenericDao<Auditcard, java.lang.Integer> {

	List<Auditcard> findAuditcardByCondition(AuditcardCondition auditcardCondition, Page page, Order order);
	
	Auditcard findById(Integer id);
	
	Long count(AuditcardCondition auditcardCondition);
	
}
