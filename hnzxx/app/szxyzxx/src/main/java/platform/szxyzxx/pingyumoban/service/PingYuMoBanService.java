package platform.szxyzxx.pingyumoban.service;

import framework.generic.dao.Page;
import platform.szxyzxx.pingyumoban.vo.PingYuMoBan;

import java.util.List;

public interface PingYuMoBanService {
    public List<PingYuMoBan> findByAll(Integer pingyuType,Integer gradeId,String schoolYear,String schoolTrem,Integer subjectId, Page page);
    public PingYuMoBan findById(Integer id);
    Integer create(PingYuMoBan pingYuType);
    Integer update(PingYuMoBan pingYuType);
    Integer updateDelete(Integer id);
    Integer updateShanchu(Integer id);
    List<PingYuMoBan> findByIds(String name,Integer gradeId,Integer subjectId,Integer liacyId,String schoolYear,String schoolTrem);
    List<PingYuMoBan> findBySchoolYear(Integer schoolYear,String schoolTrem);
}
