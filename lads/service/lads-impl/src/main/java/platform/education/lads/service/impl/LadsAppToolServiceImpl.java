package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsAppTool;
import platform.education.lads.vo.LadsAppToolCondition;
import platform.education.lads.service.LadsAppToolService;
import platform.education.lads.dao.LadsAppToolDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LadsAppToolServiceImpl implements LadsAppToolService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsAppToolDao ladsAppToolDao;

    public void setLadsAppToolDao(LadsAppToolDao ladsAppToolDao) {
        this.ladsAppToolDao = ladsAppToolDao;
    }

    @Override
    public LadsAppTool findLadsAppToolById(Integer id) {
        try {
            return ladsAppToolDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsAppTool add(LadsAppTool ladsAppTool) {
        if (ladsAppTool == null) {
            return null;
        }
        Date createDate = ladsAppTool.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        ladsAppTool.setCreateDate(createDate);
        ladsAppTool.setModifyDate(createDate);
        return ladsAppToolDao.create(ladsAppTool);
    }

    @Override
    public LadsAppTool modify(LadsAppTool ladsAppTool) {
        if (ladsAppTool == null) {
            return null;
        }
        Date modify = ladsAppTool.getModifyDate();
        ladsAppTool.setModifyDate(modify != null ? modify : new Date());
        return ladsAppToolDao.update(ladsAppTool);
    }

    @Override
    public void remove(LadsAppTool ladsAppTool) {
        ladsAppToolDao.delete(ladsAppTool);
    }

    @Override
    public List<LadsAppTool> findLadsAppToolByCondition(LadsAppToolCondition ladsAppToolCondition, Page page, Order order) {
        return ladsAppToolDao.findLadsAppToolByCondition(ladsAppToolCondition, page, order);
    }

    @Override
    public List<LadsAppTool> findLadsAppToolByCondition(LadsAppToolCondition ladsAppToolCondition) {
        return ladsAppToolDao.findLadsAppToolByCondition(ladsAppToolCondition, null, null);
    }

    @Override
    public List<LadsAppTool> findLadsAppToolByCondition(LadsAppToolCondition ladsAppToolCondition, Page page) {
        return ladsAppToolDao.findLadsAppToolByCondition(ladsAppToolCondition, page, null);
    }

    @Override
    public List<LadsAppTool> findLadsAppToolByCondition(LadsAppToolCondition ladsAppToolCondition, Order order) {
        return ladsAppToolDao.findLadsAppToolByCondition(ladsAppToolCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsAppToolDao.count(null);
    }

    @Override
    public Long count(LadsAppToolCondition ladsAppToolCondition) {
        return this.ladsAppToolDao.count(ladsAppToolCondition);
    }

    @Override
    public void remove(LadsAppToolCondition ladsAppToolCondition) {
        this.ladsAppToolDao.deleteByCondition(ladsAppToolCondition);
    }

    //以下是业务方法
    @Override
    public LadsAppTool findLadsAppToolByUuid(String uuid) {
        try {
            return ladsAppToolDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }
}
