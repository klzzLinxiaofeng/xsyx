package platform.education.paper.service;

import platform.education.paper.model.PaCollect;
import platform.education.paper.vo.PaCollectCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Collection;

import java.util.List;

public interface PaCollectService {

    PaCollect findPaCollectById(Integer id);

    PaCollect add(PaCollect paCollect);

    PaCollect modify(PaCollect paCollect);

    void remove(PaCollect paCollect);

    List<PaCollect> findPaCollectByCondition(PaCollectCondition paCollectCondition, Page page, Order order);

    List<PaCollect> findPaCollectByCondition(PaCollectCondition paCollectCondition);

    List<PaCollect> findPaCollectByCondition(PaCollectCondition paCollectCondition, Page page);

    List<PaCollect> findPaCollectByCondition(PaCollectCondition paCollectCondition, Order order);

    Long count();

    Long count(PaCollectCondition paCollectCondition);

    void remove(PaCollectCondition paCollectCondition);
    
    PaCollect findPaCollectByUuid(String uuid);

    List<PaCollect> confirmCollect(String userId, Collection relateId, int collectType);
}
