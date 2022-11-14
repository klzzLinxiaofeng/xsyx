package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.CanteenCardPojo;
import platform.education.generalTeachingAffair.model.StudentAskingPojo;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/1/8 16:55
 * @Description: 学生请假
 */
public interface StudentAskingService {
    List<StudentAskingPojo> findCanteenByCondition(StudentAskingPojo condition, Page page, Order order);

}
