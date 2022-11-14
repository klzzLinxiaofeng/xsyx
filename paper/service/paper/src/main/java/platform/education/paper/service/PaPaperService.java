package platform.education.paper.service;

import platform.education.paper.model.PaPaper;
import platform.education.paper.vo.PaPaperCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaPaperService {

    PaPaper findPaPaperById(Integer id);

    PaPaper add(PaPaper paPaper);

    PaPaper modify(PaPaper paPaper);

    void remove(PaPaper paPaper);

    List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page, Order order);

    List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition);

    List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page);

    List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Order order);

    Long count();

    Long count(PaPaperCondition paPaperCondition);

    void remove(PaPaperCondition paPaperCondition);
    
    PaPaper findPaPaperByUuid(String id);

}
