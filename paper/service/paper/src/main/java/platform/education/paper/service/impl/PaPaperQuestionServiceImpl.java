package platform.education.paper.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaPaperQuestion;
import platform.education.paper.vo.PaPaperQuestionCondition;
import platform.education.paper.service.PaPaperQuestionService;
import platform.education.paper.dao.PaPaperQuestionDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Date;
import platform.education.paper.vo.FindByPaperIdAndPosCondition;
import platform.education.paper.vo.FindCollectQuestionListCondition;
import platform.education.paper.vo.FindWrongQuestionListCondition;

public class PaPaperQuestionServiceImpl implements PaPaperQuestionService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private PaPaperQuestionDao paPaperQuestionDao;

    public void setPaPaperQuestionDao(PaPaperQuestionDao paPaperQuestionDao) {
        this.paPaperQuestionDao = paPaperQuestionDao;
    }

    @Override
    public PaPaperQuestion findPaPaperQuestionById(Integer id) {
        try {
            return paPaperQuestionDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public PaPaperQuestion add(PaPaperQuestion paPaperQuestion) {
        if (paPaperQuestion == null) {
            return null;
        }
        Date createDate = paPaperQuestion.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        paPaperQuestion.setCreateDate(createDate);
        paPaperQuestion.setModifyDate(createDate);
        return paPaperQuestionDao.create(paPaperQuestion);
    }

    @Override
    public PaPaperQuestion modify(PaPaperQuestion paPaperQuestion) {
        if (paPaperQuestion == null) {
            return null;
        }
        Date modify = paPaperQuestion.getModifyDate();
        paPaperQuestion.setModifyDate(modify != null ? modify : new Date());
        return paPaperQuestionDao.update(paPaperQuestion);
    }

    @Override
    public void remove(PaPaperQuestion paPaperQuestion) {
        paPaperQuestionDao.delete(paPaperQuestion);
    }

    @Override
    public List<PaPaperQuestion> findPaPaperQuestionByCondition(PaPaperQuestionCondition paPaperQuestionCondition, Page page, Order order) {
        return paPaperQuestionDao.findPaPaperQuestionByCondition(paPaperQuestionCondition, page, order);
    }

    @Override
    public List<PaPaperQuestion> findPaPaperQuestionByCondition(PaPaperQuestionCondition paPaperQuestionCondition) {
        return paPaperQuestionDao.findPaPaperQuestionByCondition(paPaperQuestionCondition, null, null);
    }

    @Override
    public List<PaPaperQuestion> findPaPaperQuestionByCondition(PaPaperQuestionCondition paPaperQuestionCondition, Page page) {
        return paPaperQuestionDao.findPaPaperQuestionByCondition(paPaperQuestionCondition, page, null);
    }

    @Override
    public List<PaPaperQuestion> findPaPaperQuestionByCondition(PaPaperQuestionCondition paPaperQuestionCondition, Order order) {
        return paPaperQuestionDao.findPaPaperQuestionByCondition(paPaperQuestionCondition, null, order);
    }

    @Override
    public Long count() {
        return this.paPaperQuestionDao.count(null);
    }

    @Override
    public Long count(PaPaperQuestionCondition paPaperQuestionCondition) {
        return this.paPaperQuestionDao.count(paPaperQuestionCondition);
    }

    @Override
    public void remove(PaPaperQuestionCondition paPaperQuestionCondition) {
        this.paPaperQuestionDao.deleteByCondition(paPaperQuestionCondition);
    }
    
    //以下为业务方法
    @Override
    public PaPaperQuestion findPaPaperQuestionByUuid(String id) {
        try {
            return paPaperQuestionDao.findByUuid(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", id);
        }
        return null;
    }

    @Override
    public PaPaperQuestion getParentPaperQuestion(PaPaperQuestion pq) {
        PaPaperQuestion pa = paPaperQuestionDao.findByUuid(pq.getParentQuestion());
        if (pa != null) {
            return pa;
        } else {
            return null;
        }
    }

    @Override
    public void deleteNotInUpdateQuestions(String paId, List<String> ids) {
        this.paPaperQuestionDao.deleteNotInUpdateQuestions(paId, ids);
    }

    @Override
    public void deleteChildren(String paperId) {
        this.paPaperQuestionDao.deleteChildren(paperId);
    }

    @Override
    public List<PaPaperQuestion> findWrongQuestionList(FindWrongQuestionListCondition wqlc, Page page, Order order) {
        return this.paPaperQuestionDao.findWrongQuestionList(wqlc, page, order);
    }

    @Override
    public List<PaPaperQuestion> findCollectQuestionList(FindCollectQuestionListCondition cqlc, Page page, Order order) {
        return this.paPaperQuestionDao.findCollectQuestionList(cqlc, page, order);
    }

    @Override
    public List<PaPaperQuestion> findByPaperIdAndPos(FindByPaperIdAndPosCondition pac, Page page, Order order) {
        return this.paPaperQuestionDao.findByPaperIdAndPos(pac, page, order);
    }

    @Override
    public Long countByPaperId(String paperId) {
        return this.paPaperQuestionDao.countByPaperId(paperId);
    }

    @Override
    public List<PaPaperQuestion> findByPaperId(String paperId, Page page, Order order) {
        return this.paPaperQuestionDao.findByPaperId(paperId, page, order);
    }

    @Override
    public List<String> findIdByPaperId(String paperId, Page page, Order order) {
        return this.paPaperQuestionDao.findIdByPaperId(paperId, page, order);
    }
}
