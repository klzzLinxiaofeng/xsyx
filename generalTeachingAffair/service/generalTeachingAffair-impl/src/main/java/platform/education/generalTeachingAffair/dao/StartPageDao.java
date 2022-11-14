package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
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
 * @Date: 2020/12/14 9:58
 * @Description:
 */
public interface StartPageDao extends GenericDao<StartPagePojo, Integer> {

    List<StartPagePojo> findStartPageVoByCondition(StartPagePojo startPagePojo, Page page, Order order);

    StartPagePojo findById(Integer id);

    void modify(StartPagePojo startPagePojo);
}
