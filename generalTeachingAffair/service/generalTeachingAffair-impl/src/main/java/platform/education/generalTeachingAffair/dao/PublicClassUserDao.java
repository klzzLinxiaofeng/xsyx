package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.PublicClass;
import platform.education.generalTeachingAffair.model.PublicUserVo;

import java.util.List;

public interface PublicClassUserDao extends GenericDao<PublicUserVo, Integer> {


    List<PublicUserVo> findByClassId(Integer id, Integer stuId);
}
