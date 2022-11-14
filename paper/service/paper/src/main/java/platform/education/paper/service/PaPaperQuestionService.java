package platform.education.paper.service;

import platform.education.paper.model.PaPaperQuestion;
import platform.education.paper.vo.PaPaperQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import platform.education.paper.vo.FindByPaperIdAndPosCondition;
import platform.education.paper.vo.FindCollectQuestionListCondition;
import platform.education.paper.vo.FindWrongQuestionListCondition;

public interface PaPaperQuestionService {

    PaPaperQuestion findPaPaperQuestionById(Integer id);

    PaPaperQuestion add(PaPaperQuestion paPaperQuestion);

    PaPaperQuestion modify(PaPaperQuestion paPaperQuestion);

    void remove(PaPaperQuestion paPaperQuestion);

    List<PaPaperQuestion> findPaPaperQuestionByCondition(PaPaperQuestionCondition paPaperQuestionCondition, Page page, Order order);

    List<PaPaperQuestion> findPaPaperQuestionByCondition(PaPaperQuestionCondition paPaperQuestionCondition);

    List<PaPaperQuestion> findPaPaperQuestionByCondition(PaPaperQuestionCondition paPaperQuestionCondition, Page page);

    List<PaPaperQuestion> findPaPaperQuestionByCondition(PaPaperQuestionCondition paPaperQuestionCondition, Order order);

    Long count();

    Long count(PaPaperQuestionCondition paPaperQuestionCondition);

    void remove(PaPaperQuestionCondition paPaperQuestionCondition);
    
    //以下为业务方法
    PaPaperQuestion findPaPaperQuestionByUuid(String id);

    void deleteNotInUpdateQuestions(String paId, List<String> ids);

    void deleteChildren(String paperId);

    List<PaPaperQuestion> findByPaperId(String paperId, Page page, Order order);

    List<String> findIdByPaperId(String paperId, Page page, Order order);

    List<PaPaperQuestion> findWrongQuestionList(FindWrongQuestionListCondition wqlc, Page page, Order order);

    List<PaPaperQuestion> findByPaperIdAndPos(FindByPaperIdAndPosCondition pac, Page page, Order order);

    PaPaperQuestion getParentPaperQuestion(PaPaperQuestion pq);

    List<PaPaperQuestion> findCollectQuestionList(FindCollectQuestionListCondition cqlc, Page page, Order order);

    Long countByPaperId(String paperId);
}
