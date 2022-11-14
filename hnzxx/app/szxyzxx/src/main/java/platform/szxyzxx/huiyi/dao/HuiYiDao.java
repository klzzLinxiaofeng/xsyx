package platform.szxyzxx.huiyi.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.huiyi.vo.HuiYi;

import java.util.List;

public interface HuiYiDao {
    List<HuiYi> findByAll(String theme, Integer depentId, String shenqingName,
                          String fuzeren, String changdi, String shijian,String shijian2,
                          Integer teacherId, String teacherName, Page page);
    List<HuiYi> findByAllDaoChu(String theme, Integer depentId, String shenqingName,
                                String fuzeren, String changdi, String shijian,String shijian2,Integer teacherid,String name );
    List<HuiYi> findByTime(String time);

    HuiYi findById(Integer id);
    Integer update(HuiYi huiYi);
    Integer create(HuiYi huiYi);
    Integer updateshanchu(Integer id);
    Integer updateShenHe(Integer id,Integer zhuangtai,String text);
    void createshenhe(HuiYi huiYi);
}
