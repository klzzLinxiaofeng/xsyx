package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PublicTimeRelatedVo;
import platform.education.generalTeachingAffair.model.PublicTimeVo;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/11/15 18:46
 * @Description:
 */
public interface PublicClassTimeRelatedDao extends GenericDao<PublicTimeRelatedVo, Integer> {

    void deleteTimeRelated(Integer id, Date modify);
}
