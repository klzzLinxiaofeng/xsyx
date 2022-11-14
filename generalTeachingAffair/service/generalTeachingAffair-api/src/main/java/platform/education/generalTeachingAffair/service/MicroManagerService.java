package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.MicroTypePojo;
import platform.education.generalTeachingAffair.vo.MicroManagerPojo;
import platform.education.generalTeachingAffair.vo.UserCommentsPojo;

import java.util.List;

/**
 * 小程序微课管理
 *
 * @author: yhc
 * @Date: 2020/10/16 13:23
 * @Description:
 */
public interface MicroManagerService {


    List<MicroManagerPojo> findMicroManagerByCondition(MicroManagerPojo condition, Page page, Order order);

    MicroManagerPojo findMicroManagerPojoById(Integer id);

    MicroManagerPojo add(MicroManagerPojo publicClass);

    MicroManagerPojo modify(MicroManagerPojo publicClass);

    String abandon(MicroManagerPojo publicClass);

    List<UserCommentsPojo> findStuCommentsPojoByCondition(MicroManagerPojo condition, Page page, Order order);

    String commentAbandon(UserCommentsPojo userCommentsPojo);

    List<MicroTypePojo> findMicroType(MicroTypePojo microTypePojo);

    String findMicroGradeNameById(Integer id, Integer schoolId);
}

