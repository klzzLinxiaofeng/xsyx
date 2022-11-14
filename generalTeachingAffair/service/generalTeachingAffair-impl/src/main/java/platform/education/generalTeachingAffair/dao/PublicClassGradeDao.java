package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.PublicGradeRelatedVo;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/11/15 18:46
 * @Description:
 */
public interface PublicClassGradeDao extends GenericDao<PublicGradeRelatedVo, Integer> {


    List<PublicGradeRelatedVo> findGradeInfoByClassId(Integer id, Integer schoolId);

    void deleteGradeRelated(Integer id, Date modify);
}
