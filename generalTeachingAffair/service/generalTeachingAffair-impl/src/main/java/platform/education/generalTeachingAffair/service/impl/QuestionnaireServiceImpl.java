package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.QuestionnaireDao;
import platform.education.generalTeachingAffair.model.Questionnaire;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.education.generalTeachingAffair.service.QuestionnaireService;
import platform.education.generalTeachingAffair.vo.QuestionnaireCondition;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/10/12 16:24
 * @Description: 问卷管理
 */
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 开课管理
     */
    private QuestionnaireDao questionnaireDao;


    public void setQuestionnaireDao(QuestionnaireDao questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }

    /**
     * 获取所有问卷
     *
     * @param condition
     * @param page
     * @param order
     * @return
     */
    @Override
    public List<Questionnaire> findQuestionnaireVoByCondition(QuestionnaireCondition condition, Page page, Order order) {
        List<Questionnaire> publicClassByCondition = null;
        try {
            publicClassByCondition = questionnaireDao.findQuestionnaireVoByCondition(condition, page, order);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return publicClassByCondition;
    }

    /**
     * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
     */
    @Override
    public String abandon(Questionnaire publicClass) {
        if (publicClass != null) {
            publicClass.setIsDelete(1);
            Date modify = publicClass.getModifyDate();
            publicClass.setModifyDate(modify != null ? modify : new Date());
            try {
                publicClass = this.questionnaireDao.update(publicClass);
                return PublicClassService.OPERATE_SUCCESS;
            } catch (Exception e) {
                log.error("废弃 -> {} 失败，异常信息为 {}", publicClass.getId(), e);
                return PublicClassService.OPERATE_ERROR;
            }
        }
        return PublicClassService.OPERATE_FAIL;
    }


    /**
     * 新增问卷
     *
     * @param publicClass
     * @return
     */
    @Override
    public Questionnaire add(Questionnaire publicClass) {
        if (publicClass == null) {
            return null;
        }
        Date createDate = publicClass.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        publicClass.setCreateDate(createDate);
        publicClass.setModifyDate(createDate);
        return questionnaireDao.create(publicClass);
    }

    @Override
    public Questionnaire findQuestionnaireVoById(Integer id) {
        try {
            return questionnaireDao.findById(id);
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }


    @Override
    public Questionnaire modify(Questionnaire publicClass) {
        if (publicClass == null) {
            return null;
        }
        Date modify = publicClass.getModifyDate();
        publicClass.setModifyDate(modify != null ? modify : new Date());
        return questionnaireDao.update(publicClass);
    }

}
