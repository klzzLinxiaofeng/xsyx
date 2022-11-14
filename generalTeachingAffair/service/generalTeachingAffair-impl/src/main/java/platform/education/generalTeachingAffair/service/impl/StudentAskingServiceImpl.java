package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.StudentAskingDao;
import platform.education.generalTeachingAffair.model.StudentAskingPojo;
import platform.education.generalTeachingAffair.service.StudentAskingService;

import java.util.List;

/**
 * 补卡管理
 *
 * @author: yhc
 * @Date: 2020/12/3 17:21
 * @Description:
 */
public class StudentAskingServiceImpl implements StudentAskingService {
    private StudentAskingDao studentAskingDao;

    public void setStudentAskingDao(StudentAskingDao studentAskingDao) {
        this.studentAskingDao = studentAskingDao;
    }

    @Override
    public List<StudentAskingPojo> findCanteenByCondition(StudentAskingPojo condition, Page page, Order order) {
        return this.studentAskingDao.findCanteenByCondition(condition, page, order);
    }
}
