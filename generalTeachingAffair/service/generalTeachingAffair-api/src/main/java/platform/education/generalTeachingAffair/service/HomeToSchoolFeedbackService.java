package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.HomeToSchoolFeedback;

import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-03 18:30
 */
public interface HomeToSchoolFeedbackService {



    /**
     * 当前接口crud操作 成功时返回的状态值
     */
    public final static String OPERATE_SUCCESS = "success";

    /**
     * 当前接口crud操作 失败时返回的状态值
     */
    public final static String OPERATE_FAIL = "fail";

    /**
     * 系统异常造成的操作失败 系统返回的状态值
     */
    public final static String OPERATE_ERROR = "error";

    List<HomeToSchoolFeedback> findHomeToSchoolFeedbackByCondition(HomeToSchoolFeedback homeToSchoolFeedback, Page page, Order order);

    //String abandon(HomeToSchoolFeedback entity);
    void abandon(Integer id);



    HomeToSchoolFeedback findContentById(Integer id);

    HomeToSchoolFeedback findHomeToSchoolFeedbackByName(String name);

    void updateCondition(Integer id);
    String updateTeacher(Integer id,Integer teacherId);
}
