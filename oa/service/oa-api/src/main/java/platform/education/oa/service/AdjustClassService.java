package platform.education.oa.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.oa.model.AdjustClass;
import platform.education.oa.vo.AdjustClassCondition;
import platform.education.oa.vo.AdjustClassVo;

import java.util.List;

/**
 * @author ZenGx1n
 * @version 1.0
 * @date 2021-03-26 16:14
 */
public interface AdjustClassService {

    List<AdjustClass> findAdjustClassByCondition(AdjustClassCondition adjustClassCondition, Page page, Order order);

    Long count(AdjustClassCondition adjustClassCondition);

    List<AdjustClassVo> findAdjustClassVoByCondition(AdjustClassCondition adjustClassCondition, Page page, Order order);

    AdjustClass findById(Integer id);

    List<AdjustClass> modifyReject(AdjustClass adjustClass);

    List<AdjustClass> findAdjustClassByCondition(AdjustClassCondition adjustClassCondition);

}
