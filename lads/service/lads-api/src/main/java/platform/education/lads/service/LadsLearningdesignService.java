package platform.education.lads.service;

import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.vo.LadsLearningdesignCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LadsLearningdesignService {

    LadsLearningdesign findLadsLearningdesignById(Integer id);

    LadsLearningdesign add(LadsLearningdesign ladsLearningdesign);

    LadsLearningdesign modify(LadsLearningdesign ladsLearningdesign);

    void remove(LadsLearningdesign ladsLearningdesign);

    List<LadsLearningdesign> findLadsLearningdesignByCondition(LadsLearningdesignCondition ladsLearningdesignCondition, Page page, Order order);

    List<LadsLearningdesign> findLadsLearningdesignByCondition(LadsLearningdesignCondition ladsLearningdesignCondition);

    List<LadsLearningdesign> findLadsLearningdesignByCondition(LadsLearningdesignCondition ladsLearningdesignCondition, Page page);

    List<LadsLearningdesign> findLadsLearningdesignByCondition(LadsLearningdesignCondition ladsLearningdesignCondition, Order order);

    Long count();

    Long count(LadsLearningdesignCondition ladsLearningdesignCondition);

    void remove(LadsLearningdesignCondition ladsLearningdesignCondition);
    
    //以下是业务方法

    LadsLearningdesign findLadsLearningdesignByUuid(String uuid);
    
    List<String> findUserIdByLdId(String ldId);
}
