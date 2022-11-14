package platform.szxyzxx.schoolbus.dao;




import platform.szxyzxx.schoolbus.pojo.BusStudentSign;
import platform.szxyzxx.schoolbus.pojo.StudentSignInfo;

import java.util.List;
import java.util.Map;


public interface BusStudentSignDao {

    int create(BusStudentSign record);


    void createBatch(Map map);


    List<StudentSignInfo> findStudentSignList( Map<String,Object> map);


    int countByBusStu(Map<String, Object> paramMap);


    List<StudentSignInfo> findPaginStudentSignCoreInfoList(Map<String, Object> paramMap);

    List<StudentSignInfo> findStudentSignCoreInfoList(Map<String, Object> paramMap);

}