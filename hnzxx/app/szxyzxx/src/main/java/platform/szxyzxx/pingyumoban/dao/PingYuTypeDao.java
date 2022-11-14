package platform.szxyzxx.pingyumoban.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.pingyumoban.vo.PingYuType;

import java.util.List;

public interface PingYuTypeDao {
    List<PingYuType> findByAll(String name, Page page);
    Integer create(PingYuType pingYuType);
    Integer update(Integer id,String name);
    Integer updateShanChu(Integer id);
    PingYuType findById(Integer id);
}
