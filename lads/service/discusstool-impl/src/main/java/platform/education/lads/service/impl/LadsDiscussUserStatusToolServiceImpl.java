package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsDiscussUserStatusTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussUserStatusToolCondition;
import platform.education.lads.service.LadsDiscussUserStatusToolService;
import platform.education.lads.dao.LadsDiscussUserStatusToolDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.CountFinishedStatusCondition;

public class LadsDiscussUserStatusToolServiceImpl implements LadsDiscussUserStatusToolService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsDiscussUserStatusToolDao ladsDiscussUserStatusToolDao;

    public void setLadsDiscussUserStatusToolDao(LadsDiscussUserStatusToolDao ladsDiscussUserStatusToolDao) {
        this.ladsDiscussUserStatusToolDao = ladsDiscussUserStatusToolDao;
    }

    @Override
    public LadsDiscussUserStatusTool findLadsDiscussUserStatusToolById(Integer id) {
        try {
            return ladsDiscussUserStatusToolDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsDiscussUserStatusTool add(LadsDiscussUserStatusTool ladsDiscussUserStatusTool) {
        if (ladsDiscussUserStatusTool == null) {
            return null;
        }
        Date createDate = ladsDiscussUserStatusTool.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        ladsDiscussUserStatusTool.setCreateDate(createDate);
        ladsDiscussUserStatusTool.setModifyDate(createDate);
        return ladsDiscussUserStatusToolDao.create(ladsDiscussUserStatusTool);
    }

    @Override
    public LadsDiscussUserStatusTool modify(LadsDiscussUserStatusTool ladsDiscussUserStatusTool) {
        if (ladsDiscussUserStatusTool == null) {
            return null;
        }
        Date modify = ladsDiscussUserStatusTool.getModifyDate();
        ladsDiscussUserStatusTool.setModifyDate(modify != null ? modify : new Date());
        return ladsDiscussUserStatusToolDao.update(ladsDiscussUserStatusTool);
    }

    @Override
    public void remove(LadsDiscussUserStatusTool ladsDiscussUserStatusTool) {
        try {
            ladsDiscussUserStatusToolDao.delete(ladsDiscussUserStatusTool);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", ladsDiscussUserStatusTool.getId(), e);
            }
        }
    }

    @Override
    public List<LadsDiscussUserStatusTool> findLadsDiscussUserStatusToolByCondition(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition, Page page, Order order) {
        return ladsDiscussUserStatusToolDao.findLadsDiscussUserStatusToolByCondition(ladsDiscussUserStatusToolCondition, page, order);
    }

    @Override
    public List<LadsDiscussUserStatusTool> findLadsDiscussUserStatusToolByCondition(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition) {
        return ladsDiscussUserStatusToolDao.findLadsDiscussUserStatusToolByCondition(ladsDiscussUserStatusToolCondition, null, null);
    }

    @Override
    public List<LadsDiscussUserStatusTool> findLadsDiscussUserStatusToolByCondition(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition, Page page) {
        return ladsDiscussUserStatusToolDao.findLadsDiscussUserStatusToolByCondition(ladsDiscussUserStatusToolCondition, page, null);
    }

    @Override
    public List<LadsDiscussUserStatusTool> findLadsDiscussUserStatusToolByCondition(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition, Order order) {
        return ladsDiscussUserStatusToolDao.findLadsDiscussUserStatusToolByCondition(ladsDiscussUserStatusToolCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsDiscussUserStatusToolDao.count(null);
    }

    @Override
    public Long count(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition) {
        return this.ladsDiscussUserStatusToolDao.count(ladsDiscussUserStatusToolCondition);
    }

    @Override
    public LadsDiscussUserStatusTool findLadsDiscussUserStatusToolByUuid(String uuid) {
        try {
            return ladsDiscussUserStatusToolDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }

    @Override
    public LadsDiscussUserStatusTool findUserStatusByToolIdAndUserId(GetToolCondition egsc) {
        return ladsDiscussUserStatusToolDao.findUserStatusByToolIdAndUserId(egsc);
    }

    @Override
    public List<String> findScoreByToolIdAndUserId(CountFinishedStatusCondition cfsc) {
        return ladsDiscussUserStatusToolDao.findScoreByToolIdAndUserId(cfsc);
    }
}
