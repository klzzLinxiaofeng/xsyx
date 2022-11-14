package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.TextbookManager;
import platform.education.generalTeachingAffair.vo.TextbookManagerCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface TextbookManagerDao extends GenericDao<TextbookManager, java.lang.Integer> {

	List<TextbookManager> findTextbookManagerByCondition(TextbookManagerCondition textbookManagerCondition, Page page, Order order);
	
	TextbookManager findById(Integer id);
	
	Long count(TextbookManagerCondition textbookManagerCondition);
	
}
