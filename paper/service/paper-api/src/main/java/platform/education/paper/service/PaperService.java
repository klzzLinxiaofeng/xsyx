package platform.education.paper.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.Paper;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.PaperCondition;

import java.util.List;
import java.util.Map;

public interface PaperService {
    Paper findPaperById(Integer id);
	   
	Paper add(Paper paper);
	   
	Paper modify(Paper paper);
	   
	void remove(Paper paper);
	   
	List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page, Order order);
	
	List<Paper> findPaperByCondition(PaperCondition paperCondition);
	
	List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page);
	
	List<Paper> findPaperByCondition(PaperCondition paperCondition, Order order);
	
	Long count();
	
	Long count(PaperCondition paperCondition);
	
	Paper findPaperByUuid(String paperUuid);

	/**
	 * @function 根据试卷UUID获取试卷中的知识点总分
	 * @param paperUuid
	 * @return
     */
	List<KnowledgeCountVo> findKnowledgeCountByPaperUuid(String paperUuid);
	
	//批量做题用
	void batchUnitModify(Integer lpId);
	void bachTaskModify(Integer lpId);
	
	List<Map> findALL();
	
}
