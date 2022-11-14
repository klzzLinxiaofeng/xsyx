package platform.szxyzxx.knowledge.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.knowledge.dao.KnowLedgeDao;
import platform.szxyzxx.knowledge.service.KnowLedgeService;
import platform.szxyzxx.knowledge.vo.KnowLedge;
import platform.szxyzxx.knowledge.vo.Menu;

import java.util.List;

@Service
public class KnowLedgeServiceImpl implements KnowLedgeService {
    @Autowired
    private KnowLedgeDao knowLedgeDao;
    @Override
    public List<KnowLedge> findByAll(String name, Integer gradeId, Integer subject, Page page) {
        return knowLedgeDao.findByAll(name, gradeId, subject,page);
    }

    @Override
    public Integer create(KnowLedge knowLedge) {
        return knowLedgeDao.create(knowLedge);
    }

    @Override
    public Integer update(KnowLedge knowLedge) {
        return knowLedgeDao.update(knowLedge);
    }

    @Override
    public Integer updateDelete(Integer id) {
        return knowLedgeDao.updateDelete(id);
    }

    @Override
    public KnowLedge findById(Integer id) {
        return knowLedgeDao.findById(id);
    }

    @Override
    public List<Menu> findByAllMenu(Integer knowId) {
        //一级菜单
        List<Menu> menus= knowLedgeDao.findByAllMenu(knowId,0,null);
        if(menus.size()>0){
            for(Menu aa:menus){
                //二级菜单
                List<Menu> chilenMenus=knowLedgeDao.findByAllMenu(knowId,1,aa.getId());
                if(chilenMenus.size()>0){
                    //三级菜单
                    for(Menu bb:chilenMenus) {
                        List<Menu> chilenMenusTwo = knowLedgeDao.findByAllMenu(knowId, 2, bb.getId());
                        bb.setChildren(chilenMenusTwo);
                    }
                }
                aa.setChildren(chilenMenus);
            }
        }
        return menus;
    }



    @Override
    public Integer createMenu(Menu menu) {
        return knowLedgeDao.createMenu(menu);
    }

    @Override
    public Integer updateMenu(Menu menu) {
        return knowLedgeDao.updateMenu(menu);
    }
    @Override
    public Integer updateDeleteMenu(Integer id) {
        return knowLedgeDao.updateDeleteMenu(id);
    }

    @Override
    public Menu findByMenuId(Integer id) {
        return knowLedgeDao.findByMenuId(id);
    }
}
