package platform.education.paper.service;
import platform.education.paper.model.PaUserPaper;
import platform.education.paper.vo.PaUserPaperCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaUserPaperService {
    PaUserPaper findPaUserPaperById(Integer id);
	   
	PaUserPaper add(PaUserPaper paUserPaper);
	   
	PaUserPaper modify(PaUserPaper paUserPaper);
	   
	void remove(PaUserPaper paUserPaper);
	   
	List<PaUserPaper> findPaUserPaperByCondition(PaUserPaperCondition paUserPaperCondition, Page page, Order order);
	
	List<PaUserPaper> findPaUserPaperByCondition(PaUserPaperCondition paUserPaperCondition);
	
	List<PaUserPaper> findPaUserPaperByCondition(PaUserPaperCondition paUserPaperCondition, Page page);
	
	List<PaUserPaper> findPaUserPaperByCondition(PaUserPaperCondition paUserPaperCondition, Order order);
	
	Long count();
	
	Long count(PaUserPaperCondition paUserPaperCondition);
	
}
