package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsEditorUserStatusTool;
import platform.education.lads.vo.editortoolVo.LadsEditorUserStatusToolCondition;
import platform.education.lads.service.LadsEditorUserStatusToolService;
import platform.education.lads.dao.LadsEditorUserStatusToolDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.GetToolStatusCondition;

public class LadsEditorUserStatusToolServiceImpl implements LadsEditorUserStatusToolService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsEditorUserStatusToolDao ladsEditorUserStatusToolDao;

    public void setLadsEditorUserStatusToolDao(LadsEditorUserStatusToolDao ladsEditorUserStatusToolDao) {
        this.ladsEditorUserStatusToolDao = ladsEditorUserStatusToolDao;
    }

    @Override
    public LadsEditorUserStatusTool findLadsEditorUserStatusToolById(Integer id) {
        try {
            return ladsEditorUserStatusToolDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsEditorUserStatusTool add(LadsEditorUserStatusTool ladsEditorUserStatusTool) {
        if (ladsEditorUserStatusTool == null) {
            return null;
        }
        Date createDate = ladsEditorUserStatusTool.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        ladsEditorUserStatusTool.setCreateDate(createDate);
        ladsEditorUserStatusTool.setModifyDate(createDate);
        return ladsEditorUserStatusToolDao.create(ladsEditorUserStatusTool);
    }

    @Override
    public LadsEditorUserStatusTool modify(LadsEditorUserStatusTool ladsEditorUserStatusTool) {
        if (ladsEditorUserStatusTool == null) {
            return null;
        }
        Date modify = ladsEditorUserStatusTool.getModifyDate();
        ladsEditorUserStatusTool.setModifyDate(modify != null ? modify : new Date());
        return ladsEditorUserStatusToolDao.update(ladsEditorUserStatusTool);
    }

    @Override
    public void remove(LadsEditorUserStatusTool ladsEditorUserStatusTool) {
        try {
            ladsEditorUserStatusToolDao.delete(ladsEditorUserStatusTool);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", ladsEditorUserStatusTool.getId(), e);
            }
        }
    }

    @Override
    public List<LadsEditorUserStatusTool> findLadsEditorUserStatusToolByCondition(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition, Page page, Order order) {
        return ladsEditorUserStatusToolDao.findLadsEditorUserStatusToolByCondition(ladsEditorUserStatusToolCondition, page, order);
    }

    @Override
    public List<LadsEditorUserStatusTool> findLadsEditorUserStatusToolByCondition(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition) {
        return ladsEditorUserStatusToolDao.findLadsEditorUserStatusToolByCondition(ladsEditorUserStatusToolCondition, null, null);
    }

    @Override
    public List<LadsEditorUserStatusTool> findLadsEditorUserStatusToolByCondition(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition, Page page) {
        return ladsEditorUserStatusToolDao.findLadsEditorUserStatusToolByCondition(ladsEditorUserStatusToolCondition, page, null);
    }

    @Override
    public List<LadsEditorUserStatusTool> findLadsEditorUserStatusToolByCondition(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition, Order order) {
        return ladsEditorUserStatusToolDao.findLadsEditorUserStatusToolByCondition(ladsEditorUserStatusToolCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsEditorUserStatusToolDao.count(null);
    }

    @Override
    public Long count(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition) {
        return this.ladsEditorUserStatusToolDao.count(ladsEditorUserStatusToolCondition);
    }

    //以下是业务方法
    @Override
    public LadsEditorUserStatusTool findLadsEditorUserStatusToolByUuid(String uuid) {
        try {
            return ladsEditorUserStatusToolDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }
    
    @Override
    public List<LadsEditorUserStatusTool> findUserStatusByToolIdAndUserId(GetToolCondition egsc) {
        return this.ladsEditorUserStatusToolDao.findUserStatusByToolIdAndUserId(egsc);
    }
    
    @Override
    public Long countFinishedStatus(GetToolStatusCondition gtsc){
        return this.ladsEditorUserStatusToolDao.countFinishedStatus(gtsc);
    }
    
    @Override
    public List<String> findScoreByToolIdAndUserId(CountFinishedStatusCondition cfsc) {
        return ladsEditorUserStatusToolDao.findScoreByToolIdAndUserId(cfsc);
    }
}
