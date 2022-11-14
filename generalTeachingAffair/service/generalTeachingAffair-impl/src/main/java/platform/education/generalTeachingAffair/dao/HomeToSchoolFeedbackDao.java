package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.HomeToSchoolFeedback;

import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-03 18:26
 */
public interface HomeToSchoolFeedbackDao extends GenericDao<HomeToSchoolFeedback, Integer> {

    List<HomeToSchoolFeedback> findHomeToSchoolFeedbackByCondition(HomeToSchoolFeedback homeToSchoolFeedback, Page page, Order order);


    HomeToSchoolFeedback findById(Integer id);

    HomeToSchoolFeedback findByName(String name);

    void updateCondition(Integer isReplay);

    void updateDelCondition(Integer id);
    Integer updateTeacher(Integer id,Integer teacherId);
}
