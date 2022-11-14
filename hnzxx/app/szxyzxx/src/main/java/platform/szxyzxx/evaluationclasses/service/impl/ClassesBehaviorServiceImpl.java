package platform.szxyzxx.evaluationclasses.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.evaluationclasses.dao.ClassesBehaviorDao;
import platform.szxyzxx.evaluationclasses.dao.ClassesIndicatorsDao;
import platform.szxyzxx.evaluationclasses.service.ClassesBehaviorService;
import platform.szxyzxx.evaluationclasses.vo.ClassesBehavior;
import platform.szxyzxx.evaluationclasses.vo.ClassesIndicators;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
@Service
public class ClassesBehaviorServiceImpl implements ClassesBehaviorService {
    @Autowired
    private ClassesBehaviorDao classesBehaviorDao;
    @Autowired
    private ClassesIndicatorsDao classesIndicatorsDao;
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<ClassesBehavior> findByAll(QueryPojo queryPojo, Page page) {
        return classesBehaviorDao.findByAll(queryPojo,page);
    }

    @Override
    public List<ClassesBehavior> findByAllTongji(UserInfo userInfo, String schoolYear, String schoolTrem,
                                                Integer gradeId, Integer teamId, String stuName,
                                                Page page) {
        List<Student> student=studentDao.findByall(userInfo.getSchoolId(),schoolYear,teamId,gradeId,stuName,page);
      if(schoolYear!=null && schoolTrem!=null){
          userInfo.setSchoolYear(schoolYear);
          userInfo.setSchoolTermCode(schoolTrem);
      }
        List<ClassesIndicators> idealIndicators=classesIndicatorsDao.findByAll(userInfo,null);
        List<ClassesBehavior> list=new ArrayList<>();
        for(Student aa:student){
            ClassesBehavior idealBehavior=new ClassesBehavior();
            idealBehavior.setStudentName(aa.getName());
            idealBehavior.setId(aa.getId());
            List<ClassesIndicators> idealIndicatorsList=new ArrayList<>();
            for(ClassesIndicators bb:idealIndicators){
                ClassesIndicators idealIndicators1=new ClassesIndicators();
                Integer num=classesBehaviorDao.findByNumber(schoolYear,schoolTrem,aa.getId(),bb.getId());
                idealIndicators1.setName(bb.getName());
                if(num!=null){
                    idealIndicators1.setScore(bb.getScore()+num);
                }else{
                    idealIndicators1.setScore(bb.getScore());
                }
                idealIndicatorsList.add(idealIndicators1);
            }
            idealBehavior.setLifeIndicatorsList(idealIndicatorsList);
            list.add(idealBehavior);
        }
        return list;

    }
}
