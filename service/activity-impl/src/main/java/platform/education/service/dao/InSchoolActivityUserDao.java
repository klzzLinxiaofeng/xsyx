package platform.education.service.dao;

import platform.education.service.model.InSchoolActivityUser;
import platform.education.service.vo.InSchoolActivityUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.service.vo.PersonVo;

import java.util.List;

public interface InSchoolActivityUserDao extends GenericDao<InSchoolActivityUser, Integer> {

    List<InSchoolActivityUser> findInSchoolActivityUserByCondition(InSchoolActivityUserCondition inSchoolActivityUserCondition, Page page, Order order);

    InSchoolActivityUser findById(Integer id);

    Long count(InSchoolActivityUserCondition inSchoolActivityUserCondition);
}
