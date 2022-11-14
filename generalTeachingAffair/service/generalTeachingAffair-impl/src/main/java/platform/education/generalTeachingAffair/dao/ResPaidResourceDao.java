package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ResPaidResource;


/**
 * 付费资源
 * @author edison
 */
public interface ResPaidResourceDao extends GenericDao<ResPaidResource, Integer> {




    ResPaidResource findByUuid(String uuid);


}
