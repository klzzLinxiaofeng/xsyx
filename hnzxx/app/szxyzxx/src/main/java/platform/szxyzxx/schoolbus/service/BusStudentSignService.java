package platform.szxyzxx.schoolbus.service;

import framework.generic.dao.Page;
import platform.szxyzxx.schoolbus.pojo.BusStudentSign;
import platform.szxyzxx.schoolbus.pojo.StudentSignInfo;

import java.util.List;
import java.util.Map;

/**
 * 学生校车打卡信息Service
 */
public interface BusStudentSignService {

    boolean create(BusStudentSign record);

    void createBatch(List<BusStudentSign> records);

    List<StudentSignInfo> findStudentSignInfoList(Integer teamId, String date, Integer direction);

    List<StudentSignInfo> findStudentSignCoreInfoList(Page page,String schoolYear,Integer teamId, Integer gradeId,String date, Integer direction, String name);
}
