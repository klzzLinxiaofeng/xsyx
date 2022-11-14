package platform.education.paper.service;

import platform.education.paper.model.PaUserAnswer;
import platform.education.paper.vo.PaUserAnswerCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import platform.education.paper.model.PaPaperQuestion;
import platform.education.paper.vo.FindDistinctAnswerCondition;
import platform.education.paper.vo.FindDistinctAnswerResult;

public interface PaUserAnswerService {

    PaUserAnswer findPaUserAnswerById(Integer id);

    PaUserAnswer add(PaUserAnswer paUserAnswer);

    PaUserAnswer modify(PaUserAnswer paUserAnswer);

    void remove(PaUserAnswer paUserAnswer);

    List<PaUserAnswer> findPaUserAnswerByCondition(PaUserAnswerCondition paUserAnswerCondition, Page page, Order order);

    List<PaUserAnswer> findPaUserAnswerByCondition(PaUserAnswerCondition paUserAnswerCondition);

    List<PaUserAnswer> findPaUserAnswerByCondition(PaUserAnswerCondition paUserAnswerCondition, Page page);

    List<PaUserAnswer> findPaUserAnswerByCondition(PaUserAnswerCondition paUserAnswerCondition, Order order);

    Long count();

    Long count(PaUserAnswerCondition paUserAnswerCondition);

    void remove(PaUserAnswerCondition paUserAnswerCondition);
    
    //以下是业务方法
    PaUserAnswer findPaUserAnswerByUuid(String uuid);

    PaPaperQuestion getPaperQuestion(PaUserAnswer ua);

    List<FindDistinctAnswerResult> findDistinctAnswer(FindDistinctAnswerCondition ac, Page page, Order order);

    List<String> findDistinctQuestion(FindDistinctAnswerCondition ac, Page page, Order order);
}
