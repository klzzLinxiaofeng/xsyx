package platform.szxyzxx.warehousing.service;

import framework.generic.dao.Page;
import platform.szxyzxx.warehousing.vo.ShenHe;
import platform.szxyzxx.warehousing.vo.WareHousing;

import java.util.List;

public interface WareHousingService {
    List<WareHousing> findByAll(String shenqingrenName, Integer type, String name, Integer zhuangtai, String startTime, String endTime, Page page);
    List<WareHousing> findByDaoAll(String shenqingrenName, Integer type, String name, Integer zhuangtai, String startTime, String endTime);
    Integer create(WareHousing wareHousing);
    Integer update(WareHousing wareHousing);
    WareHousing findById(Integer id);
    Integer createShenhe(ShenHe shenHe);
}
