package platform.education.lads.service;

import platform.education.lads.model.LadsPreviewJson;
import platform.education.lads.vo.LadsPreviewJsonCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LadsPreviewJsonService {

    LadsPreviewJson findLadsPreviewJsonById(Integer id);

    LadsPreviewJson add(LadsPreviewJson ladsPreviewJson);

    LadsPreviewJson modify(LadsPreviewJson ladsPreviewJson);

    void remove(LadsPreviewJson ladsPreviewJson);

    List<LadsPreviewJson> findLadsPreviewJsonByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition, Page page, Order order);

    List<LadsPreviewJson> findLadsPreviewJsonByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition);

    List<LadsPreviewJson> findLadsPreviewJsonByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition, Page page);

    List<LadsPreviewJson> findLadsPreviewJsonByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition, Order order);

    Long count();

    Long count(LadsPreviewJsonCondition ladsPreviewJsonCondition);

    void remove(LadsPreviewJsonCondition ladsPreviewJsonCondition);

    //以下是业务方法
    LadsPreviewJson findLadsPreviewJsonByUuid(String uuid);
}
