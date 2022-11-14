package platform.szxyzxx.ishangkelilu.dao;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.ishangkelilu.pojo.*;

import java.util.List;

public interface StudyHabitsDao {
    Integer findByTeamId(Integer teacherId, String schoolYear);
    List<Student> findByStudent(Integer schoolId , Integer teamId,Integer gradeId,String schoolYear);
    StudyHabits findByStudy(StudyHabits studyHabits);
    Integer createZanshi(StudyHabits studyHabits);
    Integer updateZanshi(StudyHabits studyHabits);
   List<StudyHabits> findByXiake(Integer teacherId,Integer teamId);
   Integer create(StudyHabits studyHabits);
  List<StudentXin>  findBytianjiachazhao(String stuName, Integer schoolId,String schoolYear);
  List<ZuoWei> findByZuowei(Integer teamId);
  ZuoWei findByZuoweiaa(ZuoWei zuoWei);
  Integer createZuowei(ZuoWei zuoWei);
  List<StudyHabits> findByShangke(Integer teamId,Integer teacherId);
  Integer findBychehui(Integer id);
  ZuoWeiHangLie findByzuoweiHangfLie(Integer teamId);
  Integer createZuoweiHangLie(ZuoWeiHangLie zuoWeiHangLie);
  Integer updateZuoweiHangLie(ZuoWeiHangLie zuoWeiHangLie);
Integer updateAjsd(ZuoWei zuoWei);
  StudyHabits findByIdAndLeixing(Integer studentId,Integer leixing);

  List<StudyHabits> findByAllStudy(Integer gradeId, Integer teamId, Integer subjectId, String studentName,String schoolYear,String schoolTrem,String startTime,String endTime,  Page page);
    Integer updateJiLu(Integer id ,String pingyu);
    StudyHabits findById(Integer id);
    Integer updatezuowei(Integer id);
    List<HabitsDaoChu> findBydaochuzongfen(Integer gradeId, Integer teamId, String schoolYear, String schoolTrem, Integer subjectId,String startDate, String endDate,Integer studentId);
    List<HabitsDaoChu> findByDaoChu(Integer gradeId, Integer teamId, String schoolYear, String schoolTrem, Integer subjectId,String startDate, String endDate);
   List<KeTangGongShi> findByKeTangGongShi(Integer gradeId, Integer teamId, String schoolYear, String schoolTrem, Integer subjectId,String startDate, String endDate);

}
