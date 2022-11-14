package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PublicTeacherRelatedVo;
import platform.education.generalTeachingAffair.model.PublicTeacherVo;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/11/15 18:46
 * @Description:
 */
public interface PublicClassTeacherDao extends GenericDao<PublicTeacherVo, Integer> {
    List<PublicTeacherVo> findPublicClassTeacherInfoByCondition(PublicTeacherVo condition, Page page, Order order);
//    List<PublicTeacherVo> findPublicClassTeacherInfo(PublicTeacherVo condition);

    PublicTeacherVo findByTeacherId(Integer id);


    List<PublicTeacherVo> findByClassId(Integer id, Integer schoolId);

}
