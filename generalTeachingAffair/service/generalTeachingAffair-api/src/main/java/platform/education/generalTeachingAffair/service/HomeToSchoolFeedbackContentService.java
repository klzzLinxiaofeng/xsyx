package platform.education.generalTeachingAffair.service;

import org.apache.ibatis.annotations.Param;
import platform.education.generalTeachingAffair.model.HomeToSchoolFeedback;
import platform.education.generalTeachingAffair.model.HomeToSchoolFeedbackContent;

/**
 * @author Eternityhua
 * @create 2020-12-06 15:39
 */
public interface HomeToSchoolFeedbackContentService {


    HomeToSchoolFeedbackContent createBatch(HomeToSchoolFeedbackContent entity);


    void deleteById(Integer feedbackId);
}
