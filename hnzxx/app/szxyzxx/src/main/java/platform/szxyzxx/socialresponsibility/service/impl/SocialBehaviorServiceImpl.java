package platform.szxyzxx.socialresponsibility.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.socialresponsibility.dao.SocialalBehaviorDao;
import platform.szxyzxx.socialresponsibility.service.SocialBehaviorService;
import platform.szxyzxx.socialresponsibility.service.SocialIndicatorService;
import platform.szxyzxx.socialresponsibility.vo.SocialBehavior;
import platform.szxyzxx.socialresponsibility.vo.SocialIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
@Service
public class SocialBehaviorServiceImpl implements SocialBehaviorService {
    @Autowired
    private SocialalBehaviorDao socialalBehaviorDao;
    @Autowired
    private SocialIndicatorService socialIndicatorService;
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<SocialBehavior> findByAll(QueryPojo queryPojo, Page page) {
        return socialalBehaviorDao.findByAll(queryPojo,page);
    }

    @Override
    public List<SocialBehavior> findByAllTongji(UserInfo userInfo, String schoolYear, String schoolTrem,
                                                Integer gradeId, Integer teamId, String stuName,
                                                Page page) {
        List<Student> student=studentDao.findByall(userInfo.getSchoolId(),schoolYear,teamId,gradeId,stuName,page);
      if(schoolYear!=null && schoolTrem!=null){
          userInfo.setSchoolYear(schoolYear);
          userInfo.setSchoolTermCode(schoolTrem);
      }
        List<SocialIndicators> idealIndicators=socialIndicatorService.findByAll(userInfo,null);
        List<SocialBehavior> list=new ArrayList<>();
        for(Student aa:student){
            SocialBehavior idealBehavior=new SocialBehavior();
            idealBehavior.setStudentName(aa.getName());
            idealBehavior.setId(aa.getId());
            List<SocialIndicators> idealIndicatorsList=new ArrayList<>();
            for(SocialIndicators bb:idealIndicators){
                SocialIndicators idealIndicators1=new SocialIndicators();
                Integer num=socialalBehaviorDao.findByNumber(schoolYear,schoolTrem,aa.getId(),bb.getId());
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
