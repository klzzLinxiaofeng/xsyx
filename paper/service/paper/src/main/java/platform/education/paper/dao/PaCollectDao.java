package platform.education.paper.dao;

import platform.education.paper.model.PaCollect;
import platform.education.paper.vo.PaCollectCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import java.util.Collection;
import java.util.List;

public interface PaCollectDao extends GenericDao<PaCollect, java.lang.Integer> {

    List<PaCollect> findPaCollectByCondition(PaCollectCondition paCollectCondition, Page page, Order order);

    PaCollect findById(Integer id);

    Long count(PaCollectCondition paCollectCondition);

    void deleteByCondition(PaCollectCondition paCollectCondition);
    
    //以下为业务方法
    PaCollect findByUuid(String uuid);

    List<PaCollect> confirmCollect(String userId, Collection relateId, int collectType);
}
