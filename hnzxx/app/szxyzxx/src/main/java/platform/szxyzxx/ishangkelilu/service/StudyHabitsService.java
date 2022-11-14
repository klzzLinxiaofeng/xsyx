package platform.szxyzxx.ishangkelilu.service;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.ishangkelilu.pojo.*;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface StudyHabitsService {
    List<Student> findByStudent(UserInfo userInfo, Integer gradeId, String schoolYear, Integer teamId, Page page);
    String createOrupdate(StudyHabits studyHabits);

    String findByXiake(UserInfo userInfo,Integer teamId);
    List<StudentXin> findBytianjiachazhao(String stuName,UserInfo user);
    List<ZuoWei> findByZuowei(Integer teamId);
    Boolean createZuowei(ZuoWei zuoWei);
    List<StudyHabits> findByShangke(Integer teamId,Integer teacherId);
    Boolean findBychehui(Integer id);
    ZuoWeiHangLie findByzuoweiHangfLie(Integer teamId, UserInfo userInfo);
    Boolean createZuoweiHangLie(Integer teamId,Integer zu,Integer hang,Integer lie);
    Boolean updateZuoweiHangLie(Integer teamId,Integer zu,Integer hang,Integer lie);


    List<StudyHabits> getAllStudy(Integer gradeId,Integer teamId,Integer subjectId,String studentName,String schoolYear,String schoolTrem,String startTime,String endTime,Page page);
    Integer updateStudy(Integer id,String pingyu);
    StudyHabits findById(Integer id);
    String updatezuowei(Integer id);
    List<HabitsDaoChu> findByDaoChu(Integer gradeId, Integer teamId, String schoolYear, String schoolTrem, Integer subjectId,String startDate,String endDate);
    List<HabitsDaoChu> daochuzongfen(Integer gradeId, Integer teamId, String schoolYear, String schoolTrem, Integer subjectId,String startDate,String endDate);

    List<KeTangGongShi> findByKeTangGongShi(Integer gradeId, Integer teamId, String schoolYear, String schoolTrem, Integer subjectId,String startDate,String endDate);

}
