package platform.education.oa.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.oa.model.AdjustClass;
import platform.education.oa.vo.AdjustClassCondition;
import platform.education.oa.vo.AdjustClassVo;

import java.util.Date;
import java.util.List;

/**
 * @author ZenGx1n
 * @version 1.0
 * @date 2021-03-26 16:37
 */
public interface AdjustClassDao extends GenericDao<AdjustClass, Integer> {

    List<AdjustClass> findAdjustClassByCondition(AdjustClassCondition adjustClassCondition, Page page, Order order);

    List<AdjustClass> findAdjustClassByDate(AdjustClass adjustClass, String applyDateStr, String setDateStr);

    Long count(AdjustClassCondition adjustClassCondition);

    List<AdjustClassVo> findAdjustClassVoByCondition(AdjustClassCondition adjustClassCondition, Page page, Order order);

    AdjustClass findById(Integer id);

    void updateReject(AdjustClass adjustClass);

}
