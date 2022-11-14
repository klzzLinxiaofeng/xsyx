package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.ExamWorksSubjectTemplateDao;
import platform.education.generalTeachingAffair.model.ExamWorksSubjectTemplate;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.service.ExamWorksSubjectTemplateService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.vo.ExamWorksSubjectTemplateCondition;

import java.util.Date;
import java.util.List;

public class ExamWorksSubjectTemplateServiceImpl implements ExamWorksSubjectTemplateService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private ExamWorksSubjectTemplateDao examWorksSubjectTemplateDao;

    private GradeService gradeService;

    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public void setExamWorksSubjectTemplateDao(ExamWorksSubjectTemplateDao examWorksSubjectTemplateDao) {
        this.examWorksSubjectTemplateDao = examWorksSubjectTemplateDao;
    }

    @Override
    public ExamWorksSubjectTemplate findExamWorksSubjectTemplateById(Integer id) {
        try {
            return examWorksSubjectTemplateDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public ExamWorksSubjectTemplate add(ExamWorksSubjectTemplate examWorksSubjectTemplate) {
        if (examWorksSubjectTemplate == null) {
            return null;
        }
        Date createDate = examWorksSubjectTemplate.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        examWorksSubjectTemplate.setCreateDate(createDate);
        examWorksSubjectTemplate.setModifyDate(createDate);
        return examWorksSubjectTemplateDao.create(examWorksSubjectTemplate);
    }

    @Override
    public ExamWorksSubjectTemplate modify(ExamWorksSubjectTemplate examWorksSubjectTemplate) {
        if (examWorksSubjectTemplate == null) {
            return null;
        }
        Date modify = examWorksSubjectTemplate.getModifyDate();
        examWorksSubjectTemplate.setModifyDate(modify != null ? modify : new Date());
        return examWorksSubjectTemplateDao.update(examWorksSubjectTemplate);
    }

    @Override
    public void remove(ExamWorksSubjectTemplate examWorksSubjectTemplate) {
        try {
            examWorksSubjectTemplateDao.delete(examWorksSubjectTemplate);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", examWorksSubjectTemplate.getId(), e);
            }
        }
    }

    @Override
    public List<ExamWorksSubjectTemplate> findExamWorksSubjectTemplateByCondition(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition, Page page, Order order) {
        return examWorksSubjectTemplateDao.findExamWorksSubjectTemplateByCondition(examWorksSubjectTemplateCondition, page, order);
    }

    @Override
    public List<ExamWorksSubjectTemplate> findExamWorksSubjectTemplateByCondition(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition) {
        return examWorksSubjectTemplateDao.findExamWorksSubjectTemplateByCondition(examWorksSubjectTemplateCondition, null, null);
    }

    @Override
    public List<ExamWorksSubjectTemplate> findExamWorksSubjectTemplateByCondition(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition, Page page) {
        return examWorksSubjectTemplateDao.findExamWorksSubjectTemplateByCondition(examWorksSubjectTemplateCondition, page, null);
    }

    @Override
    public List<ExamWorksSubjectTemplate> findExamWorksSubjectTemplateByCondition(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition, Order order) {
        return examWorksSubjectTemplateDao.findExamWorksSubjectTemplateByCondition(examWorksSubjectTemplateCondition, null, order);
    }

    @Override
    public Long count() {
        return this.examWorksSubjectTemplateDao.count(null);
    }

    @Override
    public Long count(ExamWorksSubjectTemplateCondition examWorksSubjectTemplateCondition) {
        return this.examWorksSubjectTemplateDao.count(examWorksSubjectTemplateCondition);
    }

    @Override
    public ExamWorksSubjectTemplate findSubjectTemplate(Integer schoolId, Integer gradeId, String subjectCode) {
        Grade grade = gradeService.findGradeById(gradeId);
        if (grade == null) {
            return null;
        }
        String gradeCode = grade.getUniGradeCode();
        return getUniqueTemplate(schoolId, subjectCode, gradeCode);
    }

    private ExamWorksSubjectTemplate getUniqueTemplate(Integer schoolId, String subjectCode, String gradeCode) {
        ExamWorksSubjectTemplate template = this.examWorksSubjectTemplateDao.findUnique(schoolId,null, gradeCode, subjectCode);
        if (template == null) {
            template = this.examWorksSubjectTemplateDao.findUnique(0, null, gradeCode, subjectCode);
        }
        return template;
    }

    @Override
    public ExamWorksSubjectTemplate findUnique(Integer schoolId, Integer gradeId, String gradeCode, String subjectCode) {
        if (gradeCode == null || "".equals(gradeCode)) {
            gradeCode = gradeService.findGradeById(gradeId).getUniGradeCode();
        }
        return getUniqueTemplate(schoolId, subjectCode, gradeCode);
    }


    @Override
    public List<ExamWorksSubjectTemplate> acquireTemplateOfGrade(Integer schoolId, Integer gradeId) {
        Grade grade = gradeService.findGradeById(gradeId);
        if (grade == null) {
            return null;
        }
        String gradeCode = grade.getUniGradeCode();
        return getTemplates(schoolId, gradeCode);
    }

    private List<ExamWorksSubjectTemplate> getTemplates(Integer schoolId, String gradeCode) {
        List<ExamWorksSubjectTemplate> list = this.examWorksSubjectTemplateDao.findTemplateOfGrade(schoolId, null, gradeCode);
        if (list == null || list.size() == 0) {
            list = this.examWorksSubjectTemplateDao.findTemplateOfGrade(0, null, gradeCode);
            //同步模板 ---- 判断学校是否为空，为空则同步模板,不为空则只同步该年级的模板
            List<ExamWorksSubjectTemplate> templates = this.examWorksSubjectTemplateDao.findTemplateOfGrade(schoolId, null, null);
            if (templates == null || templates.size() == 0) {
                syncSchoolTemplate(schoolId, null);
            } else {
                syncTemplates(schoolId, list);
            }
        }
        return list;
    }

    @Override
    public List<ExamWorksSubjectTemplate> acquireTemplateOfGrade(Integer schoolId, Integer gradeId, String gradeCode) {
        if (gradeCode == null || "".equals(gradeCode)) {
            gradeCode = gradeService.findGradeById(gradeId).getUniGradeCode();
        }
        return getTemplates(schoolId, gradeCode);
    }

    @Override
    public void syncSchoolTemplate(Integer schoolId, String stages) {
        List<ExamWorksSubjectTemplate> list = null;
        if (stages == null || "".equals(stages)) {
            list = this.examWorksSubjectTemplateDao.findTemplateOfGrade(0, null, null);
            syncTemplates(schoolId, list);
        }
    }

    private void syncTemplates(Integer schoolId, List<ExamWorksSubjectTemplate> list) {
        ExamWorksSubjectTemplate subjectTemplate = null;
        if (list != null && list.size() > 0) {
            for (ExamWorksSubjectTemplate template : list) {
                subjectTemplate = new ExamWorksSubjectTemplate();
                subjectTemplate.setSchoolId(schoolId);
                subjectTemplate.setGradeCode(template.getGradeCode());
                subjectTemplate.setSubjectCode(template.getSubjectCode());
                subjectTemplate.setStatNeeded(true);
                subjectTemplate.setFullScore(template.getFullScore());
                subjectTemplate.setHighScore(template.getHighScore());
                subjectTemplate.setLowScore(template.getLowScore());
                subjectTemplate.setPassScore(template.getPassScore());
                subjectTemplate.setIsDelteted(false);
                subjectTemplate.setCreateDate(new Date());
                subjectTemplate.setModifyDate(new Date());
                examWorksSubjectTemplateDao.create(subjectTemplate);
            }
        }
    }
}
