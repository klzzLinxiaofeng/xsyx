package platform.szxyzxx.huojiang.service;

import framework.generic.dao.Page;
import platform.szxyzxx.huojiang.vo.ClassLunwen;
import platform.szxyzxx.huojiang.vo.DaochuPojo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface ClassLunwenService {
    List<ClassLunwen> findByAll(String startTime, String endTime, String type, String teacherName,Integer leixing,String theme, Page page);
    ClassLunwen findById(Integer id,Integer taecherId);
   List<DaochuPojo> findByDaoAll(String startTime, String endTime, String type, String teacherName, Integer leixing, String theme);
   List<ClassLunwen> findByTongJi(String teacherName, UserInfo userInfo,Page page);
   List<ClassLunwen> findByhuojiangJiLu(Integer teacherId);
}
