package platform.education.paper.service;
import java.util.List;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.PaPaper;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.PaPaperCondition;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.QuestionKnoledgeScoreVo;

import java.util.List;

public interface PaPaperService {
    PaPaper findPaPaperById(Integer id);
	   
	PaPaper add(PaPaper paPaper);
	   
	PaPaper modify(PaPaper paPaper);
	
	PaPaper modifyNotWithModifyDate(PaPaper paPaper);
	   
	void remove(PaPaper paPaper);
	
	List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page, Order order);
	
	List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition);
	
	List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page);
	
	List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Order order);
	
	Long count();
	
	Long count(PaPaperCondition paPaperCondition);

	List<PaPaperVo> findPaPaperByConditions(Integer userId, String schooUUID, String libType, String stagecode,
			String subjectcode, String type, String code, String textbookId, Page page, Order order);

	/**
	 * 通过试卷id找到每个科目码对应的分数
	 * @param paperId
	 * @return
	 */
	List<QuestionKnoledgeScoreVo> findQuestionKnoledgeScoreByPaperId(Integer paperId);

	PaPaper findPaPaperByUUid(String paperUuid);

	void removeAllPaperInfo(PaPaper paPaper);
	
	/**
	 * 一份或多份处理好的试卷里的所有题目 与 个别复数题目 组成新的试卷(json)
	 * @param papers     题目处理过的试卷（控制层处理）
	 * @param questionIds  组进来的题目
	 * @return
	 */
	String consturctNewPaper(List <PaPaper> papers, List <Integer> questionIds);

	List<PaPaperVo> findPaPaperByCatalogCodes(String[] codes, Page page);
	
	List<PaPaper> findMyUploadPaper(Integer userId, Page page, Order order);

	List<PaPaper> findMyFavPaper(Integer userId, Page page, Order order);

	List<PaPaperVo> findMyPaper(Integer userId, String libType, String stageCode, String subjectCode, Page page,
			Order order);
	String findStageCodeByPaperId(Integer paperId);
	
	/**
	 * @function 根据试卷UUID获取试卷中的知识点总分
	 * @param paperUuid
	 * @return
     */
	List<KnowledgeCountVo> findKnowledgeCountByPaperUuid(Integer paperId);
	
	/**
	 * 通过数据库的数据组装json
	 * @param paperId
	 * @return
	 */
	String findPaperJsonByDb(Integer paperId);
	
	List<PaPaper> findPaperByUuids(String[] paperUuids);
}
