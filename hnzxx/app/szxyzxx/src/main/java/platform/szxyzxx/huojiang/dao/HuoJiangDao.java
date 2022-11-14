package platform.szxyzxx.huojiang.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.huojiang.vo.HuoJiang;

import java.util.List;

public interface HuoJiangDao {
    Integer create(HuoJiang huoJiang);
    List<HuoJiang> findByAll(Integer shenhe,String name,String teacherName, Page page);
    HuoJiang findById(Integer id);
    Integer updateHuoJiang(Integer id);
    Integer updateShenHe(Integer id, Integer zhuangtai);
}
