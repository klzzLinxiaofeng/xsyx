package platform.szxyzxx.kexuanke.service;

import framework.generic.dao.Page;
import platform.szxyzxx.kexuanke.vo.GradeKeXuanKe;
import platform.szxyzxx.kexuanke.vo.KeXuanKe;

import java.util.List;

public interface KeXuanKeService {
    List<KeXuanKe> findByGradeIdKeXuanKes(Integer gradeId);
    Integer updateKeXuanKe(Integer gradeId,Integer zhuantai);
    Integer createKeXuanKe(KeXuanKe keXuanKe);
    List<GradeKeXuanKe> findByAll(Integer schoolId, String schoolYear, Page page);

}
