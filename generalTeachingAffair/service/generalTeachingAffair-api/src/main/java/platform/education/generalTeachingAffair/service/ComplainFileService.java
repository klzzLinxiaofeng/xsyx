package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.ComplainFile;
import platform.education.generalTeachingAffair.vo.ComplainFileCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ComplainFileService {
    ComplainFile findComplainFileById(Integer id);
	   
	ComplainFile add(ComplainFile complainFile);
	   
	ComplainFile modify(ComplainFile complainFile);
	   
	void remove(ComplainFile complainFile);
	   
	List<ComplainFile> findComplainFileByCondition(ComplainFileCondition complainFileCondition, Page page, Order order);
	
	List<ComplainFile> findComplainFileByCondition(ComplainFileCondition complainFileCondition);
	
	List<ComplainFile> findComplainFileByCondition(ComplainFileCondition complainFileCondition, Page page);
	
	List<ComplainFile> findComplainFileByCondition(ComplainFileCondition complainFileCondition, Order order);
	
	Long count();
	
	Long count(ComplainFileCondition complainFileCondition);

	List<ComplainFile> findByComplainId(Integer complainId);
	
}
