package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ComplainFile;
import platform.education.generalTeachingAffair.vo.ComplainFileCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ComplainFileDao extends GenericDao<ComplainFile, Integer> {

	List<ComplainFile> findComplainFileByCondition(ComplainFileCondition complainFileCondition, Page page, Order order);
	
	ComplainFile findById(Integer id);
	
	Long count(ComplainFileCondition complainFileCondition);

	List<ComplainFile> findByComplainId(Integer complainId);
	
}
