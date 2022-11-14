package platform.education.paper.service;

import platform.education.paper.model.PaWrong;
import platform.education.paper.vo.PaWrongCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaWrongService {

    PaWrong findPaWrongById(Integer id);

    PaWrong add(PaWrong paWrong);

    PaWrong modify(PaWrong paWrong);

    void remove(PaWrong paWrong);

    List<PaWrong> findPaWrongByCondition(PaWrongCondition paWrongCondition, Page page, Order order);

    List<PaWrong> findPaWrongByCondition(PaWrongCondition paWrongCondition);

    List<PaWrong> findPaWrongByCondition(PaWrongCondition paWrongCondition, Page page);

    List<PaWrong> findPaWrongByCondition(PaWrongCondition paWrongCondition, Order order);

    Long count();

    Long count(PaWrongCondition paWrongCondition);

    void remove(PaWrongCondition paWrongCondition);
    
    //以下是业务方法
    PaWrong findPaWrongByUuid(String uuid);
}
