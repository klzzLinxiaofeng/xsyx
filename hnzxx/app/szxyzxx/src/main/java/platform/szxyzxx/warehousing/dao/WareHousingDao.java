package platform.szxyzxx.warehousing.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.warehousing.vo.ShenHe;
import platform.szxyzxx.warehousing.vo.WareHousing;

import java.util.List;

public interface WareHousingDao {
    List<WareHousing> findByAll(String shenqingrenName, Integer type, String name, Integer zhuangtai, String startTime, String endTime,Page page);

    List<WareHousing> findByDaoAll(String shenqingrenName, Integer type, String name, Integer zhuangtai, String startTime, String endTime);
    Integer create(WareHousing wareHousing);
    Integer createshenhe(ShenHe shenHe);
    Integer update(WareHousing wareHousing);
    WareHousing findById(Integer id);
}
