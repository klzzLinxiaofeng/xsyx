package platform.szxyzxx.homewoke.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.homewoke.pojo.HomeWokeGongShi;
import platform.szxyzxx.homewoke.pojo.HomeWokePojo;
import platform.szxyzxx.homewoke.pojo.HomeWokeXueKe;
import platform.szxyzxx.homewoke.pojo.StudentHomeWoke;

import java.util.List;

public interface HomeWokeDao {
    List<HomeWokePojo> findByAll(Integer schoolId, Integer teamId, Integer subjectId, String text,String text2,Integer isHome,Integer gradeId ,String schoolTrem,String schoolYear, Page page);
    Integer createHomeWoke(HomeWokePojo homeWokePojo);
    Integer updateAll(HomeWokePojo homeWokePojo);
    HomeWokePojo findById(Integer id);
    Integer updateIsDelete(Integer is_delete,Integer id);
    Integer createStudent(StudentHomeWoke studentHomeWoke);
    Integer updateStudentHomeIsDelete(Integer is_delete,Integer id);
    List<StudentHomeWoke> findByStudentAll(Integer jobId,Integer zhuangtai,Page page);

    Integer updatePingfen(StudentHomeWoke studentHomeWoke);

    Integer updateZhuanTai(Integer StudentId,Integer jobId,Integer id,Integer zhuantai);

    StudentHomeWoke findByStudentId(Integer id);
    Integer updateshouzuoye(Integer id);
    Integer updateDaoru(StudentHomeWoke studentHomeWoke);
    Integer updateSaoMaQiang(Integer studentId,Integer jobId,String dengji);
    List<HomeWokeGongShi> findByGongshi(Integer schoolId,String schoolYear, String schoolTrem, Integer gradeId, Integer teamId, Integer subjectId, String startTime, String endTime, Integer isHome);
    List<HomeWokeXueKe> findByXueKe(Integer schoolId, String schoolYear, String schoolTrem, Integer gradeId, Integer teamId, Integer subjectId, String startTime, String endTime, Integer isHome);
    List<HomeWokePojo> findByIdYiBuZhi(HomeWokePojo homeWokePojo);
}
