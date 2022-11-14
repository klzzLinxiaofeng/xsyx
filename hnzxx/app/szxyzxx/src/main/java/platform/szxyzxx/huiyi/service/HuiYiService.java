package platform.szxyzxx.huiyi.service;

import framework.generic.dao.Page;
import platform.szxyzxx.huiyi.vo.HuiYi;

import java.util.List;

public interface HuiYiService {
    List<HuiYi> findByAll(String theme, Integer depentId, String shenqingName,
                          String fuzeren, String changdi, String shijian,String shijian2,
                          Integer teacherId, String teacherName,Page page);
    List<HuiYi> findByAllDaoChu(String theme, Integer depentId, String shenqingName,
                                String fuzeren, String changdi, String shijian,String shijian2,Integer teacherid,String name );
    HuiYi findById(Integer id);
    String create( HuiYi huiYi);
    String update(HuiYi huiYi);
    String updateshanchu(Integer id);
    HuiYi fingByIdXingQing(Integer id);
    String updateShenHe(Integer id,Integer zhuangtai,String text);
    void createshenhe(HuiYi huiYi);
}
