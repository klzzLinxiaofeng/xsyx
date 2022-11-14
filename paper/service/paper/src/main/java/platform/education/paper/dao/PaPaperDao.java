package platform.education.paper.dao;

import platform.education.paper.model.PaPaper;
import platform.education.paper.vo.PaPaperCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import java.util.List;

public interface PaPaperDao extends GenericDao<PaPaper, java.lang.Integer> {

    List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page, Order order);

    PaPaper findById(Integer id);

    Long count(PaPaperCondition paPaperCondition);

    void deleteByCondition(PaPaperCondition paPaperCondition);

    //以下为业务方法
    PaPaper findByUuid(String uuid);
}
