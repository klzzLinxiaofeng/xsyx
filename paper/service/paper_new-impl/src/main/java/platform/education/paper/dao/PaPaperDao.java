package platform.education.paper.dao;

import platform.education.paper.model.PaPaper;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.PaFavoritesCondition;
import platform.education.paper.vo.PaPaperCatalogCondition;
import platform.education.paper.vo.PaPaperCondition;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.QuestionKnoledgeScoreVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaPaperDao extends GenericDao<PaPaper, java.lang.Integer> {

	List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page, Order order);

	PaPaper findById(Integer id);

	Long count(PaPaperCondition paPaperCondition);

	List<PaPaperVo> findPaPaperByStageSubject(PaPaperCondition ppCondition, PaPaperCatalogCondition pcCondition, Page page,
			Order order);

	List<PaPaperVo> findPaPaperByCode(PaPaperCondition ppCondition, PaPaperCatalogCondition pcCondition, Page page, Order order);
	
	List<PaPaperVo> findPaPaperByConditionsONfavorite(PaPaperCatalogCondition prcondition, Integer userId, PaFavoritesCondition pfcondition, Page page,
			Order order);
	
	List<QuestionKnoledgeScoreVo> findQuestionKnoledgeScoreByPaperId(Integer paperId);

	List<PaPaperVo> findPaPaperByCatalogCodes(String[] codes, Page page);

	List<PaPaper> findMyFavPaper(Integer userId, Page page, Order order);
	
	List<PaPaperVo> findMyUploadPaPaper(PaPaperCatalogCondition pcCondition, Integer userId, Page page, Order order);

	String findStageCodeByPaperId(Integer paperId);
	
	/**
	 * @function 根据试卷UUID获取试卷中的知识点总分
	 * @param paperUuid
	 * @return
     */
	List<KnowledgeCountVo> findKnowledgeCountByPaperUuid(Integer paperId);
	
	
	List<PaPaper> findPaperByUuids(String[] paperUuids);
}
