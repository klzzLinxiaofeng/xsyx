package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.MicroTypePojo;
import platform.education.generalTeachingAffair.vo.MicroManagerPojo;

import java.util.List;

/**
 * 小程序微课管理
 *
 * @author: yhc
 * @Date: 2020/10/16 13:24
 * @Description:
 */
public interface MicroManagerDao extends GenericDao<MicroManagerPojo, Integer> {
    List<MicroManagerPojo> findMicroManagerByCondition(MicroManagerPojo condition, Page page, Order order);

    MicroManagerPojo findById(Integer id);

    List<MicroTypePojo> findMicroType(MicroTypePojo microTypePojo);

    String findMicroGradeNameById(Integer id, Integer schoolId);

    void createGradeRelated(String[] split, Integer id, Integer schoolId);

    void createTypeRelated(Integer typeId, Integer id, Integer schoolId);

    void deleteTypeRelated(Integer id, Integer schoolId);

    void deleteGradeRelated(Integer id, Integer schoolId);
}