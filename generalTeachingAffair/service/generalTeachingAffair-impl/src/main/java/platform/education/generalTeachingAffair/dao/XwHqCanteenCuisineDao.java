package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.XwHqCanteenCuisine;
import platform.education.generalTeachingAffair.vo.CanteenCuisineCondition;

import java.util.List;

public interface XwHqCanteenCuisineDao extends GenericDao<XwHqCanteenCuisine, Integer> {

    List<XwHqCanteenCuisine> findxwHqCanteenCuisineByCondition(CanteenCuisineCondition condition, Page page, Order order);

    List<XwHqCanteenCuisine> findByCuisineId(Integer id);
}
