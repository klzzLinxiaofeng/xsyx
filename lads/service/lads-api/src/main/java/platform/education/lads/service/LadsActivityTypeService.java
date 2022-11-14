package platform.education.lads.service;

import platform.education.lads.model.LadsActivityType;
import platform.education.lads.vo.LadsActivityTypeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LadsActivityTypeService {

    LadsActivityType findLadsActivityTypeById(Integer id);

    LadsActivityType add(LadsActivityType ladsActivityType);

    LadsActivityType modify(LadsActivityType ladsActivityType);

    void remove(LadsActivityType ladsActivityType);

    List<LadsActivityType> findLadsActivityTypeByCondition(LadsActivityTypeCondition ladsActivityTypeCondition, Page page, Order order);

    List<LadsActivityType> findLadsActivityTypeByCondition(LadsActivityTypeCondition ladsActivityTypeCondition);

    List<LadsActivityType> findLadsActivityTypeByCondition(LadsActivityTypeCondition ladsActivityTypeCondition, Page page);

    List<LadsActivityType> findLadsActivityTypeByCondition(LadsActivityTypeCondition ladsActivityTypeCondition, Order order);

    Long count();

    Long count(LadsActivityTypeCondition ladsActivityTypeCondition);

    void remove(LadsActivityTypeCondition ladsActivityTypeCondition);

    //以下是业务方法
    LadsActivityType findLadsActivityTypeByUuid(String uuid);
}
