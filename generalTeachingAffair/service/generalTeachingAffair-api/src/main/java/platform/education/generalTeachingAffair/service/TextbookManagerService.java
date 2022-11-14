package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.TextbookManager;
import platform.education.generalTeachingAffair.vo.TextbookManagerCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface TextbookManagerService {
    TextbookManager findTextbookManagerById(Integer id);
	   
	TextbookManager add(TextbookManager textbookManager);
	   
	TextbookManager modify(TextbookManager textbookManager);
	   
	void remove(TextbookManager textbookManager);
	   
	List<TextbookManager> findTextbookManagerByCondition(TextbookManagerCondition textbookManagerCondition, Page page, Order order);
	
	List<TextbookManager> findTextbookManagerByCondition(TextbookManagerCondition textbookManagerCondition);
	
	List<TextbookManager> findTextbookManagerByCondition(TextbookManagerCondition textbookManagerCondition, Page page);
	
	List<TextbookManager> findTextbookManagerByCondition(TextbookManagerCondition textbookManagerCondition, Order order);
	
	Long count();
	
	Long count(TextbookManagerCondition textbookManagerCondition);
	
}
