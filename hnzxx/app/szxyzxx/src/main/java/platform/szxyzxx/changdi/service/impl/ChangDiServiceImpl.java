package platform.szxyzxx.changdi.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.changdi.dao.ChangDiDao;
import platform.szxyzxx.changdi.service.ChangDiService;
import platform.szxyzxx.changdi.vo.ChangDi;

import java.util.List;

@Service
public class ChangDiServiceImpl implements ChangDiService {
    @Autowired
    private ChangDiDao changDiDao;

    @Override
    public List<ChangDi> findByAll(String name, String address, Page page) {
        return changDiDao.findByAll(name,address,page);
    }

    @Override
    public String create(ChangDi changDi) {
        int num=changDiDao.createchangdi(changDi);
        if(num>0){
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public String update(ChangDi changDi) {
        int num=changDiDao.updatechangdi(changDi);
        if(num>0){
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public String deleteChangDi(Integer id) {
        int num=changDiDao.deleteChangDi(id);
        if(num>0){
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public ChangDi findById(Integer id) {
        return changDiDao.findById(id);
    }
}
