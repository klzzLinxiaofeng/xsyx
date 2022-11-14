package platform.education.lads.service;

import platform.education.lads.model.LadsActivityTransition;
import platform.education.lads.vo.LadsActivityTransitionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import platform.education.lads.model.LadsActivity;
import platform.education.lads.model.LadsLearningdesign;

public interface LadsActivityTransitionService {

    LadsActivityTransition findLadsActivityTransitionById(Integer id);

    LadsActivityTransition add(LadsActivityTransition ladsActivityTransition);

    LadsActivityTransition modify(LadsActivityTransition ladsActivityTransition);

    void remove(LadsActivityTransition ladsActivityTransition);

    List<LadsActivityTransition> findLadsActivityTransitionByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition, Page page, Order order);

    List<LadsActivityTransition> findLadsActivityTransitionByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition);

    List<LadsActivityTransition> findLadsActivityTransitionByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition, Page page);

    List<LadsActivityTransition> findLadsActivityTransitionByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition, Order order);

    Long count();

    Long count(LadsActivityTransitionCondition ladsActivityTransitionCondition);

    void remove(LadsActivityTransitionCondition ladsActivityTransitionCondition);

    //以下是业务方法
    LadsActivityTransition findLadsActivityTransitionByUuid(String uuid);

    LadsActivity getfromActivity(LadsActivityTransition at);

    LadsActivity getToActivity(LadsActivityTransition at);

    LadsLearningdesign getLearningdesign(LadsActivityTransition la);
}
