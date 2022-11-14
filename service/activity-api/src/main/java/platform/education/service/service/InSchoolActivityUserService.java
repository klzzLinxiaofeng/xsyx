package platform.education.service.service;

import platform.education.service.model.InSchoolActivityUser;
import platform.education.service.vo.InSchoolActivityUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface InSchoolActivityUserService {
    InSchoolActivityUser findInSchoolActivityUserById(Integer id);

    InSchoolActivityUser add(InSchoolActivityUser inSchoolActivityUser);

    InSchoolActivityUser modify(InSchoolActivityUser inSchoolActivityUser);

    void remove(InSchoolActivityUser inSchoolActivityUser);

    List<InSchoolActivityUser> findInSchoolActivityUserByCondition(InSchoolActivityUserCondition inSchoolActivityUserCondition, Page page, Order order);

    List<InSchoolActivityUser> findInSchoolActivityUserByCondition(InSchoolActivityUserCondition inSchoolActivityUserCondition);

    List<InSchoolActivityUser> findInSchoolActivityUserByCondition(InSchoolActivityUserCondition inSchoolActivityUserCondition, Page page);

    List<InSchoolActivityUser> findInSchoolActivityUserByCondition(InSchoolActivityUserCondition inSchoolActivityUserCondition, Order order);

    Long count();

    Long count(InSchoolActivityUserCondition inSchoolActivityUserCondition);

}
