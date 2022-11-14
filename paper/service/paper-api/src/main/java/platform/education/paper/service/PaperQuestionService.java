package platform.education.paper.service;
import platform.education.paper.model.PaperQuestion;
import platform.education.paper.model.PaperQuestionResult;
import platform.education.paper.model.PaperQuestionResultJson;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.vo.PaperQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import java.util.Map;

public interface PaperQuestionService {
    PaperQuestion findPaperQuestionById(Integer id);
	   
	PaperQuestion add(PaperQuestion paperQuestion);
	   
	PaperQuestion modify(PaperQuestion paperQuestion);
	   
	void remove(PaperQuestion paperQuestion);
	   
	List<PaperQuestion> findPaperQuestionByCondition(PaperQuestionCondition paperQuestionCondition, Page page, Order order);
	
	List<PaperQuestion> findPaperQuestionByCondition(PaperQuestionCondition paperQuestionCondition);
	
	List<PaperQuestion> findPaperQuestionByCondition(PaperQuestionCondition paperQuestionCondition, Page page);
	
	List<PaperQuestion> findPaperQuestionByCondition(PaperQuestionCondition paperQuestionCondition, Order order);
	
	Long count();
	
	Long count(PaperQuestionCondition paperQuestionCondition);
	
	/**
	 * 
	* @Title: findPaperQuestionByPaperUuId
	* @author pantq 
	* @Description:  根据paperUuId 查询试卷下的所有题目
	* @param paperUuid
	* @return    设定文件 
	* @return List<PaperQuestionResult>    返回类型 
	* @throws
	 */
	List<PaperQuestionResult> findPaperQuestionByPaperId(Integer paperId,String paperUuId);

	PaperQuestion findPaperQuestionByUuid(String questionUuid);

	Map<String, PaperQuestion> findPaperQuestionMapByUUIDs(String[] questionUUIDs);

	List<PaperQuestion> findPaperQuestionByUUIDs(String[] questionUUIDs);

}
