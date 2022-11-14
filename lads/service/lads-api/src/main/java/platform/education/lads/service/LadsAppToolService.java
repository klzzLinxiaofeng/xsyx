package platform.education.lads.service;

import platform.education.lads.model.LadsAppTool;
import platform.education.lads.vo.LadsAppToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LadsAppToolService {

    LadsAppTool findLadsAppToolById(Integer id);

    LadsAppTool add(LadsAppTool ladsAppTool);

    LadsAppTool modify(LadsAppTool ladsAppTool);

    void remove(LadsAppTool ladsAppTool);

    List<LadsAppTool> findLadsAppToolByCondition(LadsAppToolCondition ladsAppToolCondition, Page page, Order order);

    List<LadsAppTool> findLadsAppToolByCondition(LadsAppToolCondition ladsAppToolCondition);

    List<LadsAppTool> findLadsAppToolByCondition(LadsAppToolCondition ladsAppToolCondition, Page page);

    List<LadsAppTool> findLadsAppToolByCondition(LadsAppToolCondition ladsAppToolCondition, Order order);

    Long count();

    Long count(LadsAppToolCondition ladsAppToolCondition);

    void remove(LadsAppToolCondition ladsAppToolCondition);

    //以下是业务方法
    LadsAppTool findLadsAppToolByUuid(String uuid);
}
