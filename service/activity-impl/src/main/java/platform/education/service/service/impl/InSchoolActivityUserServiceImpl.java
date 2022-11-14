package platform.education.service.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.service.model.InSchoolActivityUser;
import platform.education.service.vo.InSchoolActivityUserCondition;
import platform.education.service.service.InSchoolActivityUserService;
import platform.education.service.dao.InSchoolActivityUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class InSchoolActivityUserServiceImpl implements InSchoolActivityUserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private InSchoolActivityUserDao inSchoolActivityUserDao;

    public void setInSchoolActivityUserDao(InSchoolActivityUserDao inSchoolActivityUserDao) {
        this.inSchoolActivityUserDao = inSchoolActivityUserDao;
    }

    @Override
    public InSchoolActivityUser findInSchoolActivityUserById(Integer id) {
        try {
            return inSchoolActivityUserDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public InSchoolActivityUser add(InSchoolActivityUser inSchoolActivityUser) {
        if (inSchoolActivityUser == null) {
            return null;
        }
        return inSchoolActivityUserDao.create(inSchoolActivityUser);
    }

    @Override
    public InSchoolActivityUser modify(InSchoolActivityUser inSchoolActivityUser) {
        if (inSchoolActivityUser == null) {
            return null;
        }
        Date modify = inSchoolActivityUser.getModifyDate();
        inSchoolActivityUser.setModifyDate(modify != null ? modify : new Date());
        return inSchoolActivityUserDao.update(inSchoolActivityUser);
    }

    @Override
    public void remove(InSchoolActivityUser inSchoolActivityUser) {
        try {
            inSchoolActivityUserDao.delete(inSchoolActivityUser);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", inSchoolActivityUser.getId(), e);
            }
        }
    }

    @Override
    public List<InSchoolActivityUser> findInSchoolActivityUserByCondition(InSchoolActivityUserCondition inSchoolActivityUserCondition, Page page, Order order) {
        return inSchoolActivityUserDao.findInSchoolActivityUserByCondition(inSchoolActivityUserCondition, page, order);
    }

    @Override
    public List<InSchoolActivityUser> findInSchoolActivityUserByCondition(InSchoolActivityUserCondition inSchoolActivityUserCondition) {
        return inSchoolActivityUserDao.findInSchoolActivityUserByCondition(inSchoolActivityUserCondition, null, null);
    }

    @Override
    public List<InSchoolActivityUser> findInSchoolActivityUserByCondition(InSchoolActivityUserCondition inSchoolActivityUserCondition, Page page) {
        return inSchoolActivityUserDao.findInSchoolActivityUserByCondition(inSchoolActivityUserCondition, page, null);
    }

    @Override
    public List<InSchoolActivityUser> findInSchoolActivityUserByCondition(InSchoolActivityUserCondition inSchoolActivityUserCondition, Order order) {
        return inSchoolActivityUserDao.findInSchoolActivityUserByCondition(inSchoolActivityUserCondition, null, order);
    }

    @Override
    public Long count() {
        return this.inSchoolActivityUserDao.count(null);
    }

    @Override
    public Long count(InSchoolActivityUserCondition inSchoolActivityUserCondition) {
        return this.inSchoolActivityUserDao.count(inSchoolActivityUserCondition);
    }

}
