package platform.szxyzxx.wokeBiao.dao;

import platform.szxyzxx.wokeBiao.pojo.WokeXingQing;
import platform.szxyzxx.wokeBiao.pojo.ZhouQi;

public interface ZhouQiDao {
    WokeXingQing findByAll(Integer zhouQiId,Integer jieshu,Integer zhoushu);
    Integer createZhouQi(ZhouQi zhouQi);
    Integer createZhouQiXiangQing(WokeXingQing wokeXingQing);
    Integer updateZhouQiXianQing(WokeXingQing wokeXingQing);
    Integer updateZhouQi(ZhouQi zhouQi);
    ZhouQi findByAllZhouQi(Integer id);
    ZhouQi findById(Integer zhouShu,String schoolYear,String schoolTerm);

}

