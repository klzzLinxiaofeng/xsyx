package platform.education.oa.dao;
 
import platform.education.oa.model.Paper;
import platform.education.oa.vo.PaperCondition;
import platform.education.oa.vo.PaperVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaperDao extends GenericDao<Paper, java.lang.Integer> {

	List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page, Order order);
	
	PaperVo findById1(Integer id);
	
	Paper findById(Integer id);
	
	Long count(PaperCondition paperCondition);
	List<Paper>  findPaperToUser(Integer ownerId,String ownerType,
			Integer userId, Page page, Order order); 
	
	List<PaperVo>findPaperByRelated(PaperCondition condition,Page page,Order order);
	
	
}
