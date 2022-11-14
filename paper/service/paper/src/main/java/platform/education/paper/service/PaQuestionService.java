package platform.education.paper.service;

import platform.education.paper.model.PaQuestion;
import platform.education.paper.vo.PaQuestionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaQuestionService {

    PaQuestion findPaQuestionById(Integer id);

    PaQuestion add(PaQuestion paQuestion);

    PaQuestion modify(PaQuestion paQuestion);

    void remove(PaQuestion paQuestion);

    List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page, Order order);

    List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition);

    List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page);

    List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Order order);

    Long count();

    Long count(PaQuestionCondition paQuestionCondition);

    void remove(PaQuestionCondition paQuestionCondition);
    
    //以下为业务方法
    PaQuestion findPaQuestionByUuid(String uuid);
}
