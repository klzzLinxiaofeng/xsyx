package platform.szxyzxx.knowledge.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.knowledge.vo.KnowLedge;
import platform.szxyzxx.knowledge.vo.Menu;

import java.util.List;

public interface KnowLedgeDao {
    List<KnowLedge> findByAll(String name, Integer gradeId, Integer subject, Page page);
    Integer create(KnowLedge knowLedge);
    Integer update(KnowLedge knowLedge);
    Integer updateDelete(Integer id);
    KnowLedge findById(Integer id);
    List<Menu> findByAllMenu(Integer knowId,Integer leven,Integer parentMenu);
    Integer createMenu(Menu menu);
    Integer updateMenu(Menu menu);
    Integer updateDeleteMenu(Integer id);
    Menu findByMenuId(Integer id);
}
