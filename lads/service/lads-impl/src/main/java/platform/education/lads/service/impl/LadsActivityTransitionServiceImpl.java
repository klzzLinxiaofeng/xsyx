package platform.education.lads.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsActivityTransition;
import platform.education.lads.vo.LadsActivityTransitionCondition;
import platform.education.lads.service.LadsActivityTransitionService;
import platform.education.lads.dao.LadsActivityTransitionDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.lads.dao.LadsActivityDao;
import platform.education.lads.dao.LadsLearningdesignDao;
import platform.education.lads.model.LadsActivity;
import platform.education.lads.model.LadsLearningdesign;

public class LadsActivityTransitionServiceImpl implements LadsActivityTransitionService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsActivityTransitionDao ladsActivityTransitionDao;
    private LadsActivityDao ladsActivityDao;
    private LadsLearningdesignDao ladsLearningdesignDao;

    public void setLadsActivityDao(LadsActivityDao ladsActivityDao) {
        this.ladsActivityDao = ladsActivityDao;
    }

    public void setLadsActivityTransitionDao(LadsActivityTransitionDao ladsActivityTransitionDao) {
        this.ladsActivityTransitionDao = ladsActivityTransitionDao;
    }

    public void setLadsLearningdesignDao(LadsLearningdesignDao ladsLearningdesignDao) {
        this.ladsLearningdesignDao = ladsLearningdesignDao;
    }

    @Override
    public LadsActivityTransition findLadsActivityTransitionById(Integer id) {
        try {
            return ladsActivityTransitionDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsActivityTransition add(LadsActivityTransition ladsActivityTransition) {
        return ladsActivityTransitionDao.create(ladsActivityTransition);
    }

    @Override
    public LadsActivityTransition modify(LadsActivityTransition ladsActivityTransition) {
        return ladsActivityTransitionDao.update(ladsActivityTransition);
    }

    @Override
    public void remove(LadsActivityTransition ladsActivityTransition) {
        ladsActivityTransitionDao.delete(ladsActivityTransition);
    }

    @Override
    public List<LadsActivityTransition> findLadsActivityTransitionByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition, Page page, Order order) {
        return ladsActivityTransitionDao.findLadsActivityTransitionByCondition(ladsActivityTransitionCondition, page, order);
    }

    @Override
    public List<LadsActivityTransition> findLadsActivityTransitionByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition) {
        return ladsActivityTransitionDao.findLadsActivityTransitionByCondition(ladsActivityTransitionCondition, null, null);
    }

    @Override
    public List<LadsActivityTransition> findLadsActivityTransitionByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition, Page page) {
        return ladsActivityTransitionDao.findLadsActivityTransitionByCondition(ladsActivityTransitionCondition, page, null);
    }

    @Override
    public List<LadsActivityTransition> findLadsActivityTransitionByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition, Order order) {
        return ladsActivityTransitionDao.findLadsActivityTransitionByCondition(ladsActivityTransitionCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsActivityTransitionDao.count(null);
    }

    @Override
    public Long count(LadsActivityTransitionCondition ladsActivityTransitionCondition) {
        return this.ladsActivityTransitionDao.count(ladsActivityTransitionCondition);
    }

    @Override
    public void remove(LadsActivityTransitionCondition ladsActivityTransitionCondition) {
        this.ladsActivityTransitionDao.deleteByCondition(ladsActivityTransitionCondition);
    }

    //以下是业务方法
    @Override
    public LadsActivityTransition findLadsActivityTransitionByUuid(String uuid) {
        try {
            return ladsActivityTransitionDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }

    @Override
    public LadsActivity getfromActivity(LadsActivityTransition at) {
        LadsActivity fa = ladsActivityDao.findByUuid(at.getFromActivity());
        if (fa != null) {
            return fa;
        } else {
            return null;
        }
    }

    @Override
    public LadsActivity getToActivity(LadsActivityTransition at) {
        LadsActivity ta = ladsActivityDao.findByUuid(at.getToActivity());
        if (ta != null) {
            return ta;
        } else {
            return null;
        }
    }

    @Override
    public LadsLearningdesign getLearningdesign(LadsActivityTransition la) {
        LadsLearningdesign ld = ladsLearningdesignDao.findByUuid(la.getLearningdesign());
        if (ld != null) {
            return ld;
        } else {
            return null;
        }
    }
}
