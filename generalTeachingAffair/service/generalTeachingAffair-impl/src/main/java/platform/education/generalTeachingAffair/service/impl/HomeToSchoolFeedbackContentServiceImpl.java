package platform.education.generalTeachingAffair.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.HomeToSchoolFeedbackContentDao;
import platform.education.generalTeachingAffair.model.HomeToSchoolFeedbackContent;
import platform.education.generalTeachingAffair.service.HomeToSchoolFeedbackContentService;

import java.util.Date;

/**
 * @author Eternityhua
 * @create 2020-12-06 15:50
 */
public class HomeToSchoolFeedbackContentServiceImpl implements HomeToSchoolFeedbackContentService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private HomeToSchoolFeedbackContentDao homeToSchoolFeedbackContentDao;

    public void setHomeToSchoolFeedbackContentDao(HomeToSchoolFeedbackContentDao homeToSchoolFeedbackContentDao) {
        this.homeToSchoolFeedbackContentDao = homeToSchoolFeedbackContentDao;
    }

    @Override
    public HomeToSchoolFeedbackContent createBatch(HomeToSchoolFeedbackContent entity) {
        entity.setCreateDate(new Date());
        return homeToSchoolFeedbackContentDao.create(entity);
    }

    @Override
    public void deleteById(Integer feedbackId) {
        HomeToSchoolFeedbackContent homeToSchoolFeedbackContent = new HomeToSchoolFeedbackContent();
        homeToSchoolFeedbackContent.setFeedbackId(feedbackId);
        homeToSchoolFeedbackContentDao.delete(homeToSchoolFeedbackContent);
    }

}
