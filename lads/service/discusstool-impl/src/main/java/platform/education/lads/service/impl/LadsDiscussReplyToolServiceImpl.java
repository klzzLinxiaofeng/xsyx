package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsDiscussReplyTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussReplyToolCondition;
import platform.education.lads.service.LadsDiscussReplyToolService;
import platform.education.lads.dao.LadsDiscussReplyToolDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.discussToolVo.LadsDiscussAttachmentResult;

public class LadsDiscussReplyToolServiceImpl implements LadsDiscussReplyToolService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsDiscussReplyToolDao ladsDiscussReplyToolDao;

    public void setLadsDiscussReplyToolDao(LadsDiscussReplyToolDao ladsDiscussReplyToolDao) {
        this.ladsDiscussReplyToolDao = ladsDiscussReplyToolDao;
    }

    @Override
    public LadsDiscussReplyTool findLadsDiscussReplyToolById(Integer id) {
        try {
            return ladsDiscussReplyToolDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsDiscussReplyTool add(LadsDiscussReplyTool ladsDiscussReplyTool) {
        if (ladsDiscussReplyTool == null) {
            return null;
        }
        Date createDate = ladsDiscussReplyTool.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        ladsDiscussReplyTool.setCreateDate(createDate);
        ladsDiscussReplyTool.setModifyDate(createDate);
        return ladsDiscussReplyToolDao.create(ladsDiscussReplyTool);
    }

    @Override
    public LadsDiscussReplyTool modify(LadsDiscussReplyTool ladsDiscussReplyTool) {
        if (ladsDiscussReplyTool == null) {
            return null;
        }
        Date modify = ladsDiscussReplyTool.getModifyDate();
        ladsDiscussReplyTool.setModifyDate(modify != null ? modify : new Date());
        return ladsDiscussReplyToolDao.update(ladsDiscussReplyTool);
    }

    @Override
    public void remove(LadsDiscussReplyTool ladsDiscussReplyTool) {
        try {
            ladsDiscussReplyToolDao.delete(ladsDiscussReplyTool);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", ladsDiscussReplyTool.getId(), e);
            }
        }
    }

    @Override
    public List<LadsDiscussReplyTool> findLadsDiscussReplyToolByCondition(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Page page, Order order) {
        return ladsDiscussReplyToolDao.findLadsDiscussReplyToolByCondition(ladsDiscussReplyToolCondition, page, order);
    }

    @Override
    public List<LadsDiscussReplyTool> findLadsDiscussReplyToolByCondition(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition) {
        return ladsDiscussReplyToolDao.findLadsDiscussReplyToolByCondition(ladsDiscussReplyToolCondition, null, null);
    }

    @Override
    public List<LadsDiscussReplyTool> findLadsDiscussReplyToolByCondition(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Page page) {
        return ladsDiscussReplyToolDao.findLadsDiscussReplyToolByCondition(ladsDiscussReplyToolCondition, page, null);
    }

    @Override
    public List<LadsDiscussReplyTool> findLadsDiscussReplyToolByCondition(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Order order) {
        return ladsDiscussReplyToolDao.findLadsDiscussReplyToolByCondition(ladsDiscussReplyToolCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsDiscussReplyToolDao.count(null);
    }

    @Override
    public Long count(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition) {
        return this.ladsDiscussReplyToolDao.count(ladsDiscussReplyToolCondition);
    }

    //以下是业务方法
    @Override
    public LadsDiscussReplyTool findLadsDiscussReplyToolByUuid(String uuid) {
        try {
            return ladsDiscussReplyToolDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }

    @Override
    public List<LadsDiscussAttachmentResult> findAttachMentByDiscussIdAndUserId(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Page page, Order order) {
        return ladsDiscussReplyToolDao.findAttachMentByDiscussIdAndUserId(ladsDiscussReplyToolCondition, page, order);
    }

    @Override
    public List<LadsDiscussReplyTool> findReplyListByToolId(String toolId, Page page, Order order) {
        return ladsDiscussReplyToolDao.findReplyListByToolId(toolId, page, order);
    }

    @Override
    public List<LadsDiscussReplyTool> findReplyListByToolIdAndUserId(GetToolCondition gtc, Page page, Order order) {
        return ladsDiscussReplyToolDao.findReplyListByToolIdAndUserId(gtc, page, order);
    }

    @Override
    public Long countFinishedStatus(CountFinishedStatusCondition cfsc) {
        return ladsDiscussReplyToolDao.countFinishedStatus(cfsc);
    }
}
