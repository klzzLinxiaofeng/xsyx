package platform.education.paper.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaPaper;
import platform.education.paper.vo.PaPaperCondition;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.dao.PaPaperDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Date;

public class PaPaperServiceImpl implements PaPaperService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private PaPaperDao paPaperDao;

    public void setPaPaperDao(PaPaperDao paPaperDao) {
        this.paPaperDao = paPaperDao;
    }

    @Override
    public PaPaper findPaPaperById(Integer id) {
        try {
            return paPaperDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public PaPaper add(PaPaper paPaper) {
        if (paPaper == null) {
            return null;
        }
        Date createDate = paPaper.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        paPaper.setCreateDate(createDate);
        paPaper.setModifyDate(createDate);
        return paPaperDao.create(paPaper);
    }

    @Override
    public PaPaper modify(PaPaper paPaper) {
        if (paPaper == null) {
            return null;
        }
        Date modify = paPaper.getModifyDate();
        paPaper.setModifyDate(modify != null ? modify : new Date());
        return paPaperDao.update(paPaper);
    }

    @Override
    public void remove(PaPaper paPaper) {
        paPaperDao.delete(paPaper);
    }

    @Override
    public List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page, Order order) {
        return paPaperDao.findPaPaperByCondition(paPaperCondition, page, order);
    }

    @Override
    public List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition) {
        return paPaperDao.findPaPaperByCondition(paPaperCondition, null, null);
    }

    @Override
    public List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page) {
        return paPaperDao.findPaPaperByCondition(paPaperCondition, page, null);
    }

    @Override
    public List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Order order) {
        return paPaperDao.findPaPaperByCondition(paPaperCondition, null, order);
    }

    @Override
    public Long count() {
        return this.paPaperDao.count(null);
    }

    @Override
    public Long count(PaPaperCondition paPaperCondition) {
        return this.paPaperDao.count(paPaperCondition);
    }

    @Override
    public void remove(PaPaperCondition paPaperCondition) {
        this.paPaperDao.deleteByCondition(paPaperCondition);
    }

    
    //以下为业务方法
    @Override
    public PaPaper findPaPaperByUuid(String id) {
        try {
            return paPaperDao.findByUuid(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", id);
        }
        return null;
    }
}
