package platform.szxyzxx.indicators.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Grade;
import platform.szxyzxx.indicators.dao.IndicatorsDao;
import platform.szxyzxx.indicators.pojo.IndicatorsPojo;
import platform.szxyzxx.indicators.pojo.StudentIndicaPojo;
import platform.szxyzxx.indicators.service.IndicatorsService;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Date;
import java.util.List;

@Service
public class IndicatorsServiceImpl implements IndicatorsService {
    @Autowired
    private IndicatorsDao indicatorsDao;

    @Override
    public List<Grade> findBygrade(UserInfo user,String schoolYear) {
        return indicatorsDao.findBygrade(user.getSchoolId(),schoolYear);
    }

    @Override
    public List<IndicatorsPojo> findByAll(UserInfo user,Integer gradeId,String schoolYear) {
        List<IndicatorsPojo> indicatorsPojoList= indicatorsDao.findByAll(gradeId,user.getSchoolId(),schoolYear);
        return indicatorsPojoList;
    }

    @Override
    public Boolean create(IndicatorsPojo indicatorsPojo,UserInfo user) {
        IndicatorsPojo indicatorsPojo1=new IndicatorsPojo();
        Date date=new Date();
        indicatorsPojo1.setCreateTime(date);
        indicatorsPojo1.setSchoolYear(indicatorsPojo.getSchoolYear());
        indicatorsPojo1.setSchoolId(user.getSchoolId());
        indicatorsPojo1.setIsDelete(0);
        indicatorsPojo1.setModieTime(date);
        indicatorsPojo1.setGradeId(indicatorsPojo.getGradeId());
        indicatorsPojo1.setDanwei(indicatorsPojo.getDanwei());
        indicatorsPojo1.setName(indicatorsPojo.getName());
        List<Integer> studentIdList=indicatorsDao.findByGrade(user.getSchoolId(),indicatorsPojo.getGradeId());
       int a= indicatorsDao.create(indicatorsPojo1);
       int b=indicatorsPojo1.getId();
        Boolean falg=true;
       if(a>0){
           if(studentIdList.size()>0){
               for(Integer aa:studentIdList){
                   StudentIndicaPojo studentIndicaPojo=new StudentIndicaPojo();
                   studentIndicaPojo.setBaogaoId(null);
                   studentIndicaPojo.setIsDelete(0);
                   studentIndicaPojo.setIndicatorsId(b);
                   studentIndicaPojo.setSchoolYear(indicatorsPojo1.getSchoolYear());
                   studentIndicaPojo.setStudentId(aa);
                   studentIndicaPojo.setScore(0);
                   indicatorsDao.createStudent(studentIndicaPojo);
               }
           }
           return falg;
       }
       falg=false;
        return falg;
    }



}
