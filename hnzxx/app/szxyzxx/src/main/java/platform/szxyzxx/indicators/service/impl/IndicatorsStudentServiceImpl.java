package platform.szxyzxx.indicators.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.indicators.dao.IndicatorsStudentDao;
import platform.szxyzxx.indicators.pojo.IndicatorsPojo;
import platform.szxyzxx.indicators.pojo.IndicatorsStudent;
import platform.szxyzxx.indicators.service.IndicatorsStudentService;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.*;


@Service
public class IndicatorsStudentServiceImpl implements IndicatorsStudentService {
    @Autowired
    private IndicatorsStudentDao incaStuDao;


    @Override
    public List<Student> findByStudent(Integer schoolId, Integer gradeId,
                                       Integer teamId, String stuName,String xn, Page page) {
        List<Student> students=incaStuDao.findByStudent(schoolId, gradeId, teamId, stuName,xn,page);
        return students;
    }

    @Override
    public IndicatorsStudent findByIndicator(UserInfo user, Integer studentId) {
        //这个学生所有的体测成绩
        List<IndicatorsStudent> list=incaStuDao.findBystudents(studentId,user.getSchoolYear(),user.getSchoolId());
        //健康信息
        IndicatorsStudent studentList =new IndicatorsStudent();
        IndicatorsStudent studentList2=incaStuDao.findByIndicaStudent(user.getSchoolId(),studentId);
        if(studentList2!=null){
            studentList.setBaogaoId(studentList2.getBaogaoId());
            studentList.setCervixReport(studentList2.getCervixReport());
            studentList.setHealthReport(studentList2.getHealthReport());
            studentList.setStudentId(studentList2.getStudentId());
            studentList.setStuName(studentList2.getStuName());
            studentList.setYiWu(studentList2.getYiWu());
        }else{
            Student student= incaStuDao.findByStudentId(studentId);
            if(student!=null){
                studentList.setStudentId(student.getId());
                studentList.setStuName(student.getName());
            }
        }
        //请假信息
        List<Integer> qingjia=incaStuDao.findByQingJia(studentId,user.getSchoolId());
        if(list.size()>0){
            studentList.setIndicatorsStudents(list);
        }
        if(qingjia.size()>0){
            int a=0;
           studentList.setJiaDay(qingjia.size());
           for(Integer aa:qingjia){
               if(aa!=null){
                   a+=aa;
               }
           }
           studentList.setTianshu(a/24);
        }
        studentList.setJiaDay(0);
        studentList.setTianshu(0);
        return studentList;
    }

    @Override
    public Integer updateIn(IndicatorsStudent indicatorsStudent) {
        List<IndicatorsPojo> indicatorsPojoList=indicatorsStudent.getIndicatorsPojo();
        Integer studentId = indicatorsStudent.getStudentId();
        indicatorsStudent.setCreateTime(new Date());
        if(indicatorsStudent.getBaogaoId()==null){
            Integer b=incaStuDao.create(indicatorsStudent);
            System.out.println("1"+b);
            Integer sda=incaStuDao.findByStudentIdss(indicatorsStudent.getStudentId());
            System.out.println("id"+sda);
            if (b> 0) {
                return sda;
            }
        }else{
            Date date = new Date();
            indicatorsStudent.setModieTime(date);
            Integer a = incaStuDao.updateBaogao(indicatorsStudent);
            System.out.println("2"+a);
            if (a > 0) {
                return indicatorsStudent.getBaogaoId();
            }
        }
            return 0;
    }

    @Override
    public String updatess(IndicatorsStudent indicatorsStudent) {
               Integer num=     incaStuDao.updateIndiStudent(indicatorsStudent);
               if(num>0){
                   return "success";
               }
        return "success";
    }
}
