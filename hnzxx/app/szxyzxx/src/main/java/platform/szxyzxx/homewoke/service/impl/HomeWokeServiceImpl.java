package platform.szxyzxx.homewoke.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.homewoke.dao.HomeWokeDao;
import platform.szxyzxx.homewoke.pojo.HomeWokeGongShi;
import platform.szxyzxx.homewoke.pojo.HomeWokePojo;
import platform.szxyzxx.homewoke.pojo.HomeWokeXueKe;
import platform.szxyzxx.homewoke.pojo.StudentHomeWoke;
import platform.szxyzxx.homewoke.service.HomeWokeService;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
import java.util.Map;

@Service
public class HomeWokeServiceImpl implements HomeWokeService {
    //pj_publish_job_content  作业表

    @Autowired
    private HomeWokeDao homeWokeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private BasicSQLService basicSQLService;
    @Override
    public List<HomeWokePojo> findByAll(UserInfo userInfo,Integer teamId, Integer gradeId, String schoolYear,String schoolTrem, Integer subjectId, String text,String text2,Integer isHome,Page page) {
        return homeWokeDao.findByAll(userInfo.getSchoolId(),teamId,subjectId,text,text2,isHome,gradeId,schoolTrem,schoolYear,page);
    }

    @Override
    public String createHomke(HomeWokePojo homeWokePojo) {
        Integer num=homeWokeDao.createHomeWoke(homeWokePojo);
        Integer id=homeWokePojo.getId();
        List<Map<String ,Object>> studentList=basicSQLService.find("select * from pj_student where is_delete=0 and school_id="+homeWokePojo.getSchoolId()+" and team_id="+homeWokePojo.getTeamId());
       if(studentList.size()>0){
           for(Map<String,Object> aa:studentList){
               StudentHomeWoke studentHomeWoke=new StudentHomeWoke();
               studentHomeWoke.setCreateTime(homeWokePojo.getCreateTime());
               studentHomeWoke.setModieTime(homeWokePojo.getModieTime());
               studentHomeWoke.setJobId(id);
               studentHomeWoke.setStudentId(Integer.parseInt(aa.get("id").toString()));
               homeWokeDao.createStudent(studentHomeWoke);
           }
       }
        if(num>0){

            return "success";
        }
        return "false";
    }

    @Override
    public String UpdateHomke(HomeWokePojo homeWokePojo) {
        Integer num=homeWokeDao.updateAll(homeWokePojo);
        homeWokeDao.updateStudentHomeIsDelete(1,homeWokePojo.getId());
        List<Map<String ,Object>> studentList=basicSQLService.find("select * from pj_student where is_delete=0 and school_id="+homeWokePojo.getSchoolId()+" and team_id="+homeWokePojo.getTeamId());
        if(studentList.size()>0){
            for(Map<String,Object> aa:studentList){
                StudentHomeWoke studentHomeWoke=new StudentHomeWoke();
                studentHomeWoke.setCreateTime(homeWokePojo.getCreateTime());
                studentHomeWoke.setModieTime(homeWokePojo.getModieTime());
                studentHomeWoke.setJobId(homeWokePojo.getId());
                studentHomeWoke.setStudentId(Integer.parseInt(aa.get("id").toString()));
                homeWokeDao.createStudent(studentHomeWoke);
            }
        }
        if(num>0){
            return "success";
        }
        return "false";
    }
    @Override
    public Integer updateIsDelete(Integer is_delete, Integer id) {
        homeWokeDao.updateStudentHomeIsDelete(1,id);
        return homeWokeDao.updateIsDelete(1,id);
    }

    @Override
    public HomeWokePojo findById(Integer id) {
        return homeWokeDao.findById(id);
    }

    @Override
    public List<StudentHomeWoke> findByAllStudentAll(Integer id, Integer zhuangtai,Page page) {
        return homeWokeDao.findByStudentAll(id,zhuangtai,page);
    }

    @Override
    public String findByPingfen(StudentHomeWoke studentHomeWoke) {
        Integer pingfen=homeWokeDao.updatePingfen(studentHomeWoke);
        if(pingfen>0) {
            return "success";
        }
        return "评分失败";
    }

