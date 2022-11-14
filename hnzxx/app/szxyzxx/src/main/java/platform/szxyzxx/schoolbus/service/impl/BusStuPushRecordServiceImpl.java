package platform.szxyzxx.schoolbus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.schoolbus.dao.BusStuPushRecordDao;
import platform.szxyzxx.schoolbus.pojo.StuAndPushRecord;
import platform.szxyzxx.schoolbus.service.BusStuPushRecordService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusStuPushRecordServiceImpl implements BusStuPushRecordService {
    @Autowired
    private BusStuPushRecordDao dao;
    @Override
    public List<StuAndPushRecord> findAllStuRecord(String date) {
        Map<String,Object> map=new HashMap<>(2,1);
        map.put("date",date);
        return dao.findAllStuRecord(map);
    }
}
