package platform.education.paper.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.PaperQuestion;
import platform.education.paper.model.PaperQuestionResult;
import platform.education.paper.vo.PaperQuestionCondition;

public interface PaperQuestionDao extends GenericDao<PaperQuestion, java.lang.Integer> {

	List<PaperQuestion> findPaperQuestionByCondition(PaperQuestionCondition paperQuestionCondition, Page page, Order order);
	
	PaperQuestion findById(Integer id);
	
	Long count(PaperQuestionCondition paperQuestionCondition);
	
	/**
	 * 
	* @Title: findPaperQuestionByPaperUuId
	* @author pantq 
	* @Description:  根据paperUuId 查询试卷下的所有题目
	* @param paperId 
	* @return    设定文件 
	* @return List<PaperQuestionResult>    返回类型 
	* @throws
	 */
	List<PaperQuestionResult> findPaperQuestionByPaperId(Integer paperId, String questionUuId);

	PaperQuestion findPaperQuestionByUuid(String questionUuid);

	List<PaperQuestion> findPaperQuestionByUUIDs(String[] questionUUIDs);

}
