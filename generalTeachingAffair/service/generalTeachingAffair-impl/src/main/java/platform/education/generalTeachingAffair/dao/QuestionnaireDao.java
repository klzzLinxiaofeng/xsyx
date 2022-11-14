package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Questionnaire;
import platform.education.generalTeachingAffair.vo.QuestionnaireCondition;

import java.util.List;

public interface QuestionnaireDao extends GenericDao<Questionnaire, Integer> {

    List<Questionnaire> findQuestionnaireVoByCondition(QuestionnaireCondition questionnaireCondition, Page page, Order order);

    Questionnaire findById(Integer id);

}
