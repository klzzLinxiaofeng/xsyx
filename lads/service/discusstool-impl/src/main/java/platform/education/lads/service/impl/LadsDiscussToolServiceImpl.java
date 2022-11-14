package platform.education.lads.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsDiscussTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussToolCondition;
import platform.education.lads.service.LadsDiscussToolService;
import platform.education.lads.dao.LadsDiscussToolDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LadsDiscussToolServiceImpl implements LadsDiscussToolService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsDiscussToolDao ladsDiscussToolDao;

    public void setLadsDiscussToolDao(LadsDiscussToolDao ladsDiscussToolDao) {
        this.ladsDiscussToolDao = ladsDiscussToolDao;
    }

    @Override
    public LadsDiscussTool findLadsDiscussToolById(Integer id) {
        try {
            return ladsDiscussToolDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsDiscussTool add(LadsDiscussTool ladsDiscussTool) {
        if (ladsDiscussTool == null) {
            return null;
        }
        Date createDate = ladsDiscussTool.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        ladsDiscussTool.setCreateDate(createDate);
        ladsDiscussTool.setModifyDate(createDate);
        return ladsDiscussToolDao.create(ladsDiscussTool);
    }

    @Override
    public LadsDiscussTool modify(LadsDiscussTool ladsDiscussTool) {
        if (ladsDiscussTool == null) {
            return null;
        }
        Date modify = ladsDiscussTool.getModifyDate();
        ladsDiscussTool.setModifyDate(modify != null ? modify : new Date());
        return ladsDiscussToolDao.update(ladsDiscussTool);
    }

    @Override
    public void remove(LadsDiscussTool ladsDiscussTool) {
        try {
            ladsDiscussToolDao.delete(ladsDiscussTool);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", ladsDiscussTool.getId(), e);
            }
        }
    }

    @Override
    public List<LadsDiscussTool> findLadsDiscussToolByCondition(LadsDiscussToolCondition ladsDiscussToolCondition, Page page, Order order) {
        return ladsDiscussToolDao.findLadsDiscussToolByCondition(ladsDiscussToolCondition, page, order);
    }

    @Override
    public List<LadsDiscussTool> findLadsDiscussToolByCondition(LadsDiscussToolCondition ladsDiscussToolCondition) {
        return ladsDiscussToolDao.findLadsDiscussToolByCondition(ladsDiscussToolCondition, null, null);
    }

    @Override
    public List<LadsDiscussTool> findLadsDiscussToolByCondition(LadsDiscussToolCondition ladsDiscussToolCondition, Page page) {
        return ladsDiscussToolDao.findLadsDiscussToolByCondition(ladsDiscussToolCondition, page, null);
    }

    @Override
    public List<LadsDiscussTool> findLadsDiscussToolByCondition(LadsDiscussToolCondition ladsDiscussToolCondition, Order order) {
        return ladsDiscussToolDao.findLadsDiscussToolByCondition(ladsDiscussToolCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsDiscussToolDao.count(null);
    }

    @Override
    public Long count(LadsDiscussToolCondition ladsDiscussToolCondition) {
        return this.ladsDiscussToolDao.count(ladsDiscussToolCondition);
    }

    //以下是业务方法
    @Override
    public LadsDiscussTool findLadsDiscussToolByUuid(String uuid) {
        try {
            return ladsDiscussToolDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }
}
