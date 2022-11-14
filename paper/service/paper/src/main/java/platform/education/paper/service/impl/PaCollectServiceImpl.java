package platform.education.paper.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaCollect;
import platform.education.paper.vo.PaCollectCondition;
import platform.education.paper.service.PaCollectService;
import platform.education.paper.dao.PaCollectDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Collection;
import java.util.Date;

public class PaCollectServiceImpl implements PaCollectService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private PaCollectDao paCollectDao;

    public void setPaCollectDao(PaCollectDao paCollectDao) {
        this.paCollectDao = paCollectDao;
    }

    @Override
    public PaCollect findPaCollectById(Integer id) {
        try {
            return paCollectDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public PaCollect add(PaCollect paCollect) {
        if (paCollect == null) {
            return null;
        }
        Date createDate = paCollect.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        paCollect.setCreateDate(createDate);
        paCollect.setModifyDate(createDate);
        return paCollectDao.create(paCollect);
    }

    @Override
    public PaCollect modify(PaCollect paCollect) {
        if (paCollect == null) {
            return null;
        }
        Date modify = paCollect.getModifyDate();
        paCollect.setModifyDate(modify != null ? modify : new Date());
        return paCollectDao.update(paCollect);
    }

    @Override
    public void remove(PaCollect paCollect) {
        paCollectDao.delete(paCollect);
    }

    @Override
    public List<PaCollect> findPaCollectByCondition(PaCollectCondition paCollectCondition, Page page, Order order) {
        return paCollectDao.findPaCollectByCondition(paCollectCondition, page, order);
    }

    @Override
    public List<PaCollect> findPaCollectByCondition(PaCollectCondition paCollectCondition) {
        return paCollectDao.findPaCollectByCondition(paCollectCondition, null, null);
    }

    @Override
    public List<PaCollect> findPaCollectByCondition(PaCollectCondition paCollectCondition, Page page) {
        return paCollectDao.findPaCollectByCondition(paCollectCondition, page, null);
    }

    @Override
    public List<PaCollect> findPaCollectByCondition(PaCollectCondition paCollectCondition, Order order) {
        return paCollectDao.findPaCollectByCondition(paCollectCondition, null, order);
    }

    @Override
    public Long count() {
        return this.paCollectDao.count(null);
    }

    @Override
    public Long count(PaCollectCondition paCollectCondition) {
        return this.paCollectDao.count(paCollectCondition);
    }

    @Override
    public void remove(PaCollectCondition paCollectCondition) {
        this.paCollectDao.deleteByCondition(paCollectCondition);
    }
    
    
    
    //以下为业务方法
    @Override
    public PaCollect findPaCollectByUuid(String id) {
        try {
            return paCollectDao.findByUuid(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", id);
        }
        return null;
    }

    @Override
    public List<PaCollect> confirmCollect(String userId, Collection relateId, int collectType) {
        return paCollectDao.confirmCollect(userId, relateId, collectType);
    }
}
