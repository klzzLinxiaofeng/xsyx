package platform.szxyzxx.schoolbus.dao;

import platform.szxyzxx.schoolbus.pojo.StuAndPushRecord;

import java.util.List;
import java.util.Map;

public interface BusStuPushRecordDao {

    List<StuAndPushRecord> findAllStuRecord(Map<String,Object> map);

}
