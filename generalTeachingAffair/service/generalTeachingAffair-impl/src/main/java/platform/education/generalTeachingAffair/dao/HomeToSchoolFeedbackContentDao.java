package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.HomeToSchoolFeedbackContent;

/**
 * @author Eternityhua
 * @create 2020-12-06 16:04
 */
public interface HomeToSchoolFeedbackContentDao extends GenericDao<HomeToSchoolFeedbackContent, Integer> {

    HomeToSchoolFeedbackContent create(HomeToSchoolFeedbackContent entity);

    void delete(HomeToSchoolFeedbackContent entity);



}
