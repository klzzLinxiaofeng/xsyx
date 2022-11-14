package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.MicroManagerPojo;
import platform.education.generalTeachingAffair.vo.UserCommentsPojo;

import java.util.List;

/**
 * 小程序微课管理
 *
 * @author: yhc
 * @Date: 2020/10/16 13:24
 * @Description:
 */
public interface StuCommentsDao extends GenericDao<UserCommentsPojo, Integer> {

    List<UserCommentsPojo> findStuCommentsPojoByCondition(MicroManagerPojo condition, Page page, Order order);
}