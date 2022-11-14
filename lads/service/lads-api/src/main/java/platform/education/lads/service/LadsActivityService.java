package platform.education.lads.service;

import platform.education.lads.model.LadsActivity;
import platform.education.lads.vo.LadsActivityCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import platform.education.lads.model.LadsActivityType;
import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.vo.LadsActivityVo;

public interface LadsActivityService {

    LadsActivity findLadsActivityById(Integer id);

    LadsActivity add(LadsActivity ladsActivity);

    LadsActivity modify(LadsActivity ladsActivity);

    void remove(LadsActivity ladsActivity);

    List<LadsActivity> findLadsActivityByCondition(LadsActivityCondition ladsActivityCondition, Page page, Order order);

    List<LadsActivity> findLadsActivityByCondition(LadsActivityCondition ladsActivityCondition);

    List<LadsActivity> findLadsActivityByCondition(LadsActivityCondition ladsActivityCondition, Page page);

    List<LadsActivity> findLadsActivityByCondition(LadsActivityCondition ladsActivityCondition, Order order);

    Long count();

    Long count(LadsActivityCondition ladsActivityCondition);

    void remove(LadsActivityCondition ladsActivityCondition);
    
    //以下是业务方法
    
    LadsActivity findLadsActivityByUuid(String uuid);

    void removeChildrenActivity(String parentActivityId);

    LadsActivityType getActivityType(LadsActivity la);

    LadsLearningdesign getLearningdesign(LadsActivity la);

    LadsActivity getParentActivity(LadsActivity la);

    List<String> findToolIdByToolNameAndLdid(LadsActivityVo avo);
}
