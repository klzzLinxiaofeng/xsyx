package platform.szxyzxx.schoolbus.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.schoolbus.dao.BusStudentSignDao;
import platform.szxyzxx.schoolbus.pojo.BusStudentSign;
import platform.szxyzxx.schoolbus.pojo.StudentSignInfo;
import platform.szxyzxx.schoolbus.service.BusStudentSignService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusStudentSignServiceImpl implements BusStudentSignService {
    @Autowired
    private BusStudentSignDao dao;
    @Override
    public boolean create(BusStudentSign record) {
        return dao.create(record)>0;
    }

    @Override
    public void createBatch(List<BusStudentSign> records) {
        Map<String,Object> map=new HashMap<>();
        map.put("records",records);
        dao.createBatch(map);
    }

    @Override
    public List<StudentSignInfo> findStudentSignInfoList(Integer teamId, String date, Integer direction) {
        Map<String,Object> map=new HashMap<>();
        map.put("teamId",teamId);
        map.put("date",date);
        map.put("direction",direction);
        return dao.findStudentSignList(map);
    }

    @Override
    public List<StudentSignInfo> findStudentSignCoreInfoList(Page page,String schoolYear,Integer teamId, Integer gradeId,String date, Integer direction, String name) {
        Map<String,Object> map=new HashMap<>();
        map.put("teamId",teamId);
        map.put("date",date);
        map.put("direction",direction);
        map.put("name", name);
        map.put("gradeId",gradeId);
        map.put("schoolYear",schoolYear);
        if(page!=null) {
            int total=dao.countByBusStu(map);
            page.init(total,page.getPageSize(),page.getCurrentPage());
            if(total>page.getPageSize()){
                int start=page.getPageSize()*(page.getCurrentPage()-1);
                map.put("limit","limit "+start+","+page.getPageSize());
            }
            return dao.findPaginStudentSignCoreInfoList(map);
        }
        return dao.findStudentSignCoreInfoList(map);
    }
}
