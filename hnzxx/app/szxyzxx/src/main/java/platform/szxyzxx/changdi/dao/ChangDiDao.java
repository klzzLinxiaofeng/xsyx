package platform.szxyzxx.changdi.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.changdi.vo.ChangDi;

import java.util.List;

public interface ChangDiDao {
    Integer createchangdi(ChangDi changDi);
    Integer updatechangdi(ChangDi changDi);
    Integer deleteChangDi(Integer id);
    List<ChangDi> findByAll(String name,String address, Page page);
    ChangDi findById(Integer id);
}
