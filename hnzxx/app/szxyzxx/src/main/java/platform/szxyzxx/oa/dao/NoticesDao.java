package platform.szxyzxx.oa.dao;

import platform.szxyzxx.oa.vo.Notices;

public interface NoticesDao {
    Integer create(Notices notices);
    Integer updateShenpi(Integer id, Integer zhuangti);
    Notices findById(Integer id);
    Integer updateNotice(Notices notices);
}
