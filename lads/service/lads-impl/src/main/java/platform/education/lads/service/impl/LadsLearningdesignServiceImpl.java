package platform.education.lads.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.vo.LadsLearningdesignCondition;
import platform.education.lads.service.LadsLearningdesignService;
import platform.education.lads.dao.LadsLearningdesignDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Date;

public class LadsLearningdesignServiceImpl implements LadsLearningdesignService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsLearningdesignDao ladsLearningdesignDao;

    public void setLadsLearningdesignDao(LadsLearningdesignDao ladsLearningdesignDao) {
        this.ladsLearningdesignDao = ladsLearningdesignDao;
    }

    @Override
    public LadsLearningdesign findLadsLearningdesignById(Integer id) {
        try {
            return ladsLearningdesignDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsLearningdesign add(LadsLearningdesign ladsLearningdesign) {
        if (ladsLearningdesign == null) {
            return null;
        }
        Date createDate = ladsLearningdesign.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        ladsLearningdesign.setCreateDate(createDate);
        ladsLearningdesign.setModifyDate(createDate);
        return ladsLearningdesignDao.create(ladsLearningdesign);
    }

    @Override
    public LadsLearningdesign modify(LadsLearningdesign ladsLearningdesign) {
        if (ladsLearningdesign == null) {
            return null;
        }
        Date modify = ladsLearningdesign.getModifyDate();
        ladsLearningdesign.setModifyDate(modify != null ? modify : new Date());
        return ladsLearningdesignDao.update(ladsLearningdesign);
    }

    @Override
    public void remove(LadsLearningdesign ladsLearningdesign) {
        ladsLearningdesignDao.delete(ladsLearningdesign);
    }

    @Override
    public List<LadsLearningdesign> findLadsLearningdesignByCondition(LadsLearningdesignCondition ladsLearningdesignCondition, Page page, Order order) {
        return ladsLearningdesignDao.findLadsLearningdesignByCondition(ladsLearningdesignCondition, page, order);
    }

    @Override
    public List<LadsLearningdesign> findLadsLearningdesignByCondition(LadsLearningdesignCondition ladsLearningdesignCondition) {
        return ladsLearningdesignDao.findLadsLearningdesignByCondition(ladsLearningdesignCondition, null, null);
    }

    @Override
    public List<LadsLearningdesign> findLadsLearningdesignByCondition(LadsLearningdesignCondition ladsLearningdesignCondition, Page page) {
        return ladsLearningdesignDao.findLadsLearningdesignByCondition(ladsLearningdesignCondition, page, null);
    }

    @Override
    public List<LadsLearningdesign> findLadsLearningdesignByCondition(LadsLearningdesignCondition ladsLearningdesignCondition, Order order) {
        return ladsLearningdesignDao.findLadsLearningdesignByCondition(ladsLearningdesignCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsLearningdesignDao.count(null);
    }

    @Override
    public Long count(LadsLearningdesignCondition ladsLearningdesignCondition) {
        return this.ladsLearningdesignDao.count(ladsLearningdesignCondition);
    }

    @Override
    public void remove(LadsLearningdesignCondition ladsLearningdesignCondition) {
        this.ladsLearningdesignDao.deleteByCondition(ladsLearningdesignCondition);
    }

    //以下是业务方法
    @Override
    public LadsLearningdesign findLadsLearningdesignByUuid(String uuid) {
        try {
            return ladsLearningdesignDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }

    @Override
    public List<String> findUserIdByLdId(String ldId) {
        return ladsLearningdesignDao.findUserIdByLdId(ldId);
    }
}
