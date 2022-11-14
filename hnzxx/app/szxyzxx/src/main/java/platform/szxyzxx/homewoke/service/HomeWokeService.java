package platform.szxyzxx.homewoke.service;

import framework.generic.dao.Page;
import platform.szxyzxx.homewoke.pojo.HomeWokeGongShi;
import platform.szxyzxx.homewoke.pojo.HomeWokePojo;
import platform.szxyzxx.homewoke.pojo.HomeWokeXueKe;
import platform.szxyzxx.homewoke.pojo.StudentHomeWoke;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface HomeWokeService {
    List<HomeWokePojo> findByAll(UserInfo userInfo,Integer teamId, Integer gradeId, String schoolYear,String schoolTrem, Integer  subjectId, String text,String text2,Integer isHome, Page page);
    String createHomke(HomeWokePojo homeWokePojo);
    String UpdateHomke(HomeWokePojo homeWokePojo);
    Integer updateIsDelete(Integer is_delete,Integer id);
    HomeWokePojo findById (Integer id);
    List<StudentHomeWoke> findByAllStudentAll(Integer id,Integer zhuangtai,Page page);
    /*
    * 评分
    */
    String findByPingfen(StudentHomeWoke studentHomeWoke);
    /*
    *结束收作业
    */
    List<StudentHomeWoke> findByJieshu(Integer id,Page page);
    /*
    *补交
    */

    Integer updatebujiao(Integer id,Page page);

    StudentHomeWoke findByStudentId(Integer id);

    Integer updateDaoru(StudentHomeWoke studentHomeWoke);

    Integer updateSaoMaQiang(Integer studentId,Integer jobId,String dengji);
    List<HomeWokeGongShi> findByGongshi(Integer schoolId,String schoolYear,String schoolTrem,Integer gradeId,Integer teamId,Integer  subjectId, String startTime,String endTime,Integer isHome);
    List<HomeWokeXueKe> findByXueKe(Integer schoolId, String schoolYear, String schoolTrem, Integer gradeId, Integer teamId, Integer  subjectId, String startTime, String endTime, Integer isHome);
    String findByIdYiBuZhi(HomeWokePojo homeWokePojo);
}
