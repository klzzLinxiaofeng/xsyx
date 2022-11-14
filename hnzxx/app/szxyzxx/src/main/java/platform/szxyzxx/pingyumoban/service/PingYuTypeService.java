package platform.szxyzxx.pingyumoban.service;

import framework.generic.dao.Page;
import platform.szxyzxx.pingyumoban.vo.PingYuType;

import java.util.List;

public interface PingYuTypeService {
    public List<PingYuType> findByAll(String name, Page page);
    public PingYuType findById(Integer id);
    Integer create(PingYuType pingYuType);
    Integer update(PingYuType pingYuType);
    Integer updateDelete(Integer id);
}
