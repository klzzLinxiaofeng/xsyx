package platform.szxyzxx.wokeBiao.service;

import platform.szxyzxx.wokeBiao.pojo.DaoChuPojo;
import platform.szxyzxx.wokeBiao.pojo.WokeXingQing;
import platform.szxyzxx.wokeBiao.pojo.ZhouQi;

import java.util.List;
import java.util.Map;

public interface ZhouQiService {
    List<Map<String ,Object>> findByAll(Integer zhouShu, String  schoolYear, String schoolTerm);
        Integer craete(ZhouQi zhouQi);
        ZhouQi findById(Integer zhoushu,String schoolYear,String schoolTerm);
        Integer updateZhouQiXianQing(WokeXingQing wokeXingQing);
        ZhouQi findByAllZhouQi(Integer id);
        WokeXingQing findByObject(Integer zhouQiId,Integer jieshu,Integer zhoushu);
        Integer updateZhouQi(ZhouQi zhouQi);
        List<DaoChuPojo> findByDaoChu(Integer id);
    }

