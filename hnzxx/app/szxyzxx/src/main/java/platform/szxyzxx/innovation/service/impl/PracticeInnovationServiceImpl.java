package platform.szxyzxx.innovation.service.impl;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.PublicClass;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.innovation.dao.PracticeInnovationDao;
import platform.szxyzxx.innovation.pojo.PracticeInnovation;
import platform.szxyzxx.innovation.service.PracticeInnovationService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Date;
import java.util.List;

@Service
public class PracticeInnovationServiceImpl implements PracticeInnovationService {
   @Autowired
    private PracticeInnovationDao practiceInnovationDao;
    @Autowired
    private LoggerService loggerService;

    @Override
    public List<Grade> gradejsonList(Integer teacherId, String schoolYear) {
        return practiceInnovationDao.findByGrade(teacherId,schoolYear);
    }

    @Override
    public List<Student> findByStudent(UserInfo userInfo, Integer gradeId, String schoolYear, Integer teamId,String stuName, Page page) {
        List<Student> studentList = practiceInnovationDao.findByStudent(userInfo.getSchoolId(),gradeId,teamId,stuName,schoolYear,page);

        return studentList;
    }

    @Override
    public PracticeInnovation findByPraInner(UserInfo userInfo, Integer id) {
       PracticeInnovation practiceInnovation2=practiceInnovationDao.findByPraInner(userInfo.getSchoolId(),id);
        List<PublicClass> publicClasses=practiceInnovationDao.findByPublicClass(practiceInnovation2.getStudentId());
        if(publicClasses!=null) {
            practiceInnovation2.setPublicClassList(publicClasses);
        }
        return practiceInnovation2;
    }

    @Override
    public List<PracticeInnovation> findByPraInnerAll(UserInfo userInfo, Integer studentId,String schoolYear,String schoolTrem) {
        return practiceInnovationDao.findByPraInnerAll(userInfo.getSchoolId(),studentId,schoolYear,schoolTrem);
    }

    @Override
    public String createOrupdate(PracticeInnovation practiceInnovation, UserInfo userInfo) {
        Date date =new Date();
        Integer id=practiceInnovation.getId();
        if(id==null){
            if(practiceInnovation.getJiangzhuanId()==null){
                practiceInnovation.setJiangzhuanId(null);
            }
            if(practiceInnovation.getPctreId()==null){
                practiceInnovation.setPctreId(null);
            }
            practiceInnovation.setCreateTime(date);
            Integer num=practiceInnovationDao.create(practiceInnovation);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("实践创新");
                logger.setType(1);
                logger.setMessage("新增" +practiceInnovation.getStudentName() + "实践创新");
                logger.setSchoolYear(userInfo.getSchoolYear());
                logger.setSchoolTrem(userInfo.getSchoolTermCode());
                loggerService.create(logger);
                return "success";
            }
            return "shibai";
        }
        Integer sss=practiceInnovationDao.update(practiceInnovation);
        if(sss>0){
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("实践创新");
            logger.setType(2);
            logger.setMessage("修改" +practiceInnovation.getStudentName() + "的实践创新");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }
        return "shibai";
    }

    @Override
    public List<Student> findByStudentErweiMa(UserInfo userInfo, Integer gradeId, String schoolYear, Integer teamId, String stuName, Page page) {
        return practiceInnovationDao.findByStudentErweiMa(userInfo.getSchoolId(),gradeId,teamId,stuName,page);
    }

}
