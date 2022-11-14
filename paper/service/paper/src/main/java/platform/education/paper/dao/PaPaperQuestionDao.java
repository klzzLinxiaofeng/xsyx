package platform.education.paper.dao;

import platform.education.paper.model.PaPaperQuestion;
import platform.education.paper.vo.PaPaperQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import java.util.List;
import platform.education.paper.vo.FindByPaperIdAndPosCondition;
import platform.education.paper.vo.FindCollectQuestionListCondition;
import platform.education.paper.vo.FindWrongQuestionListCondition;

public interface PaPaperQuestionDao extends GenericDao<PaPaperQuestion, java.lang.Integer> {

    List<PaPaperQuestion> findPaPaperQuestionByCondition(PaPaperQuestionCondition paPaperQuestionCondition, Page page, Order order);

    PaPaperQuestion findById(Integer id);

    Long count(PaPaperQuestionCondition paPaperQuestionCondition);

    void deleteByCondition(PaPaperQuestionCondition paPaperQuestionCondition);

    //以下为业务方法
    PaPaperQuestion findByUuid(String uuid);

    void deleteNotInUpdateQuestions(String paId, List<String> ids);

    void deleteChildren(String paperId);

    List<PaPaperQuestion> findByPaperId(String paperId, Page page, Order order);

    List<String> findIdByPaperId(String paperId, Page page, Order order);

    List<PaPaperQuestion> findByPaperIdAndPos(FindByPaperIdAndPosCondition pac, Page page, Order order);

    List<PaPaperQuestion> findWrongQuestionList(FindWrongQuestionListCondition wqlc, Page page, Order order);

    List<PaPaperQuestion> findCollectQuestionList(FindCollectQuestionListCondition cqlc, Page page, Order order);

    Long countByPaperId(String paperId);
}
