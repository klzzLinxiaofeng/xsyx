package platform.education.paper.dao;

import platform.education.paper.model.PaQuestion;
import platform.education.paper.vo.PaFavoritesCondition;
import platform.education.paper.vo.PaPaperCondition;
import platform.education.paper.vo.PaQuestionCatalogCondition;
import platform.education.paper.vo.PaQuestionCondition;
import platform.education.paper.vo.PaQuestionKnoledgeCondition;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.PaQuetionKnowledgeNodeNameVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaQuestionDao extends GenericDao<PaQuestion, java.lang.Integer> {

	List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page, Order order);
	
	List<PaQuestionVo> findPaQuestionVoByCondition(PaQuestionCondition paQuestionCondition);
	
	PaQuestion findById(Integer id);
	
	Long count(PaQuestionCondition paQuestionCondition);
	
	List<PaQuestion> findQuestionsByKnoWledgeCode(PaQuestionCondition condition,String code,Page page,Order order);
	
	List<PaQuestion> findQuestionsByCatalogCode(PaQuestionCondition condition,String code,Page page,Order order);
	
	List<PaQuestionVo>  findPaQuestionVoByPaperId(Integer paperId,Integer iscomplex,Order order);

	void deleteByIds(Integer[] questionIds);
	
	List<PaQuestionVo> findPaQuestionByUUIDs(String[] questionUuids,Integer paperId);
	
	List<PaQuestionVo> findPaQuestionListbyPaPaperId(Integer paperId);

	PaQuestionVo findPaQuestionById(Integer paperId, Integer questionId);

	List<PaQuestionVo> findPaQuestionByUUIDss(String[] questionUuids, Integer paperId);

	List<PaQuestionVo> findRelatedQuestionById(Integer questionId);
	
	List<PaQuestionVo> findHistoryQuestionById(String  questionUuid);

	List<PaQuestionVo> findPaperQuestionByUUIDs(Object[] questionUUIDs);

	List<PaQuestionVo> findPaQuestionVoByPaperUuid(String paperUuId);

	List<PaQuestionVo> findPaQuestionInfoByPaperId(Integer paperId);

	List<PaQuestionVo> findPaperQuestionByPaperIds(Integer[] paperIds);
	
	List<PaQuestion> findPaQuestionByIds(Integer[] ids);

	List<PaQuestionVo> findPaQuestionOnFavorites(PaQuestionCondition questionCondition,
			PaQuestionCatalogCondition questionCatalogCondition, PaFavoritesCondition pfCondition, Page page,
			Order order);

	List<PaQuestionVo> findPaQuestionByCode(PaQuestionKnoledgeCondition questionKnoledgeCondition,
			PaQuestionCatalogCondition questionCatalogCondition, PaQuestionCondition questionCondition, Page page,
			Order order);

	List<PaQuestionVo> findPaQuestionByStageSubject(PaQuestionCatalogCondition questionCatalogCondition,
			PaQuestionCondition questionCondition, Page page, Order order);

	List<PaQuestionVo> findPaperQuestionByIds(Integer[] ids, Order order);

	List<PaQuestionVo> findByRandom(Integer questionId);
	
	PaQuestion findPaperQuestionByUuid(String uuid);
	
	PaQuestionVo findPosQuestionDetail(Integer paperId,String uuid);

	List<PaQuestionVo> findMyUploadQuestion(PaQuestionCatalogCondition questionCatalogCondition, Integer userId, Page page,
			Order order);
	
    List<PaQuetionKnowledgeNodeNameVo> findPaQuetionKnowledgeNodeNameVo(Integer[] questionId);

	void updateUsedByquestionIdList(Integer[] questionIds);

	void updateRightAnswerCountBatch(List<PaQuestionVo> paQuestionVoArray);
}
