package platform.szxyzxx.kexuanke.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.kexuanke.dao.KeXuanKeDao;
import platform.szxyzxx.kexuanke.service.KeXuanKeService;
import platform.szxyzxx.kexuanke.vo.GradeKeXuanKe;
import platform.szxyzxx.kexuanke.vo.KeXuanKe;

import java.util.List;

@Service
public class KeXuanKeServiceImpl implements KeXuanKeService {
    @Autowired
    private KeXuanKeDao keXuanKeDao;
    @Override
    public List<KeXuanKe> findByGradeIdKeXuanKes(Integer gradeId) {
        return keXuanKeDao.findByGradeIdKeXuanKes(gradeId);
    }

    @Override
    public Integer updateKeXuanKe(Integer gradeId, Integer zhuantai) {
        return keXuanKeDao.updateKeXuanKe(gradeId,zhuantai);
    }

    @Override
    public Integer createKeXuanKe(KeXuanKe keXuanKe) {
        return keXuanKeDao.createKeXuanKe(keXuanKe);
    }

    @Override
    public List<GradeKeXuanKe> findByAll(Integer schoolId, String schoolYear, Page page) {
        return keXuanKeDao.findByAll(schoolId,schoolYear,page);
    }
}
