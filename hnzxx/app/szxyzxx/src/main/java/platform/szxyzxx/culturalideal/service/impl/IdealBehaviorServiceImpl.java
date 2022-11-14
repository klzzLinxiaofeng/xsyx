package platform.szxyzxx.culturalideal.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.culturalideal.dao.IdealBehaviorDao;
import platform.szxyzxx.culturalideal.dao.IdealIndicatorsDao;
import platform.szxyzxx.culturalideal.service.IdealBehaviorService;
import platform.szxyzxx.culturalideal.vo.IdealBehavior;
import platform.szxyzxx.culturalideal.vo.IdealIndicators;
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
public class IdealBehaviorServiceImpl implements IdealBehaviorService {
    @Autowired
    private IdealBehaviorDao idealBehaviorDao;
    @Autowired
    private IdealIndicatorsDao idealIndicatorsDao;
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<IdealBehavior> findByAll(QueryPojo queryPojo, Page page) {
        return idealBehaviorDao.findByAll(queryPojo,page);
    }

    @Override
    public List<IdealBehavior> findByAllTongji(UserInfo userInfo,String schoolYear, String schoolTrem,
                                              Integer gradeId, Integer teamId, String stuName,
                                              Page page) {
        List<Student> student=studentDao.findByall(userInfo.getSchoolId(),schoolYear,teamId,gradeId,stuName,page);
      if(schoolYear!=null && schoolTrem!=null){
          userInfo.setSchoolYear(schoolYear);
          userInfo.setSchoolTermCode(schoolTrem);
      }
        List<IdealIndicators> idealIndicators=idealIndicatorsDao.findByAll(userInfo,null);
        List<IdealBehavior> list=new ArrayList<>();
        for(Student aa:student){
            IdealBehavior idealBehavior=new IdealBehavior();
            idealBehavior.setStudentName(aa.getName());
            idealBehavior.setId(aa.getId());
            List<IdealIndicators> idealIndicatorsList=new ArrayList<>();
            for(IdealIndicators bb:idealIndicators){
                IdealIndicators idealIndicators1=new IdealIndicators();
                Integer num=idealBehaviorDao.findByNumber(schoolYear,schoolTrem,aa.getId(),bb.getId());
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
