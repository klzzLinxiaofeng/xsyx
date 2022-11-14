package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Questionnaire;
import platform.education.generalTeachingAffair.vo.QuestionnaireCondition;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/10/12 16:22
 * @Description: 问卷管理
 */
public interface QuestionnaireService {

    List<Questionnaire> findQuestionnaireVoByCondition(QuestionnaireCondition condition, Page page, Order order);

    /**
     * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
     * @param publicClass
     * @return
     */
    String abandon(Questionnaire publicClass);

    Questionnaire add(Questionnaire publicClass);

    Questionnaire findQuestionnaireVoById(Integer id);

    Questionnaire modify(Questionnaire publicClass);
}
