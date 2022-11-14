package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PublicTimeVo;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/11/15 18:46
 * @Description:
 */
public interface PublicClassTimeDao extends GenericDao<PublicTimeVo, Integer> {

    List<PublicTimeVo> findPublicClassTimeInfoByCondition(PublicTimeVo condition, Page page, Order order);

    PublicTimeVo findByTimeId(Integer id);
    PublicTimeVo findByTimeAll(Integer weekDate, String startTime,String entTime);

    List<PublicTimeVo> findTimeInfoByClassId(Integer id, Integer schoolId);
}
