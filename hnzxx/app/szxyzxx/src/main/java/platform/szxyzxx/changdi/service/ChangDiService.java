package platform.szxyzxx.changdi.service;

import framework.generic.dao.Page;
import platform.szxyzxx.changdi.vo.ChangDi;

import java.util.List;

public interface ChangDiService {
    List<ChangDi> findByAll(String name, String address, Page page);
    String create(ChangDi changDi);
    String update(ChangDi changDi);
    String deleteChangDi(Integer id);
    ChangDi findById(Integer id);
}
