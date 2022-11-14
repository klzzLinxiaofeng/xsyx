package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsActivityType;
import platform.education.lads.vo.LadsActivityTypeCondition;
import platform.education.lads.service.LadsActivityTypeService;
import platform.education.lads.dao.LadsActivityTypeDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LadsActivityTypeServiceImpl implements LadsActivityTypeService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsActivityTypeDao ladsActivityTypeDao;

    public void setLadsActivityTypeDao(LadsActivityTypeDao ladsActivityTypeDao) {
        this.ladsActivityTypeDao = ladsActivityTypeDao;
    }

    @Override
    public LadsActivityType findLadsActivityTypeById(Integer id) {
        try {
            return ladsActivityTypeDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsActivityType add(LadsActivityType ladsActivityType) {
        if (ladsActivityType == null) {
            return null;
        }
        Date createDate = ladsActivityType.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        ladsActivityType.setCreateDate(createDate);
        ladsActivityType.setModifyDate(createDate);
        return ladsActivityTypeDao.create(ladsActivityType);
    }

    @Override
    public LadsActivityType modify(LadsActivityType ladsActivityType) {
        if (ladsActivityType == null) {
            return null;
        }
        Date modify = ladsActivityType.getModifyDate();
        ladsActivityType.setModifyDate(modify != null ? modify : new Date());
        return ladsActivityTypeDao.update(ladsActivityType);
    }

    @Override
    public void remove(LadsActivityType ladsActivityType) {
        ladsActivityTypeDao.delete(ladsActivityType);
    }

    @Override
    public List<LadsActivityType> findLadsActivityTypeByCondition(LadsActivityTypeCondition ladsActivityTypeCondition, Page page, Order order) {
        return ladsActivityTypeDao.findLadsActivityTypeByCondition(ladsActivityTypeCondition, page, order);
    }

    @Override
    public List<LadsActivityType> findLadsActivityTypeByCondition(LadsActivityTypeCondition ladsActivityTypeCondition) {
        return ladsActivityTypeDao.findLadsActivityTypeByCondition(ladsActivityTypeCondition, null, null);
    }

    @Override
    public List<LadsActivityType> findLadsActivityTypeByCondition(LadsActivityTypeCondition ladsActivityTypeCondition, Page page) {
        return ladsActivityTypeDao.findLadsActivityTypeByCondition(ladsActivityTypeCondition, page, null);
    }

    @Override
    public List<LadsActivityType> findLadsActivityTypeByCondition(LadsActivityTypeCondition ladsActivityTypeCondition, Order order) {
        return ladsActivityTypeDao.findLadsActivityTypeByCondition(ladsActivityTypeCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsActivityTypeDao.count(null);
    }

    @Override
    public Long count(LadsActivityTypeCondition ladsActivityTypeCondition) {
        return this.ladsActivityTypeDao.count(ladsActivityTypeCondition);
    }

    @Override
    public void remove(LadsActivityTypeCondition ladsActivityTypeCondition) {
        this.ladsActivityTypeDao.deleteByCondition(ladsActivityTypeCondition);
    }

    //以下是业务方法
    @Override
    public LadsActivityType findLadsActivityTypeByUuid(String uuid) {
        try {
            return ladsActivityTypeDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }
}
