package platform.education.paper.service;
import platform.education.paper.model.PaUserFile;
import platform.education.paper.vo.PaUserFileCondition;
import platform.education.paper.vo.PaUserFileVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaUserFileService {
    PaUserFile findPaUserFileById(Integer id);
	   
	PaUserFile add(PaUserFile paUserFile);
	   
	PaUserFile modify(PaUserFile paUserFile);
	   
	void remove(PaUserFile paUserFile);
	   
	List<PaUserFile> findPaUserFileByCondition(PaUserFileCondition paUserFileCondition, Page page, Order order);
	
	List<PaUserFile> findPaUserFileByCondition(PaUserFileCondition paUserFileCondition);
	
	List<PaUserFile> findPaUserFileByCondition(PaUserFileCondition paUserFileCondition, Page page);
	
	List<PaUserFile> findPaUserFileByCondition(PaUserFileCondition paUserFileCondition, Order order);
	
	Long count();
	
	Long count(PaUserFileCondition paUserFileCondition);
	
	List<PaUserFileVo> findPaUserFilesByUuids(String[] uuids);
	
}
