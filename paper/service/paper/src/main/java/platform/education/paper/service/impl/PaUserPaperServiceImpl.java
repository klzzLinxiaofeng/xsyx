package platform.education.paper.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaUserPaper;
import platform.education.paper.vo.PaUserPaperCondition;
import platform.education.paper.service.PaUserPaperService;
import platform.education.paper.dao.PaUserPaperDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Date;

public class PaUserPaperServiceImpl implements PaUserPaperService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private PaUserPaperDao paUserPaperDao;

    @Override
    public PaUserPaper findPaUserPaperById(Integer id) {
        try {
            return paUserPaperDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public PaUserPaper add(PaUserPaper paUserPaper) {
        if (paUserPaper == null) {
            return null;
        }
        return paUserPaperDao.create(paUserPaper);
    }

    @Override
    public PaUserPaper modify(PaUserPaper paUserPaper) {
        if (paUserPaper == null) {
            return null;
        }
        return paUserPaperDao.update(paUserPaper);
    }

    @Override
    public void remove(PaUserPaper paUserPaper) {
        paUserPaperDao.delete(paUserPaper);
    }

    @Override
    public List<PaUserPaper> findPaUserPaperByCondition(PaUserPaperCondition paUserPaperCondition, Page page, Order order) {
        return paUserPaperDao.findPaUserPaperByCondition(paUserPaperCondition, page, order);
    }

    @Override
    public List<PaUserPaper> findPaUserPaperByCondition(PaUserPaperCondition paUserPaperCondition) {
        return paUserPaperDao.findPaUserPaperByCondition(paUserPaperCondition, null, null);
    }

    @Override
    public List<PaUserPaper> findPaUserPaperByCondition(PaUserPaperCondition paUserPaperCondition, Page page) {
        return paUserPaperDao.findPaUserPaperByCondition(paUserPaperCondition, page, null);
    }

    @Override
    public List<PaUserPaper> findPaUserPaperByCondition(PaUserPaperCondition paUserPaperCondition, Order order) {
        return paUserPaperDao.findPaUserPaperByCondition(paUserPaperCondition, null, order);
    }

    @Override
    public Long count() {
        return this.paUserPaperDao.count(null);
    }

    @Override
    public Long count(PaUserPaperCondition paUserPaperCondition) {
        return this.paUserPaperDao.count(paUserPaperCondition);
    }

    @Override
    public void remove(PaUserPaperCondition paUserPaperCondition) {
        this.paUserPaperDao.deleteByCondition(paUserPaperCondition);
    }
    
    //以下是业务方法
    @Override
    public PaUserPaper findPaUserPaperByUuid(String id) {
        try {
            return paUserPaperDao.findByUuid(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", id);
        }
        return null;
    }
}
