package platform.szxyzxx.pingyumoban.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.pingyumoban.vo.PingYuMoBan;

import java.util.List;

public interface PingYuMoBanDao {
     List<PingYuMoBan> findByAll(Integer gradeId, Integer pingyutypeId,String schoolYear,String schoolTrem,Integer subjectId, Page page);
    Integer create(PingYuMoBan pingYuMoBan);
    Integer update(PingYuMoBan pingYuMoBan);
    Integer updateShanChu(Integer id);
    Integer updateDelete(Integer id);
    PingYuMoBan findById(Integer id);
    List<PingYuMoBan> findByIds(String name, Integer gradeId,Integer  subjectId, Integer liacyId,String schoolYear,String schoolTrem);
    List<PingYuMoBan> findBySchoolYear(Integer schoolYear, String schoolTrem);
}
