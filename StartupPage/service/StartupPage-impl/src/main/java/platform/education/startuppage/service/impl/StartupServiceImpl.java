package platform.education.startuppage.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.startuppage.dao.StartupDao;
import platform.education.startuppage.model.Startup;
import platform.education.startuppage.service.StartupService;
import platform.education.startuppage.vo.StartupCondition;


import java.util.Date;
import java.util.List;

public class StartupServiceImpl implements StartupService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private StartupDao startupDao;
    public void setstartupDao(StartupDao startupDao) {
        this.startupDao = startupDao;
    }

    @Override
    public Startup findStartupById(Integer id) {
        try {
            return this.startupDao.findById(id);
        } catch (Exception var3) {
            var3.printStackTrace();
            this.log.info("数据库无存在ID为 {} ", id);
            return null;
        }
    }

    @Override
    public Startup add(Startup startup) {
        if (startup == null) {
            return null;
        } else {
            Date createDate = startup.getCreateDate();
            if (createDate == null) {
                createDate = new Date();
            }

            startup.setCreateDate(createDate);
            startup.setModifyDate(createDate);
            return this.startupDao.create(startup);
        }
    }

    @Override
    public Startup modify(Startup startup) {
        if (startup == null) {
            return null;
        } else {
            Date modify = startup.getModifyDate();
            startup.setModifyDate(modify != null ? modify : new Date());
            return  this.startupDao.update(startup);
        }
    }

    @Override
    public void remove(Startup startup) {
        try {
            this.startupDao.delete(startup);
        } catch (Exception var3) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("删除数据库无存在ID为 {} ,异常为：{}", startup.getId(), var3);
            }
        }

    }

    @Override
    public List<Startup> findStartupCondition(StartupCondition startupCondition, Page page, Order order) {
        return this.startupDao.findStartupByCondition(startupCondition,page,order);
    }

    @Override
    public List<Startup> findStartupCondition(StartupCondition startupCondition) {
        return this.startupDao.findStartupByCondition(startupCondition,null,null);
    }

    @Override
    public List<Startup> findStartupCondition(StartupCondition startupCondition, Page page) {
        return this.startupDao.findStartupByCondition(startupCondition,page,null);
    }

    @Override
    public List<Startup> findStartupCondition(StartupCondition startupCondition, Order order) {
        return this.startupDao.findStartupByCondition(startupCondition,null,order);
    }

    @Override
    public Long count() {
        return this.startupDao.count(null);
    }

    @Override
    public Long count(StartupCondition startupCondition) {
        return this.startupDao.count(startupCondition);
    }
}
