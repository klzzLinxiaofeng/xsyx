package platform.szxyzxx.activitieshome.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.activitieshome.dao.HomeBehaviorDao;
import platform.szxyzxx.activitieshome.dao.HomeIndicatorsDao;
import platform.szxyzxx.activitieshome.service.HomeBehaviorService;
import platform.szxyzxx.activitieshome.vo.HomeBehavior;
import platform.szxyzxx.activitieshome.vo.HomeIndicators;
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
public class HomeBehaviorServiceImpl implements HomeBehaviorService {
    @Autowired
    private HomeBehaviorDao homeBehaviorDao;
    @Autowired
    private HomeIndicatorsDao homeIndicatorsDao;
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<HomeBehavior> findByAll(QueryPojo queryPojo, Page page) {
        return homeBehaviorDao.findByAll(queryPojo,page);
    }

    @Override
    public List<HomeBehavior> findByAllTongji(UserInfo userInfo,String schoolYear, String schoolTrem,
                                              Integer gradeId, Integer teamId, String stuName,
                                              Page page) {
        List<Student> student=studentDao.findByall(userInfo.getSchoolId(),schoolYear,teamId,gradeId,stuName,page);
      if(schoolYear!=null && schoolTrem!=null){
          userInfo.setSchoolYear(schoolYear);
          userInfo.setSchoolTermCode(schoolTrem);
      }
        List<HomeIndicators> homeIndicators=homeIndicatorsDao.findByAll(userInfo,null);
        List<HomeBehavior> list=new ArrayList<>();
        for(Student aa:student){
            HomeBehavior homeBehavior=new HomeBehavior();
            homeBehavior.setStudentName(aa.getName());
            homeBehavior.setId(aa.getId());
            List<HomeIndicators> lifeIndicators1=new ArrayList<>();
            for(HomeIndicators bb:homeIndicators){
                HomeIndicators homeIndicators1=new HomeIndicators();
                Integer num=homeBehaviorDao.findByNumber(schoolYear,schoolTrem,aa.getId(),bb.getId());
                homeIndicators1.setName(bb.getName());
                if(num!=null){
                    homeIndicators1.setScore(bb.getScore()+num);
                }else{
                    homeIndicators1.setScore(bb.getScore());
                }
                lifeIndicators1.add(homeIndicators1);
            }
            homeBehavior.setLifeIndicatorsList(lifeIndicators1);
            list.add(homeBehavior);
        }
        return list;

    }
}
