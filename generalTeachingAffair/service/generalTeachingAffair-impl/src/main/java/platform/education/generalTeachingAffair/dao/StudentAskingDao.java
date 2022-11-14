package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.CanteenCardPojo;
import platform.education.generalTeachingAffair.model.StudentAskingPojo;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/1/8 17:06
 * @Description:
 */
public interface StudentAskingDao extends GenericDao<StudentAskingPojo, Integer> {
    /**
     * 获取所有
     *
     * @param condition
     * @param page
     * @param order
     * @return
     */
    List<StudentAskingPojo> findCanteenByCondition(StudentAskingPojo condition, Page page, Order order);
}
