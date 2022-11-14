package platform.education.lads.service;

import platform.education.lads.model.LadsDiscussTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LadsDiscussToolService {

    LadsDiscussTool findLadsDiscussToolById(Integer id);

    LadsDiscussTool add(LadsDiscussTool ladsDiscussTool);

    LadsDiscussTool modify(LadsDiscussTool ladsDiscussTool);

    void remove(LadsDiscussTool ladsDiscussTool);

    List<LadsDiscussTool> findLadsDiscussToolByCondition(LadsDiscussToolCondition ladsDiscussToolCondition, Page page, Order order);

    List<LadsDiscussTool> findLadsDiscussToolByCondition(LadsDiscussToolCondition ladsDiscussToolCondition);

    List<LadsDiscussTool> findLadsDiscussToolByCondition(LadsDiscussToolCondition ladsDiscussToolCondition, Page page);

    List<LadsDiscussTool> findLadsDiscussToolByCondition(LadsDiscussToolCondition ladsDiscussToolCondition, Order order);

    Long count();

    Long count(LadsDiscussToolCondition ladsDiscussToolCondition);

    //以下是业务方法
    LadsDiscussTool findLadsDiscussToolByUuid(String uuid);
}
