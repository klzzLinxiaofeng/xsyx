package platform.szxyzxx.aesthetic.service.impl;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.aesthetic.dao.AestheticDao;
import platform.szxyzxx.aesthetic.pojo.AestheticPojo;
import platform.szxyzxx.aesthetic.pojo.ErWerMa;
import platform.szxyzxx.aesthetic.service.AestheticService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Date;
import java.util.List;

@Service
public class AestheticServiceImpl implements AestheticService {
    @Autowired
    private AestheticDao aestheticDao;
    @Autowired
    private LoggerService loggerService;

    @Override
    public List<AestheticPojo> findByAestheticPojo(UserInfo userInfo, Integer studentId, String schoolYear, String schoolTrem, Page page) {
        List<AestheticPojo> aestheticPojo2=aestheticDao.findByAesthetic(studentId,userInfo.getSchoolId(),  schoolYear,  schoolTrem,  page);
       /* if(aestheticPojo2.size()>0){
            Student student=aestheticDao.findByStudentId(studentId,userInfo.getSchoolId());
            if(student!=null){
                System.out.println("student.getName()"+student.getName());
                aestheticPojo.setStudentId(student.getId());
                aestheticPojo.setStudentName(student.getName());
            }
            System.out.println("hehehe");
            return aestheticPojo2;
        }*/
        return aestheticPojo2;
    }

    @Override
    public AestheticPojo findByAestheticPojoId(Integer id) {
        AestheticPojo aestheticPojo2=aestheticDao.findByAestheticPojoId(id);
        return aestheticPojo2;
    }

    @Override
    public String createOrupdate(UserInfo userInfo,
                                 AestheticPojo aestheticPojo) {
        Student student=aestheticDao.findByStudentId(aestheticPojo.getStudentId(),userInfo.getSchoolId());
        Date date =new Date();
        Integer id=aestheticPojo.getId();
        Loggers logger = new Loggers();
        logger.setCaozuoId(userInfo.getTeacherId());
        logger.setName(userInfo.getRealName());
        logger.setUsername(userInfo.getUserName());
        logger.setMobile(userInfo.getTelephone());
        logger.setMokuaiName("艺术审美");
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        if(id==null){
            aestheticPojo.setCreateTime(date);
             Integer num=aestheticDao.create(aestheticPojo);
             if(num>0){
                 logger.setType(1);
                 logger.setMessage("添加" +student.getName()+ "的艺术审美获奖");
                 loggerService.create(logger);
                 return "success";
             }
             return "shibai";
        }
        Integer sss=aestheticDao.update(aestheticPojo);
        if(sss>0){
            logger.setType(2);
            logger.setMessage("修改" + student.getName() + "的艺术审美获奖");
            loggerService.create(logger);
            return "success";
        }
        return "shibai";
    }

    @Override
    public void createErweima(ErWerMa erWerMa) {
        aestheticDao.createErweima(erWerMa);
    }

    @Override
    public ErWerMa findByEeWeiMa(Integer studentId, String schoolYear) {
        return aestheticDao.findByEeWeiMa(studentId,schoolYear);
    }
}
