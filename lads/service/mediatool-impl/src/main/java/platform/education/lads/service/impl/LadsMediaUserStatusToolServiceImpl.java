package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsMediaUserStatusTool;

import platform.education.lads.service.LadsMediaUserStatusToolService;
import platform.education.lads.dao.LadsMediaUserStatusToolDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.GetToolStatusCondition;
import platform.education.lads.vo.mediaToolVo.LadsMediaUserStatusToolCondition;

public class LadsMediaUserStatusToolServiceImpl implements LadsMediaUserStatusToolService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsMediaUserStatusToolDao ladsMediaUserStatusToolDao;

    public void setLadsMediaUserStatusToolDao(LadsMediaUserStatusToolDao ladsMediaUserStatusToolDao) {
        this.ladsMediaUserStatusToolDao = ladsMediaUserStatusToolDao;
    }

    @Override
    public LadsMediaUserStatusTool findLadsMediaUserStatusToolById(Integer id) {
        try {
            return ladsMediaUserStatusToolDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsMediaUserStatusTool add(LadsMediaUserStatusTool ladsMediaUserStatusTool) {
        if (ladsMediaUserStatusTool == null) {
            return null;
        }
        Date createDate = ladsMediaUserStatusTool.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        ladsMediaUserStatusTool.setCreateDate(createDate);
        ladsMediaUserStatusTool.setModifyDate(createDate);
        return ladsMediaUserStatusToolDao.create(ladsMediaUserStatusTool);
    }

    @Override
    public LadsMediaUserStatusTool modify(LadsMediaUserStatusTool ladsMediaUserStatusTool) {
        if (ladsMediaUserStatusTool == null) {
            return null;
        }
        Date modify = ladsMediaUserStatusTool.getModifyDate();
        ladsMediaUserStatusTool.setModifyDate(modify != null ? modify : new Date());
        return ladsMediaUserStatusToolDao.update(ladsMediaUserStatusTool);
    }

    @Override
    public void remove(LadsMediaUserStatusTool ladsMediaUserStatusTool) {
        try {
            ladsMediaUserStatusToolDao.delete(ladsMediaUserStatusTool);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", ladsMediaUserStatusTool.getId(), e);
            }
        }
    }

    @Override
    public List<LadsMediaUserStatusTool> findLadsMediaUserStatusToolByCondition(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition, Page page, Order order) {
        return ladsMediaUserStatusToolDao.findLadsMediaUserStatusToolByCondition(ladsMediaUserStatusToolCondition, page, order);
    }

    @Override
    public List<LadsMediaUserStatusTool> findLadsMediaUserStatusToolByCondition(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition) {
        return ladsMediaUserStatusToolDao.findLadsMediaUserStatusToolByCondition(ladsMediaUserStatusToolCondition, null, null);
    }

    @Override
    public List<LadsMediaUserStatusTool> findLadsMediaUserStatusToolByCondition(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition, Page page) {
        return ladsMediaUserStatusToolDao.findLadsMediaUserStatusToolByCondition(ladsMediaUserStatusToolCondition, page, null);
    }

    @Override
    public List<LadsMediaUserStatusTool> findLadsMediaUserStatusToolByCondition(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition, Order order) {
        return ladsMediaUserStatusToolDao.findLadsMediaUserStatusToolByCondition(ladsMediaUserStatusToolCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsMediaUserStatusToolDao.count(null);
    }

    @Override
    public Long count(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition) {
        return this.ladsMediaUserStatusToolDao.count(ladsMediaUserStatusToolCondition);
    }

    //以下是业务方法
    @Override
    public LadsMediaUserStatusTool findLadsMediaUserStatusToolByUuid(String uuid) {
        try {
            return ladsMediaUserStatusToolDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }
    
    @Override
    public List<LadsMediaUserStatusTool> findUserStatusByToolIdAndUserId(GetToolCondition egsc) {
        return this.ladsMediaUserStatusToolDao.findUserStatusByToolIdAndUserId(egsc);
    }
    
    @Override
    public Long countFinishedStatus(GetToolStatusCondition gtsc){
        return this.ladsMediaUserStatusToolDao.countFinishedStatus(gtsc);
    }
    
    @Override
    public List<String> findScoreByToolIdAndUserId(CountFinishedStatusCondition cfsc){
        return this.ladsMediaUserStatusToolDao.findScoreByToolIdAndUserId(cfsc);
    }
}
