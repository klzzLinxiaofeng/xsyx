package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.PublicClass;
import platform.education.generalTeachingAffair.vo.CountPublicClassVo;
import platform.education.generalTeachingAffair.vo.PublicClassCondition;

import java.util.List;

public interface PublicClassDao extends GenericDao<PublicClass, java.lang.Integer> {

    List<PublicClass> findPublicClassByCondition(PublicClassCondition publicClassCondition, Page page, Order order);

    List<PublicClass> findPublicClassByConditionLeixing(PublicClassCondition publicClassCondition, Page page, Order order,Integer leixing);

    PublicClass findById(Integer id);

    Long count(PublicClassCondition publicClassCondition);

    /**
     * @Description: 通过schoolId查询年级
     * @Param: * @param schoolId
     * @return: java.util.List<platform.education.generalTeachingAffair.model.Grade>
     * @Author: cmb
     * @Date: 2020/10/21
     */
    List<Grade> findGradeBySchoolId(Integer schoolId);

    List<Integer> findPublicUserByIdAndSchoolId(Integer id, Integer schoolId);

    /*Integer createPublicClassStu(PublicClass publicClass);

    Integer removePublicClassStu(PublicClass publicClass);
*/

    List<CountPublicClassVo> findCountClass(CountPublicClassVo condition);

    List<CountPublicClassVo> findChoseClassStu(CountPublicClassVo condition);

    List<CountPublicClassVo> findNoChoseClassStu(CountPublicClassVo condition);
    //shanchu
}
