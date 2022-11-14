package platform.education.oa.service;
import platform.education.oa.model.Auditcard;
import platform.education.oa.vo.AuditcardCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface AuditcardService {
    Auditcard findAuditcardById(Integer id);
	   
	Auditcard add(Auditcard auditcard);
	   
	Auditcard modify(Auditcard auditcard);
	   
	void remove(Auditcard auditcard);
	   
	List<Auditcard> findAuditcardByCondition(AuditcardCondition auditcardCondition, Page page, Order order);
	
	List<Auditcard> findAuditcardByCondition(AuditcardCondition auditcardCondition);
	
	List<Auditcard> findAuditcardByCondition(AuditcardCondition auditcardCondition, Page page);
	
	List<Auditcard> findAuditcardByCondition(AuditcardCondition auditcardCondition, Order order);
	
	Long count();
	
	Long count(AuditcardCondition auditcardCondition);
	
}
