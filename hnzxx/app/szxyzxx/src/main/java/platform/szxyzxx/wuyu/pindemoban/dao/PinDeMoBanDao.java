package platform.szxyzxx.wuyu.pindemoban.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.wuyu.pindemoban.vo.PinDeMoBan;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/14 13:10
 * @Version 1.0
 */
public interface PinDeMoBanDao {
    List<PinDeMoBan> findByAll(Integer zhibiaoId, String schoolYear, String schoolTrem, Page page);
    PinDeMoBan findById(Integer id);
    Integer create(PinDeMoBan pinDeMoBan);
    Integer update(PinDeMoBan pinDeMoBan);
    Integer updateDelete(Integer id);
}