    @Override
    public List<StudentHomeWoke> findByJieshu(Integer id, Page page) {
        List<StudentHomeWoke> studentHomeWoke=homeWokeDao.findByStudentAll(id,null,page);
        for(StudentHomeWoke aa:studentHomeWoke){
            if(aa.getZhuantai()==0){
                //未交作业的改为缺交
            homeWokeDao.updateZhuanTai(aa.getStudentId(),aa.getJobId(),null,2);
            }
        }
        //修改收作业状态
        homeWokeDao.updateshouzuoye(id);
        List<StudentHomeWoke> studentHomeWoke2=homeWokeDao.findByStudentAll(id,null,page);
        return studentHomeWoke2;
    }

    @Override
    public Integer updatebujiao(Integer id, Page page) {
      Integer num=   homeWokeDao.updateZhuanTai(null,null,id,3);
      return num;
    }

    @Override
    public StudentHomeWoke findByStudentId(Integer id) {
        return homeWokeDao.findByStudentId(id);
    }

    @Override
    public Integer updateDaoru(StudentHomeWoke studentHomeWoke) {
        if(studentHomeWoke.getZhuangzhongwen().equals("缺交")){
            if(studentHomeWoke.getDengji()!=null){
                studentHomeWoke.setZhuantai(3);
            }else{
                studentHomeWoke.setZhuantai(2);
            }
        }
        if(studentHomeWoke.getZhuangzhongwen().equals("已交")){
            studentHomeWoke.setZhuantai(1);
        }
        if(studentHomeWoke.getZhuangzhongwen().equals("补交")){
            studentHomeWoke.setZhuantai(3);
        }
        return homeWokeDao.updateDaoru(studentHomeWoke);
    }

    @Override
    public Integer updateSaoMaQiang(Integer studentId, Integer jobId,String dengji) {
        return homeWokeDao.updateSaoMaQiang(studentId,jobId,dengji);
    }

    @Override
    public List<HomeWokeGongShi> findByGongshi(Integer schoolId,String schoolYear, String schoolTrem, Integer gradeId, Integer teamId, Integer subjectId, String startTime, String endTime, Integer isHome) {
        return homeWokeDao.findByGongshi(schoolId,schoolYear,schoolTrem,gradeId,teamId,subjectId,startTime,endTime, isHome);
    }

    @Override
    public List<HomeWokeXueKe> findByXueKe(Integer schoolId, String schoolYear, String schoolTrem, Integer gradeId, Integer teamId, Integer subjectId, String startTime, String endTime, Integer isHome) {
        return homeWokeDao.findByXueKe(schoolId,schoolYear,schoolTrem,gradeId,teamId,subjectId,startTime,endTime, isHome);

    }

    @Override
    public String findByIdYiBuZhi(HomeWokePojo homeWokePojo) {
        List<HomeWokePojo> list=homeWokeDao.findByIdYiBuZhi(homeWokePojo);
        if(list.size()>0){
            return "success";
        }
        return "error";
    }

    /*
    * 根据评分判断等级
    */
    public String findBydengji(StudentHomeWoke studentHomeWoke) {
        //评价等级   a+ 100,a90--99.5 ,b+85-89.5 b80-84.5  c70-79.5 d60-69.5 E<60
        if (studentHomeWoke.getFenzhi() != null) {
            Float fenzhi = studentHomeWoke.getFenzhi();

            if (fenzhi == 100) {
                return "A+";
            }
            if (fenzhi < 100 && fenzhi >= 90) {
                return "A";
            }
            if (fenzhi < 90 && fenzhi >= 85) {
                return "B+";
            }
            if (fenzhi < 85 && fenzhi >= 80) {
                return "B";
            }
            if (fenzhi < 80 && fenzhi >= 70) {
                return "C";
            }
            if (fenzhi < 70 && fenzhi >= 60) {
                return "D";
            }
            if (fenzhi < 60) {
                return "E";
            }
            return null;
        }
        else{
            return  null;
        }
    }
}
