package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.PublicTeacherRelatedVo;

import java.util.Date;

/**
 * @author: yhc
 * @Date: 2020/11/15 18:46
 * @Description:
 */
public interface PublicClassTeacherRelatedDao extends GenericDao<PublicTeacherRelatedVo, Integer> {


    void deleteTeacherRelated(Integer id, Date modify);
}
