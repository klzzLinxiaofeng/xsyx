package platform.education.paper.dao;

import platform.education.paper.model.PaUserFile;
import platform.education.paper.vo.PaUserFileCondition;
import platform.education.paper.vo.PaUserFileVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaUserFileDao extends GenericDao<PaUserFile, java.lang.Integer> {

	List<PaUserFile> findPaUserFileByCondition(PaUserFileCondition paUserFileCondition, Page page, Order order);
	
	PaUserFile findById(Integer id);
	
	Long count(PaUserFileCondition paUserFileCondition);
	
	public List<PaUserFileVo> findPaUserFilesByUuids(String[] uuids);
	
}
