package platform.szxyzxx.services.path.service.impl;

import platform.education.generalTeachingAffair.dao.ResPathDao;
import platform.education.generalTeachingAffair.model.ResPath;
import platform.szxyzxx.services.path.service.ResPathService;

import java.util.List;

public class ResPathServiceImpl implements ResPathService {

    public ResPathDao resPathDao;

    public void setResPathDao(ResPathDao dao) {
        this.resPathDao = dao;
    }

    @Override
    public List<ResPath> findAll(){
        return resPathDao.readAll();
    }

    @Override
    public List<ResPath> findByUserTypeId(Integer userTypeId) {
        return resPathDao.findByUserTypeId(userTypeId);
    }


}
