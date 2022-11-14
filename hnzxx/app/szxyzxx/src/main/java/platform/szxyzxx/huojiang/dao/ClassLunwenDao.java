package platform.szxyzxx.huojiang.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.huojiang.vo.ClassLunwen;

import java.util.List;

public interface ClassLunwenDao {
   List<ClassLunwen> findByAll(String startTime, String endTime, String type, String teacherName,Integer leixing,String theme, Page page);
   ClassLunwen findById(Integer id);
   ClassLunwen findByTongJi(Integer id);
   List<ClassLunwen> findByhuojiangJiLu(Integer id);
}
