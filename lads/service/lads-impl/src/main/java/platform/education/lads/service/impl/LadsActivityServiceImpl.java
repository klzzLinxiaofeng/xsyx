package platform.education.lads.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsActivity;
import platform.education.lads.vo.LadsActivityCondition;
import platform.education.lads.service.LadsActivityService;
import platform.education.lads.dao.LadsActivityDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Date;
import platform.education.lads.dao.LadsActivityTypeDao;
import platform.education.lads.dao.LadsLearningdesignDao;
import platform.education.lads.model.LadsActivityType;
import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.vo.LadsActivityVo;

public class LadsActivityServiceImpl implements LadsActivityService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsActivityDao ladsActivityDao;
    private LadsLearningdesignDao ladsLearningdesignDao;
    private LadsActivityTypeDao ladsActivityTypeDao;

    public void setLadsActivityDao(LadsActivityDao ladsActivityDao) {
        this.ladsActivityDao = ladsActivityDao;
    }

    public void setLadsLearningdesignDao(LadsLearningdesignDao ladsLearningdesignDao) {
        this.ladsLearningdesignDao = ladsLearningdesignDao;
    }

    public void setLadsActivityTypeDao(LadsActivityTypeDao ladsActivityTypeDao) {
        this.ladsActivityTypeDao = ladsActivityTypeDao;
    }

    @Override
    public LadsActivity findLadsActivityById(Integer id) {
        try {
            return ladsActivityDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsActivity add(LadsActivity ladsActivity) {
        if (ladsActivity == null) {
            return null;
        }
        Date createDate = ladsActivity.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        ladsActivity.setCreateDate(createDate);
        ladsActivity.setModifyDate(createDate);
        return ladsActivityDao.create(ladsActivity);
    }

    @Override
    public LadsActivity modify(LadsActivity ladsActivity) {
        if (ladsActivity == null) {
            return null;
        }
        Date modify = ladsActivity.getModifyDate();
        ladsActivity.setModifyDate(modify != null ? modify : new Date());
        return ladsActivityDao.update(ladsActivity);
    }

    @Override
    public void remove(LadsActivity ladsActivity) {
        ladsActivityDao.delete(ladsActivity);
    }

    @Override
    public List<LadsActivity> findLadsActivityByCondition(LadsActivityCondition ladsActivityCondition, Page page, Order order) {
        return ladsActivityDao.findLadsActivityByCondition(ladsActivityCondition, page, order);
    }

    @Override
    public List<LadsActivity> findLadsActivityByCondition(LadsActivityCondition ladsActivityCondition) {
        return ladsActivityDao.findLadsActivityByCondition(ladsActivityCondition, null, null);
    }

    @Override
    public List<LadsActivity> findLadsActivityByCondition(LadsActivityCondition ladsActivityCondition, Page page) {
        return ladsActivityDao.findLadsActivityByCondition(ladsActivityCondition, page, null);
    }

    @Override
    public List<LadsActivity> findLadsActivityByCondition(LadsActivityCondition ladsActivityCondition, Order order) {
        return ladsActivityDao.findLadsActivityByCondition(ladsActivityCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsActivityDao.count(null);
    }

    @Override
    public Long count(LadsActivityCondition ladsActivityCondition) {
        return this.ladsActivityDao.count(ladsActivityCondition);
    }

    @Override
    public void remove(LadsActivityCondition ladsActivityCondition) {
        this.ladsActivityDao.deleteByCondition(ladsActivityCondition);
    }

    //以下是业务方法
    
    @Override
    public LadsActivity findLadsActivityByUuid(String uuid) {
        try {
            return ladsActivityDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }
    
    @Override
    public List<String> findToolIdByToolNameAndLdid(LadsActivityVo avo) {
        return ladsActivityDao.findToolIdByToolNameAndLdid(avo);
    }

    @Override
    public LadsActivityType getActivityType(LadsActivity la) {
        LadsActivityType at = ladsActivityTypeDao.findByUuid(la.getActivityType());
        if (at != null) {
            return at;
        } else {
            return null;
        }
    }

    @Override
    public LadsLearningdesign getLearningdesign(LadsActivity la) {
        LadsLearningdesign ld = ladsLearningdesignDao.findByUuid(la.getLearningdesign());
        if (ld != null) {
            return ld;
        } else {
            return null;
        }
    }

    @Override
    public LadsActivity getParentActivity(LadsActivity la) {
        LadsActivity pa = ladsActivityDao.findByUuid(la.getParentActivity());
        if (pa != null) {
            return pa;
        } else {
            return null;
        }
    }

    @Override
    public void removeChildrenActivity(String parentActivityId) {
        ladsActivityDao.deleteChildrenActivity(parentActivityId);
    }
}
