package platform.education.paper.service;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.vo.AssemblyGroupJson;
import platform.education.paper.vo.AssemblyPaperJson;
import platform.education.paper.vo.PaQuestionCondition;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.PaperQuestionTree;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface PaQuestionService {
    PaQuestion findPaQuestionById(Integer id);
	   
	PaQuestion add(PaQuestion paQuestion);
	   
	PaQuestion modify(PaQuestion paQuestion);

	PaQuestion modifyNotWithModifyDate(PaQuestion paQuestion);
	   
	void remove(PaQuestion paQuestion);
	   
	List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page, Order order);
	
	List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition);
	
	List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page);
	
	List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Order order);
	
	Long count();
	
	Long count(PaQuestionCondition paQuestionCondition);
	
	List<PaQuestionVo> findPaperQuestionByPaperIds(Integer[] paperIds);
	
	List<PaQuestion> findPaQuestionsByTypeCode(PaQuestionCondition condition,Integer type,String code,Page page,Order order);
	
	PaQuestionVo findPaQuestionsVoById(Integer id);
	
   /**
    * 根据paperId去找题目
    * @param paperId   试卷ID
    * @param complex  是否需要复合题的题干的题目，1为需要，0为不需要
    * @param order       排序方式
    * @return
    */
	List<PaQuestionVo>  findPaQuestionVoByPaperId(Integer paperId,Integer complex,Order order);

	void removeByPaperId(Integer paperId);
	
	/**
	 * 根据paperUuids和paperId去找到对应题目
	 * @param questionUuids
	 * @param paperId
	 * @return
	 */
	List<PaQuestionVo> findPaQuestionByUUIDs(String[] questionUuids,Integer paperId);
	
	/**
	 * 根据paperId找出所有对应题目(Vo包含对应分数)
	 * @param paperId
	 * @return
	 */
	List<PaQuestionVo> findPaQuestionListbyPaPaperId (Integer paperId);
	
	/**
	 * 找对应题目（Vo含分数）
	 * @param paperId
	 * @param questionId
	 * @return
	 */
	PaQuestionVo findPaQuestionById(Integer paperId, Integer questionId);
	
	/**
	 * 根据questionUuids和paperId去得到对应题目集的Json数据
	 * @param questionUuids
	 * @param paperId
	 * @return
	 */
	List<String> getJsonDataFromPaQuestionByUUIDs(String[] questionUuids,Integer paperId);
	
	/**
	 * 根据questionId得到对应题目Json数据
	 * @param questionId
	 * @return
	 */
	String getJsonDataFromPaQuestionById(Integer questionId);
	
	/**
	 * 添加单题到questionList里面
	 * @param paQuestionList 已有试题集
	 * @param paperId     新加的原试卷id
	 * @param questionId  新加的试题id
	 * @return
	 */
	List<PaQuestionVo> addQuestionToPaQuestionList (List<PaQuestionVo> paQuestionList ,Integer paperId, Integer questionId);
	/**
	 * 
	 * @param paperId
	 * @param order
	 * @param function  1给app用得，0给页面用的
	 * @return
	 */
	List<PaperQuestionTree> findPaperQuestionTreeByPaperId(Integer paperId,Order order,Integer function);

	/**
	 * 根据uuid去获取试题详细信息
	 * @param questionUUIDs
	 * @return
	 */
	List<PaQuestionVo> findPaperQuestionByUUIDs(Object[] questionUUIDs);
	
	List<PaQuestionVo> findPaQuestionVoByPaperId(Integer id);
	
	List<PaQuestion> findPaQuestionByIds(Integer[] ids);

	PaQuestionVo findQuestionDetail(Integer id);

	List<PaQuestionVo> relatedQuestion(Integer questionId, int number);

	List<PaQuestionVo> historyQuestion(String uuid, int number);

	List<PaQuestionVo> findPaQuestionList(Integer userId, String schooUUID, String libType, String difficulity,
            String questionType, String stageCode, String subjectCode, String type, String code, String textbookId,
            Page page, Order order);

	List<PaQuestionVo> findPaperQuestionByParentId(Integer parentId) throws IllegalAccessException, InvocationTargetException;
	
	PaQuestion findPaperQuestionByUuid(String uuid);

	List<PaQuestionVo> findPaperQuestionByIds(Integer[] ids);
	/**
	 * 展示pos值的问题详情
	 * @param uuid
	 * @return
	 */
	PaQuestionVo findPosQuestionDetail(Integer paperId,String uuid);

	List<PaQuestionVo> findMyQuestion(Integer userId, String libType, String stageCode, String subjectCode, Page page,
			Order order);

	PaQuestionVo findPaQuestionAllInfoById(Integer questionId);
	
	
	Map<Integer,Object> findPaQuetionKnowledgeNodeNameByIds(Integer[] ids);

	void updateUsedByquestionIdList(List<Integer> questionIdList);

	void modifyRightAnswerCountBatch(List<PaQuestionVo> paQuestionVos);

//	List<PaQuestionVo> handleQuestionWithBasket(List<PaQuestionVo> questionList, Integer integer);

}
