package platform.szxyzxx.pingyumoban.service.impl;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.pingyumoban.dao.PingYuMoBanDao;
import platform.szxyzxx.pingyumoban.service.PingYuMoBanService;
import platform.szxyzxx.pingyumoban.vo.PingYuMoBan;

import java.util.List;

@Service
public class PingYuMoBanServiceImpl implements PingYuMoBanService {
    @Autowired
    private PingYuMoBanDao pingYuMoBanDao;


    @Override
    public List<PingYuMoBan> findByAll(Integer pingyuType, Integer gradeId,String schoolYear,String schoolTrem,Integer subjectId, Page page) {
        return pingYuMoBanDao.findByAll(gradeId,pingyuType,schoolYear,schoolTrem,subjectId,page);
    }

    @Override
    public PingYuMoBan findById(Integer id) {
        return pingYuMoBanDao.findById(id);
    }

    @Override
    public Integer create(PingYuMoBan pingYuMoBan) {
        return pingYuMoBanDao.create(pingYuMoBan);
    }

    @Override
    public Integer update(PingYuMoBan pingYuMoBan) {
        return pingYuMoBanDao.update(pingYuMoBan);
    }

    @Override
    public Integer updateDelete(Integer id) {
        return pingYuMoBanDao.updateShanChu(id);
    }

    @Override
    public Integer updateShanchu(Integer id) {
        return pingYuMoBanDao.updateDelete(id);
    }

    @Override
    public List<PingYuMoBan> findByIds(String name, Integer gradeId, Integer subjectId, Integer liacyId,String schoolYear,String schoolTrem) {
        return pingYuMoBanDao.findByIds( name,  gradeId,  subjectId,  liacyId,schoolYear,schoolTrem);
    }

    @Override
    public List<PingYuMoBan> findBySchoolYear(Integer schoolYear, String schoolTrem) {
        return pingYuMoBanDao.findBySchoolYear(schoolYear,schoolTrem);
    }
}
