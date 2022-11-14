package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Questionnaire;
import platform.education.generalTeachingAffair.model.StartPagePojo;
import platform.education.generalTeachingAffair.vo.QuestionnaireCondition;

import java.util.List;

/**
 * 启动页管理
 *
 * @author: yhc
 * @Date: 2020/12/13 17:35
 * @Description:
 */
public interface StartPageService {

    List<StartPagePojo> findQuestionnaireVoByCondition(StartPagePojo condition, Page page, Order order);

    /**
     * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
     *
     * @param publicClass
     * @return
     */
    String abandon(StartPagePojo publicClass);

    StartPagePojo add(StartPagePojo publicClass);

    StartPagePojo findStartPagePojoVoById(Integer id);

    StartPagePojo modify(StartPagePojo publicClass);
}
