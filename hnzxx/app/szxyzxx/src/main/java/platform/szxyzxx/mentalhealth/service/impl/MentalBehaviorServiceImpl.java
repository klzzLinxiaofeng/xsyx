package platform.szxyzxx.mentalhealth.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.mentalhealth.dao.MentalBehaviorDao;
import platform.szxyzxx.mentalhealth.dao.MentalIndicatorsDao;
import platform.szxyzxx.mentalhealth.service.MentalBehaviorService;
import platform.szxyzxx.mentalhealth.vo.MentalBehavior;
import platform.szxyzxx.mentalhealth.vo.MentalIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
@Service
public class MentalBehaviorServiceImpl implements MentalBehaviorService {
    @Autowired
    private MentalBehaviorDao mentalBehaviorDao;
    @Autowired
    private MentalIndicatorsDao mentalIndicatorsDao;
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<MentalBehavior> findByAll(QueryPojo queryPojo, Page page) {
        return mentalBehaviorDao.findByAll(queryPojo,page);
    }

    @Override
    public List<MentalBehavior> findByAllTongji(UserInfo userInfo, String schoolYear, String schoolTrem,
                                                Integer gradeId, Integer teamId, String stuName,
                                                Page page) {
        List<Student> student=studentDao.findByall(userInfo.getSchoolId(),schoolYear,teamId,gradeId,stuName,page);
      if(schoolYear!=null && schoolTrem!=null){
          userInfo.setSchoolYear(schoolYear);
          userInfo.setSchoolTermCode(schoolTrem);
      }
        List<MentalIndicators> idealIndicators=mentalIndicatorsDao.findByAll(userInfo,null);
        List<MentalBehavior> list=new ArrayList<>();
        for(Student aa:student){
            MentalBehavior idealBehavior=new MentalBehavior();
            idealBehavior.setStudentName(aa.getName());
            idealBehavior.setId(aa.getId());
            List<MentalIndicators> idealIndicatorsList=new ArrayList<>();
            for(MentalIndicators bb:idealIndicators){
                MentalIndicators idealIndicators1=new MentalIndicators();
                Integer num=mentalBehaviorDao.findByNumber(schoolYear,schoolTrem,aa.getId(),bb.getId());
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
