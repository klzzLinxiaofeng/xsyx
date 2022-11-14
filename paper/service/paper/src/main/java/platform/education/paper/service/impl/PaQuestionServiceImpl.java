package platform.education.paper.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaQuestion;
import platform.education.paper.vo.PaQuestionCondition;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.dao.PaQuestionDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Date;

public class PaQuestionServiceImpl implements PaQuestionService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private PaQuestionDao paQuestionDao;

    public void setPaQuestionDao(PaQuestionDao paQuestionDao) {
        this.paQuestionDao = paQuestionDao;
    }

    @Override
    public PaQuestion findPaQuestionById(Integer id) {
        try {
            return paQuestionDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public PaQuestion add(PaQuestion paQuestion) {
        if (paQuestion == null) {
            return null;
        }
        Date createDate = paQuestion.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        paQuestion.setCreateDate(createDate);
        paQuestion.setModifyDate(createDate);
        return paQuestionDao.create(paQuestion);
    }

    @Override
    public PaQuestion modify(PaQuestion paQuestion) {
        if (paQuestion == null) {
            return null;
        }
        Date modify = paQuestion.getModifyDate();
        paQuestion.setModifyDate(modify != null ? modify : new Date());
        return paQuestionDao.update(paQuestion);
    }

    @Override
    public void remove(PaQuestion paQuestion) {
        paQuestionDao.delete(paQuestion);
    }

    @Override
    public List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page, Order order) {
        return paQuestionDao.findPaQuestionByCondition(paQuestionCondition, page, order);
    }

    @Override
    public List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition) {
        return paQuestionDao.findPaQuestionByCondition(paQuestionCondition, null, null);
    }

    @Override
    public List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page) {
        return paQuestionDao.findPaQuestionByCondition(paQuestionCondition, page, null);
    }

    @Override
    public List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Order order) {
        return paQuestionDao.findPaQuestionByCondition(paQuestionCondition, null, order);
    }

    @Override
    public Long count() {
        return this.paQuestionDao.count(null);
    }

    @Override
    public Long count(PaQuestionCondition paQuestionCondition) {
        return this.paQuestionDao.count(paQuestionCondition);
    }

    @Override
    public void remove(PaQuestionCondition paQuestionCondition) {
        this.paQuestionDao.deleteByCondition(paQuestionCondition);
    }

    //以下为业务方法
    @Override
    public PaQuestion findPaQuestionByUuid(String uuid) {
        try {
            return paQuestionDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }
}
