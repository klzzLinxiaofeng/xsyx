package platform.szxyzxx.lifebehavior.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.lifebehavior.dao.LifeBehaviorDao;
import platform.szxyzxx.lifebehavior.dao.LifeIndicatorsDao;
import platform.szxyzxx.lifebehavior.service.LifeBehaviorService;
import platform.szxyzxx.lifebehavior.vo.LifeBehavior;
import platform.szxyzxx.lifebehavior.vo.LifeIndicators;
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
public class LifeBehaviorServiceImpl implements LifeBehaviorService {
    @Autowired
    private LifeBehaviorDao lifeBehaviorDao;
    @Autowired
    private LifeIndicatorsDao lifeIndicatorsDao;
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<LifeBehavior> findByAll(QueryPojo queryPojo, Page page) {
        return lifeBehaviorDao.findByAll(queryPojo,page);
    }

    @Override
    public List<LifeBehavior> findByAllTongji(UserInfo userInfo, String schoolYear, String schoolTrem,
                                              Integer gradeId, Integer teamId, String stuName,
                                              Page page) {
        //List<LifeBehavior> evaluationList =lifeBehaviorDao.findByAllTongji();
        List<Student> student=studentDao.findByall(userInfo.getSchoolId(),schoolYear,teamId,gradeId,stuName,page);
        if(schoolYear!=null && schoolTrem!=null){
            userInfo.setSchoolYear(schoolYear);
            userInfo.setSchoolTermCode(schoolTrem);
        }
        List<LifeIndicators> lifeIndicators=lifeIndicatorsDao.findByAll(userInfo,null);
        List<LifeBehavior> list=new ArrayList<>();
        for(Student aa:student){
            LifeBehavior lifeBehavior=new LifeBehavior();
            lifeBehavior.setStudentName(aa.getName());
            lifeBehavior.setId(aa.getId());
            List<LifeIndicators> lifeIndicators1=new ArrayList<>();
            for(LifeIndicators bb:lifeIndicators){
                LifeIndicators lifeIndicators2=new LifeIndicators();
                Integer num=lifeBehaviorDao.findByNumber(schoolYear,schoolTrem,aa.getId(),bb.getId());
                lifeIndicators2.setName(bb.getName());
                if(num!=null){
                    lifeIndicators2.setScore(bb.getScore()+num);
                }else{
                    lifeIndicators2.setScore(bb.getScore());
                }
                lifeIndicators1.add(lifeIndicators2);
            }
            lifeBehavior.setLifeIndicatorsList(lifeIndicators1);
            list.add(lifeBehavior);
        }
        return list;

    }
}
