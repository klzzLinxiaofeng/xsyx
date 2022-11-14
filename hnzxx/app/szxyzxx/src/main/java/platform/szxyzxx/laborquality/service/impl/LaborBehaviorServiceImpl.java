package platform.szxyzxx.laborquality.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.laborquality.dao.LaborBehaviorDao;
import platform.szxyzxx.laborquality.dao.LaborIndicatorsDao;
import platform.szxyzxx.laborquality.service.LaborBehaviorService;
import platform.szxyzxx.laborquality.vo.LaborBehavior;
import platform.szxyzxx.laborquality.vo.LaborIndicators;
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
public class LaborBehaviorServiceImpl implements LaborBehaviorService {
    @Autowired
    private LaborBehaviorDao idealBehaviorDao;
    @Autowired
    private LaborIndicatorsDao idealIndicatorsDao;
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<LaborBehavior> findByAll(QueryPojo queryPojo, Page page) {
        return idealBehaviorDao.findByAll(queryPojo,page);
    }

    @Override
    public List<LaborBehavior> findByAllTongji(UserInfo userInfo, String schoolYear, String schoolTrem,
                                               Integer gradeId, Integer teamId, String stuName,
                                               Page page) {
        List<Student> student=studentDao.findByall(userInfo.getSchoolId(),schoolYear,teamId,gradeId,stuName,page);
      if(schoolYear!=null && schoolTrem!=null){
          userInfo.setSchoolYear(schoolYear);
          userInfo.setSchoolTermCode(schoolTrem);
      }
        List<LaborIndicators> idealIndicators=idealIndicatorsDao.findByAll(userInfo,null);
        List<LaborBehavior> list=new ArrayList<>();
        for(Student aa:student){
            LaborBehavior idealBehavior=new LaborBehavior();
            idealBehavior.setStudentName(aa.getName());
            idealBehavior.setId(aa.getId());
            List<LaborIndicators> idealIndicatorsList=new ArrayList<>();
            for(LaborIndicators bb:idealIndicators){
                LaborIndicators idealIndicators1=new LaborIndicators();
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
