package platform.education.paper.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.Paper;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.PaperCondition;

import java.util.List;
import java.util.Map;

public interface PaperDao extends GenericDao<Paper, java.lang.Integer> {

	List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page, Order order);
	
	Paper findById(Integer id);
	
	Long count(PaperCondition paperCondition);
	
	Paper findPaperByUuid(String paperUuid);

	List<KnowledgeCountVo> findKnowledgeCountByPaperUuid(String paperUuid);
	
	//批量做题用
	void batchUnitModify(Integer lpId);
	void bachTaskModify(Integer lpId);
	List<Map> findALL();
}
