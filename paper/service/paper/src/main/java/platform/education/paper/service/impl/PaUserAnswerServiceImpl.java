package platform.education.paper.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaUserAnswer;
import platform.education.paper.vo.PaUserAnswerCondition;
import platform.education.paper.service.PaUserAnswerService;
import platform.education.paper.dao.PaUserAnswerDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.util.Date;
import platform.education.paper.dao.PaPaperQuestionDao;
import platform.education.paper.model.PaPaperQuestion;
import platform.education.paper.vo.FindDistinctAnswerCondition;
import platform.education.paper.vo.FindDistinctAnswerResult;

public class PaUserAnswerServiceImpl implements PaUserAnswerService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private PaUserAnswerDao paUserAnswerDao;
    private PaPaperQuestionDao paPaperQuestionDao;

    public void setPaUserAnswerDao(PaUserAnswerDao paUserAnswerDao) {
        this.paUserAnswerDao = paUserAnswerDao;
    }

    @Override
    public PaUserAnswer findPaUserAnswerById(Integer id) {
        try {
            return paUserAnswerDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public PaUserAnswer add(PaUserAnswer paUserAnswer) {
        if (paUserAnswer == null) {
            return null;
        }
        Date createDate = paUserAnswer.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        paUserAnswer.setCreateDate(createDate);
        paUserAnswer.setModifyDate(createDate);
        return paUserAnswerDao.create(paUserAnswer);
    }

    @Override
    public PaUserAnswer modify(PaUserAnswer paUserAnswer) {
        if (paUserAnswer == null) {
            return null;
        }
        Date modify = paUserAnswer.getModifyDate();
        paUserAnswer.setModifyDate(modify != null ? modify : new Date());
        return paUserAnswerDao.update(paUserAnswer);
    }

    @Override
    public void remove(PaUserAnswer paUserAnswer) {
        paUserAnswerDao.delete(paUserAnswer);
    }

    @Override
    public List<PaUserAnswer> findPaUserAnswerByCondition(PaUserAnswerCondition paUserAnswerCondition, Page page, Order order) {
        return paUserAnswerDao.findPaUserAnswerByCondition(paUserAnswerCondition, page, order);
    }

    @Override
    public List<PaUserAnswer> findPaUserAnswerByCondition(PaUserAnswerCondition paUserAnswerCondition) {
        return paUserAnswerDao.findPaUserAnswerByCondition(paUserAnswerCondition, null, null);
    }

    @Override
    public List<PaUserAnswer> findPaUserAnswerByCondition(PaUserAnswerCondition paUserAnswerCondition, Page page) {
        return paUserAnswerDao.findPaUserAnswerByCondition(paUserAnswerCondition, page, null);
    }

    @Override
    public List<PaUserAnswer> findPaUserAnswerByCondition(PaUserAnswerCondition paUserAnswerCondition, Order order) {
        return paUserAnswerDao.findPaUserAnswerByCondition(paUserAnswerCondition, null, order);
    }

    @Override
    public Long count() {
        return this.paUserAnswerDao.count(null);
    }

    @Override
    public Long count(PaUserAnswerCondition paUserAnswerCondition) {
        return this.paUserAnswerDao.count(paUserAnswerCondition);
    }

    @Override
    public void remove(PaUserAnswerCondition paUserAnswerCondition) {
        this.paUserAnswerDao.deleteByCondition(paUserAnswerCondition);
    }

    //以下是业务方法
    @Override
    public PaUserAnswer findPaUserAnswerByUuid(String uuid) {
        try {
            return paUserAnswerDao.findByUuid(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在UUID为 {} ", uuid);
        }
        return null;
    }
    
    @Override
    public PaPaperQuestion getPaperQuestion(PaUserAnswer ua) {
        PaPaperQuestion ld = paPaperQuestionDao.findByUuid(ua.getPaperQuestion());
        if (ld != null) {
            return ld;
        } else {
            return null;
        }
    }

    @Override
    public List<FindDistinctAnswerResult> findDistinctAnswer(FindDistinctAnswerCondition ac, Page page, Order order) {
        return this.paUserAnswerDao.findDistinctAnswer(ac, page, order);
    }

    @Override
    public List<String> findDistinctQuestion(FindDistinctAnswerCondition ac, Page page, Order order) {
        return this.paUserAnswerDao.findDistinctQuestion(ac, page, order);
    }
}
