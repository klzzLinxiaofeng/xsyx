package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.CanteenCardPojo;

import java.util.List;

public interface CanteenCardServiceDao extends GenericDao<CanteenCardPojo, Integer> {
    List<CanteenCardPojo> findCanteenByCondition(CanteenCardPojo condition, Page page, Order order);

    void updateStudentSign(String newCard, String oldCard);
}