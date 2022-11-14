package platform.szxyzxx.schoolbus.service;

import platform.szxyzxx.schoolbus.pojo.StuAndPushRecord;

import java.util.List;

public interface BusStuPushRecordService {

    List<StuAndPushRecord> findAllStuRecord(String date);

}
